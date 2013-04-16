package fr.xinta.atemia.web;

import fr.xinta.atemia.db.entity.Person;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DeletePerson", urlPatterns = {"/deletePerson"})
public class DeletePerson extends AbstractServlet {

    @Override
    protected String INITIAL_VIEW() {
	return VIEWS_PATH + "displayPerson.jsp";
    }

    @Override
    protected String EXECUTED_VIEW() {
	return VIEWS_PATH + "index.jsp";        
    }

    @Override
    protected void initialRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
	String id = request.getParameter("person-id");
	Person person = personFacade.find(id);
	
	if (person != null) {
	    request.setAttribute("person", person);
	    request.setAttribute("id", person.getId());
	    request.setAttribute("messageconfirm", "You will delete this person. Are you sure?");
	} else {
	    request.setAttribute("message", "No person has the id " + id + ". Aborting.");
	}
	
	request.getRequestDispatcher(INITIAL_VIEW()).forward(request, response);
    }

    @Override
    protected void executeRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // If the person is not found or removed, so by default,
        // we display the list of persons
        String view = VIEWS_PATH;
        
        String id = request.getParameter("id");
        Person person = personFacade.find(id);

        if (person != null) {
            if (request.getParameter("yes") != null) {                
                //TODO suppress projet and everything linked with this person
                personFacade.remove(person);
                
                view += "listPerson.jsp";
                request.setAttribute("message",
                        person.getFirstName() + " " + person.getLastName() + 
                        " has been removed.");
                request.setAttribute("persons", personFacade.findAll());                
            } else {                
                view += "displayPerson.jsp";
                request.setAttribute("person", person);
            }
        } else {            
            request.setAttribute("persons", personFacade.findAll());
            request.setAttribute("message", "No person has the id " + id + ". Aborting.");
        }
	
	request.getRequestDispatcher(view).forward(request, response);
    }    
}
