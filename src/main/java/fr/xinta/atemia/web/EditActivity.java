package fr.xinta.atemia.web;

import fr.xinta.atemia.db.entity.Activity;
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
	
        try {
            String projectId = request.getParameter("project-id");
            String activityId = request.getParameter("activity-id");
            Activity activity = activityFacade.find(activityId);
            Project project = projectFacade.find(projectId);
            if (project == null)
                    throw new Exception("No project has this id. Aborting.");        
            if (activity == null)
                    throw new Exception("No activity has this id. Aborting.");
            
            request.setAttribute("activity", activity);
            request.setAttribute("project-id", projectId);
            request.getRequestDispatcher(INITIAL_VIEW()).forward(request, response);
        
        } catch (NumberFormatException e) {
            request.setAttribute("error_notification", "week or year is not a valid number");
            request.getRequestDispatcher(EXECUTED_VIEW()).forward(request, response);

        } catch (Exception e) {
            request.setAttribute("error_notification", e.getMessage());
            request.getRequestDispatcher(EXECUTED_VIEW()).forward(request, response);
        }
    }

    @Override
    protected void executeRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           
        try {   
            String projectId = request.getParameter("project-id");
            String activityId = request.getParameter("activity-id");
            Project project = projectFacade.find(projectId);
            Activity activity = activityFacade.find(activityId);
            if (project == null)
                    throw new Exception("No project has this id. Aborting.");
            if (activity == null)
                    throw new Exception("No activity has this id. Aborting.");
	    
            Week week = activity.getWeek();
            float nbDaysAff = activity.getWorker().getNbDaysAffected(week).getNbDaysWork()
                    + activity.getWorker().getNbDaysConges(week.toString()) - activity.getNbDaysWork();
            // Update of the activity
            float prod = Float.parseFloat(request.getParameter("production"));
            float terr = Float.parseFloat(request.getParameter("terrain"));
            float copil = Float.parseFloat(request.getParameter("copil"));

            float sum = prod + terr + copil + nbDaysAff;
            if (sum >= 0 && sum <= 7) {

                if (sum > 5)
                    request.setAttribute("warning_notification", "you added more than 5 days on the week " + week + " !");
                activity.setProduction(prod);
                activity.setTerrain(terr);
                activity.setCopil(copil);
                activityFacade.merge(activity);

                request.getRequestDispatcher(EXECUTED_VIEW()).forward(request, response);

            } else {
                request.setAttribute("error_notification", "Impossible to add these days, your input plus " +
                         nbDaysAff + " days in other projects is not between 0 and 7 days per week");
                initialRequest(request, response);
            }                    
        } catch (NumberFormatException e) {
            request.setAttribute("error_notification", "This is not a number.");
            initialRequest(request, response);
        } catch (Exception e) {
	    request.setAttribute("error_notification", e.getMessage());
            request.getRequestDispatcher(EXECUTED_VIEW()).forward(request, response);
	}	
    }    
}
