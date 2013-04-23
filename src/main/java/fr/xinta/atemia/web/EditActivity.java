package fr.xinta.atemia.web;

import fr.xinta.atemia.db.entity.Activity;
import fr.xinta.atemia.db.entity.Project;
import fr.xinta.atemia.db.facade.ActivityFacade;
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
        return "displayProject";
    }

    @Override
    protected void initialRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
	String projectId = request.getParameter("project-id");
	String activityId = request.getParameter("activity-id");
	Activity activity = activityFacade.find(activityId);     
	
	if (activity != null) {
	    request.setAttribute("activity", activity);
	    request.setAttribute("project-id", projectId);
            request.getRequestDispatcher(INITIAL_VIEW()).forward(request, response);
	} else {
	    request.setAttribute("error_notification", "No activity has the id " + activityId + ". Aborting.");
            request.getRequestDispatcher(EXECUTED_VIEW() + "?project-id=" + projectId).forward(request, response);
	}	
    }

    @Override
    protected void executeRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
              
	String projectId = request.getParameter("project-id");
	String activityId = request.getParameter("activity-id");  
	Project project = projectFacade.find(projectId);
	Activity activity = activityFacade.find(activityId);
	
	if (activity != null && project != null) {            
            try {
                // Update of the activity
                int prod = Integer.parseInt(request.getParameter("production"));
                int terr = Integer.parseInt(request.getParameter("terrain"));
                int copil = Integer.parseInt(request.getParameter("copil"));
                int conges = Integer.parseInt(request.getParameter("conges"));

                if (prod + terr + copil + conges == 5) {

                    activity.setProduction(prod);
                    activity.setTerrain(terr);
                    activity.setCopil(copil);
                    activity.setConges(conges);
                    activityFacade.merge(activity);
                    
                    request.getRequestDispatcher(EXECUTED_VIEW()).forward(request, response);

                } else {
                    request.setAttribute("error_notification", "The number of days specified is incorrect. The sum has to be 5.");
                    initialRequest(request, response);
                }    
            } catch (NumberFormatException e) {
                request.setAttribute("error_notification", "This is not a number.");
                initialRequest(request, response);
            }
	} else {
	    request.setAttribute("error_notification", "No activity has the id " + activityId + ". Aborting.");
            request.getRequestDispatcher(EXECUTED_VIEW() + "?project-id=" + projectId).forward(request, response);
	}	
    }
    
}
