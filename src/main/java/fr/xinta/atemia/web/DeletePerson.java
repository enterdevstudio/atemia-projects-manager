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
        
        if (request.getParameter("yes") != null) {
            String id = request.getParameter("id");
            System.out.println("id " + request.getParameter("id"));
            Person person = personFacade.find(id);

            if (person != null) {
                personFacade.remove(person);
            } else {
                request.setAttribute("message", "No person has the id " + id + ". Aborting.");
            }
        }
	
	request.getRequestDispatcher(EXECUTED_VIEW()).forward(request, response);
    }    
}
