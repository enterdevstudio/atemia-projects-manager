package fr.xinta.atemia.web;

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
            String ew = request.getParameter("endWeek");
            project.setStartWeek(new Week(
                    Integer.parseInt(sw.substring(6, 8)),
                    Integer.parseInt(sw.substring(0, 4))));
            project.setEndWeek(new Week(
                    Integer.parseInt(ew.substring(6, 8)),
                    Integer.parseInt(ew.substring(0, 4))));
            
            if (project.getStartWeek().compare(project.getEndWeek()) < 0) {
                throw new Exception("Start week is after end week!");
            }                    
            
            projectFacade.persist(project);

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
