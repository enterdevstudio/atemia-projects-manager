package fr.xinta.atemia.web;

import fr.xinta.atemia.db.entity.Person;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EditPerson", urlPatterns = {"/editPerson"})
public class EditPerson extends AbstractServlet {

    @Override
    protected String INITIAL_VIEW() {
	return VIEWS_PATH + "editPerson.jsp";
    }

    @Override
    protected String EXECUTED_VIEW() {
	return VIEWS_PATH + "displayPerson.jsp";
    }

    @Override
    protected void initialRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	
	// For fill the fiel with the current value
	String id = request.getParameter("person-id");
	Person person = personFacade.find(id);
	
	if (person != null) {
	    request.setAttribute("person", person);
	} else {
	    request.setAttribute("message", "No person has the id " + id + ". Aborting.");
	}
	
	request.getRequestDispatcher(INITIAL_VIEW()).forward(request, response);
    }

    @Override
    protected void executeRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	
	String id = request.getParameter("person-id");
	Person person = personFacade.find(id);
	
	if (person != null) {
            person.setFirstName(request.getParameter("firstName"));
            person.setLastName(request.getParameter("lastName"));
            //TODO need to update ?
            personFacade.merge(person);
            request.setAttribute("person", person);
	} else {
	    request.setAttribute("message", "No person has the id " + id + ". Aborting.");
	}
	
	request.getRequestDispatcher(EXECUTED_VIEW()).forward(request, response);
    }
    
}
