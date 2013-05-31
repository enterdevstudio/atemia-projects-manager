package fr.xinta.atemia.web;

import fr.xinta.atemia.db.entity.Person;
import fr.xinta.atemia.db.entity.Week;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ManageConges", urlPatterns = {"/manageConges"})
public class ManageConges extends AbstractServlet {

    @Override
    protected String INITIAL_VIEW() {
        return VIEWS_PATH + "manageConges.jsp";
    }

    @Override
    protected String EXECUTED_VIEW() {
        return "displayPerson";
    }

    @Override
    protected void initialRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Person person = personFacade.find(request.getParameter("person-id"));
            if (person == null)
                    throw new Exception("No person has this id. Aborting.");
            
            request.setAttribute("person", person);
            String weekParam = request.getParameter("week");
            
            if (request.getParameter("setDays") != null) {
                try {
                    Week week = new Week(weekParam);
                    float conges = Float.parseFloat(request.getParameter("nbDays"));
                    if (conges < 0 || conges > 5)
                        throw new Exception("Nb days must be between 0 and 5");
                    float workedDay = person.getNbDaysAffected(week).getNbDaysWork();
                    if (workedDay + conges > 7)
                        throw new Exception("Worked days (" + workedDay + ") + unavailable days (" + conges + ") must be lesser than 7");
                    if (workedDay + conges > 5)
                        request.setAttribute("warning_notification", "With worked days (" + workedDay + "), you add more than 5 days on the week " + week + " !");
                    
                    person.getConges().put(week.toString(), conges);
                    personFacade.merge(person);
                } catch (Exception e) {
                    request.setAttribute("error_notification", e.getMessage());
                }
            } else {
                request.setAttribute("week", weekParam);
            }
            
            request.getRequestDispatcher(INITIAL_VIEW()).forward(request, response);        
        } catch (Exception e) {
            request.setAttribute("error_notification", e.getMessage());
            request.getRequestDispatcher("index").forward(request, response);
        }
    }

    @Override
    protected void executeRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setAttribute("person", personFacade.find(request.getParameter("person-id")));        
        request.getRequestDispatcher(EXECUTED_VIEW()).forward(request, response);  
    }    
}
