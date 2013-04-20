package fr.xinta.atemia.web;

import fr.xinta.atemia.db.entity.Activity;
import fr.xinta.atemia.db.entity.Project;
import fr.xinta.atemia.db.facade.ActivityFacade;
import static fr.xinta.atemia.web.AbstractServlet.VIEWS_PATH;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EditActivity", urlPatterns = {"/editActivity"})
public class EditActivity extends AbstractServlet {
    
    @EJB
    ActivityFacade activityFacade;

    @Override
    protected String INITIAL_VIEW() {
        return VIEWS_PATH + "editActivity.jsp";
    }

    @Override
    protected String EXECUTED_VIEW() {
        return VIEWS_PATH + "displayProject.jsp";
    }

    @Override
    protected void initialRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
	String id = request.getParameter("activity-id");
	Activity activity = activityFacade.find(id);        
	
	if (activity != null) {            
	    request.setAttribute("activity", activity);
	    request.setAttribute("project-id", request.getParameter("project-id"));
	} else {
	    request.setAttribute("message", "No activity has the id " + id + ". Aborting.");
	}
	
	request.getRequestDispatcher(INITIAL_VIEW()).forward(request, response);
    }

    @Override
    protected void executeRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
              
	String projectId = request.getParameter("project-id");
	String activityId = request.getParameter("activity-id");  
	Project project = projectFacade.find(projectId);
	Activity activity = activityFacade.find(activityId);  
	
	if (activity != null) {
            // Update of the activity
            activity.setProduction(Integer.parseInt(request.getParameter("production")));
            activity.setTerrain(Integer.parseInt(request.getParameter("terrain")));
            activity.setCopil(Integer.parseInt(request.getParameter("copil")));
            activity.setConges(Integer.parseInt(request.getParameter("conges")));
            activityFacade.merge(activity);
            
            //Needed to display the view project
	    request.setAttribute("project", project);
	    request.setAttribute("persons", personFacade.findAll());
	} else {
	    request.setAttribute("message", "No activity has the id " + id + ". Aborting.");
	}
	
	request.getRequestDispatcher(EXECUTED_VIEW()).forward(request, response);
    }
    
}
