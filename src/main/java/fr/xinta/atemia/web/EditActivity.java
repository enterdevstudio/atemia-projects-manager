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

@WebServlet(name = "EditActivity", urlPatterns = {"/editActivity"})
public class EditActivity extends AbstractServlet {

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
        Project project = projectFacade.find(projectId);
	
	if (activity == null) {
            int num = Integer.parseInt(request.getParameter("week"));
            int year = Integer.parseInt(request.getParameter("year"));
            Person worker = personFacade.find(request.getParameter("person-id"));
            
            activity = new Activity();
            activity.setWeek(new Week(num, year));
            activity.setProject(project);
            project.getActivities().add(activity);
            activity.setWorker(worker);
            worker.getActivities().add(activity);
            
            activityFacade.persist(activity);
            projectFacade.merge(project);
            personFacade.merge(worker);
	}
        request.setAttribute("activity", activity);
        request.setAttribute("project-id", projectId);
        request.getRequestDispatcher(INITIAL_VIEW()).forward(request, response);
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
                
                if (prod + terr + copil + conges <= 5) {

                    activity.setProduction(prod);
                    activity.setTerrain(terr);
                    activity.setCopil(copil);
                    activity.setConges(conges);
                    activityFacade.merge(activity);
                    
                    request.getRequestDispatcher(EXECUTED_VIEW()).forward(request, response);

                } else {
                    request.setAttribute("error_notification", "The number of days specified is incorrect. The sum has to be <= than 5.");
                    initialRequest(request, response);
                } 
                    
            } catch (NumberFormatException e) {
                request.setAttribute("error_notification", "This is not a number.");
                initialRequest(request, response);
            }
	} else {
	    request.setAttribute("error_notification", "No activity has the id " + activityId + ". Aborting.");
            request.getRequestDispatcher(EXECUTED_VIEW()).forward(request, response);
	}	
    }
    
}
