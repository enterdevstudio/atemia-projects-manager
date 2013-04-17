package fr.xinta.atemia.web;

import fr.xinta.atemia.db.entity.Project;
import static fr.xinta.atemia.web.AbstractServlet.VIEWS_PATH;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DeleteProject", urlPatterns = {"/deleteProject"})
public class DeleteProject extends AbstractServlet {
    
    @Override
    protected String INITIAL_VIEW() {
	return VIEWS_PATH + "displayProject.jsp";
    }

    @Override
    protected String EXECUTED_VIEW() {
	return VIEWS_PATH + "index.jsp";        
    }

    @Override
    protected void initialRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
	String id = request.getParameter("project-id");
	Project project = projectFacade.find(id);
	
	if (project != null) {
	    request.setAttribute("project", project);
	    request.setAttribute("id", project.getId());
	    request.setAttribute("messageconfirm", "You will delete this project. Are you sure?");
	} else {
	    request.setAttribute("message", "No project has the id " + id + ". Aborting.");
	}
	
	request.getRequestDispatcher(INITIAL_VIEW()).forward(request, response);
    }

    @Override
    protected void executeRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // If the person is not found or removed, so by default,
        // we display the list of persons
        String view = VIEWS_PATH;
        
        String id = request.getParameter("id");
        Project project = projectFacade.find(id);

        if (project != null) {
            if (request.getParameter("yes") != null) {                
                //TODO suppress everything linked with this project (weeks, activity...)
                projectFacade.remove(project);
                
                view += "listProject.jsp";
                request.setAttribute("message", project.getName() + " has been removed.");
                request.setAttribute("projects", projectFacade.findAll());                
            } else {                
                view += "displayProject.jsp";
                request.setAttribute("project", project);
            }
        } else {            
            request.setAttribute("projects", projectFacade.findAll());
            request.setAttribute("message", "No project has the id " + id + ". Aborting.");
        }
	
	request.getRequestDispatcher("listProject").forward(request, response);
    }    
}
