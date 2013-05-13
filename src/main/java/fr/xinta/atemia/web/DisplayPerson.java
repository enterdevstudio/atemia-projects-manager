package fr.xinta.atemia.web;

import fr.xinta.atemia.db.entity.Activity;
import fr.xinta.atemia.db.entity.Person;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DisplayPerson", urlPatterns = {"/displayPerson"})
public class DisplayPerson extends AbstractServlet {

    @Override
    protected String INITIAL_VIEW() {
	return VIEWS_PATH + "displayPerson.jsp";
    }

    @Override
    protected String EXECUTED_VIEW() {
	return INITIAL_VIEW();
    }

    @Override
    protected void initialRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	
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
	
	initialRequest(request, response);
    }    
}
