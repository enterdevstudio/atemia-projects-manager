package fr.xinta.atemia.web;

import fr.xinta.atemia.db.entity.Person;
import fr.xinta.atemia.db.entity.Project;
import fr.xinta.atemia.db.entity.Week;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EditProject", urlPatterns = {"/editProject"})
public class EditProject extends AbstractServlet {

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
	
	// For fill the fiel with the current value
	String id = request.getParameter("project-id");
	Project project = projectFacade.find(id);
	
	if (project != null) {
	    request.setAttribute("project", project);
	    request.setAttribute("persons", personFacade.findAll());
	} else {
	    request.setAttribute("message", "No project has the id " + id + ". Aborting.");
	}
	
	request.getRequestDispatcher(INITIAL_VIEW()).forward(request, response);
    }

    @Override
    protected void executeRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	
	String id = request.getParameter("project-id");
	Project project = projectFacade.find(id);
	
	if (project != null) {
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
                Week startWeek = new Week(num, year);

                String ew = request.getParameter("endWeek");
                num = Integer.parseInt(ew.substring(6, 8));
                year = Integer.parseInt(ew.substring(0, 4));
                if (year < 1900 || year > 2500 || num < 1 || num > 52) {
                    throw new Exception("End week is incorrect!");
                }
                Week endWeek = new Week(num, year);
                
                
                if (startWeek.compare(endWeek) < 0) {
                    throw new Exception("Start week is after end week!");
                }
            
                if (startWeek.compare(project.getStartWeek()) != 0) {
                    project.setStartWeek(startWeek);
                }

                if (endWeek.compare(project.getEndWeek()) != 0) {
                    project.setEndWeek(endWeek);
                }                
                
                Person newManager = personFacade.find(request.getParameter("manager-id").split(" ")[0]);
                if (newManager == null)
                    throw new Exception("You have to set a manager to the project");
                
                Person oldManager = project.getManager();
                if (newManager.getId() != oldManager.getId()) {
                    oldManager.removeManagedProject(project);
                    newManager.getManagedProjects().add(project);           
                    project.setManager(newManager);
                    if (!project.getWorkers().contains(newManager)) {
                        project.AddWorker(newManager);
                    }
                    personFacade.merge(oldManager);
                    personFacade.merge(newManager);
                }

                projectFacade.merge(project);

                request.setAttribute("persons", personFacade.findAll());
                request.setAttribute("info_notification", "The project " + project.getName() + " has been updated.");
                request.getRequestDispatcher(EXECUTED_VIEW()).forward(request, response);
                
            } catch (NumberFormatException e) {
                request.setAttribute("error_notification",
                        "A field has an incorrect value. Please check if nbDaysSold is a positive number and if weeks has a good format.");
                initialRequest(request, response);
                
            } catch (StringIndexOutOfBoundsException e) {
                request.setAttribute("error_notification",
                        "Please check that start week and end week format is like 2012-W01");
                initialRequest(request, response);
                
            } catch (Exception e) {
                request.setAttribute("error_notification", e.getMessage());
                initialRequest(request, response);
            }
	} else {
	    request.setAttribute("error_notification", "No project has the id " + id + ". Aborting.");
            request.getRequestDispatcher("listProject").forward(request, response);
	}	
    }    
}
