package fr.xinta.atemia.web;

import fr.xinta.atemia.db.entity.Activity;
import fr.xinta.atemia.db.entity.Person;
import fr.xinta.atemia.db.entity.Project;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DeletePerson", urlPatterns = {"/deletePerson"})
public class DeletePerson extends AbstractServlet {

    @Override
    protected String INITIAL_VIEW() {
	return "displayPerson";
    }

    @Override
    protected String EXECUTED_VIEW() {
	return "listPerson";        
    }

    @Override
    protected void initialRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
	String id = request.getParameter("person-id");
	Person person = personFacade.find(id);
	
	if (person != null) {
	    request.setAttribute("id", id);
	    request.setAttribute("confirmation_message", "You will delete this person. Are you sure?");
            request.getRequestDispatcher(INITIAL_VIEW() + "?person-id=" + id).forward(request, response);
	} else {
	    request.setAttribute("error_notification", "No person has the id " + id + ". Aborting.");
            request.getRequestDispatcher(EXECUTED_VIEW()).forward(request, response);
	}	
    }

    @Override
    protected void executeRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                
        String id = request.getParameter("id");
        Person person = personFacade.find(id);

        if (person != null) {
            if (request.getParameter("yes") != null) {
                if (person.getManagedProjects().isEmpty()) {

                    for (Project p : person.getProjects()) {
                        p.removePerson(person);
                        projectFacade.merge(p);
                    }
                    for (Activity a : person.getActivities()) {
                        activityFacade.remove(a);
                    }
                    personFacade.remove(person);

                    request.setAttribute("info_notification",
                            person.getFirstName() + " " + person.getLastName() + " has been removed.");
                    request.getRequestDispatcher(EXECUTED_VIEW()).forward(request, response);
                } else {
                    request.setAttribute("error_notification",
                            "You can not delete a person who manage projects, change the manager by updating project first.");
                    request.getRequestDispatcher(INITIAL_VIEW() + "?person-id=" + id).forward(request, response);
                }
            } else {
                request.getRequestDispatcher(INITIAL_VIEW() + "?person-id=" + id).forward(request, response);
            }
        } else {
	    request.setAttribute("error_notification", "No person has the id " + id + ". Aborting.");
            request.getRequestDispatcher(EXECUTED_VIEW()).forward(request, response);
        }
    }    
}
