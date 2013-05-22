package fr.xinta.atemia.web;

import fr.xinta.atemia.db.entity.Activity;
import fr.xinta.atemia.db.entity.Person;
import fr.xinta.atemia.db.entity.Project;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DeleteProject", urlPatterns = {"/deleteProject"})
public class DeleteProject extends AbstractServlet {
    
    @Override
    protected String INITIAL_VIEW() {
	return "displayProject";
    }

    @Override
    protected String EXECUTED_VIEW() {
	return "listProject";
    }

    @Override
    protected void initialRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
	String id = request.getParameter("project-id");
	Project project = projectFacade.find(id);
	
	if (project != null) {
	    request.setAttribute("id", project.getId());
	    request.setAttribute("confirmation_message", "You will delete this project. Are you sure?");
            request.getRequestDispatcher(INITIAL_VIEW() + "?project-id=" + id).forward(request, response);
	} else {
	    request.setAttribute("error_notification", "No project has the id " + id + ". Aborting.");
            request.getRequestDispatcher(EXECUTED_VIEW()).forward(request, response);
	}
    }

    @Override
    protected void executeRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String id = request.getParameter("id");
        Project project = projectFacade.find(id);

        if (project != null) {
            if (request.getParameter("yes") != null) {
                Person manager = project.getManager();
                manager.removeManagedProject(project);
                personFacade.merge(manager);
                
                for (Person p : project.getWorkers()) {
                    p.removeProject(project);
                    personFacade.merge(p);
                }
                for (Activity a : project.getActivities()) {
                    activityFacade.remove(a);
                }
                projectFacade.remove(project);
                
                request.setAttribute("info_notification", project.getName() + " has been removed.");
                request.getRequestDispatcher(EXECUTED_VIEW()).forward(request, response);
            } else {
                request.getRequestDispatcher(INITIAL_VIEW() + "?project-id=" + id).forward(request, response);
            }
        } else {
            request.setAttribute("error_notification", "No project has the id " + id + ". Aborting.");
            request.getRequestDispatcher(EXECUTED_VIEW()).forward(request, response);
        }
    }    
}
