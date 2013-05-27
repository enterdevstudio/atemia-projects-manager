package fr.xinta.atemia.web;

import fr.xinta.atemia.db.entity.Activity;
import fr.xinta.atemia.db.entity.Person;
import fr.xinta.atemia.db.entity.Project;
import fr.xinta.atemia.db.entity.Week;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CreateProject", urlPatterns = {"/createProject"})
public class CreateProject extends AbstractServlet {

    @Override
    protected String INITIAL_VIEW() {
	return VIEWS_PATH + "editProject.jsp";
    }

    @Override
    protected String EXECUTED_VIEW() {
	return VIEWS_PATH + "displayProject.jsp";
    }

    @Override
    protected void initialRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	
        request.setAttribute("persons", personFacade.findAll());
	request.getRequestDispatcher(INITIAL_VIEW()).forward(request, response);
    }

    @Override
    protected void executeRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	
        Project project = new Project();
        project.setName(request.getParameter("name"));
        project.setDepartment(request.getParameter("department"));
        request.setAttribute("project", project);
        
        try {
            project.setNbDaysSold(Float.parseFloat(request.getParameter("nbDaysSold")));
            if (project.getNbDaysSold() < 0) {
                throw new NumberFormatException();
            }

            String sw = request.getParameter("startWeek");
            int num = Integer.parseInt(sw.substring(6, 8));
            int year = Integer.parseInt(sw.substring(0, 4));
            if (year < 1900 || year > 2500 || num < 1 || num > 52) {
                throw new Exception("Start week is incorrect!");
            }
            project.setStartWeek(new Week(num, year));
            
            String ew = request.getParameter("endWeek");
            num = Integer.parseInt(ew.substring(6, 8));
            year = Integer.parseInt(ew.substring(0, 4));
            if (year < 1900 || year > 2500 || num < 1 || num > 52) {
                throw new Exception("End week is incorrect!");
            }
            project.setEndWeek(new Week(num, year));
            
            if (project.getStartWeek().compare(project.getEndWeek()) < 0) {
                throw new Exception("Start week is after end week!");
            }
            
            Person person = personFacade.find(request.getParameter("manager-id").split(" ")[0]);
            if (person == null)
                throw new Exception("You have to add a manager to the project");
            person.getManagedProjects().add(project);
            project.setManager(person);            
            project.AddWorker(person);
            
            // We persist the project first, then create activities and then merge
            projectFacade.persist(project);
                    
            for (Week week : project.getWeeks()) {
                activityFacade.persist(project.AddActivity(week, person));
            }
            
            personFacade.merge(person);
            projectFacade.merge(project);

            request.setAttribute("persons", personFacade.findAll());
            request.setAttribute("info_notification", "The project " + project.getName() + " has been created.");
            request.getRequestDispatcher(EXECUTED_VIEW()).forward(request, response);
            
        } catch (NumberFormatException e) {
            request.setAttribute("error_notification",
                    "A field has an incorrect value. Please check if nbDaysSold is a positive number and if weeks have a good format.");
            initialRequest(request, response);
            
        } catch (StringIndexOutOfBoundsException e) {
            request.setAttribute("error_notification",
                    "Please check that start week and end week format is like 2012-W01");
            initialRequest(request, response);
            
        } catch (Exception e) {
            request.setAttribute("error_notification", e.getMessage());
            initialRequest(request, response);
        }
    }    
}
