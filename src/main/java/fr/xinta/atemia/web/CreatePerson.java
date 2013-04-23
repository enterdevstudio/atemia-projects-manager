package fr.xinta.atemia.web;

import fr.xinta.atemia.db.entity.Person;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CreatePerson", urlPatterns = {"/createPerson"})
public class CreatePerson extends AbstractServlet {

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
	
	request.getRequestDispatcher(INITIAL_VIEW()).forward(request, response);
    }

    @Override
    protected void executeRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	
        Person person = new Person();
        person.setFirstName(request.getParameter("firstName"));
        person.setLastName(request.getParameter("lastName"));
        request.setAttribute("person", person);
            
        try {
            person.setNbDaysAvailable(Integer.parseInt(request.getParameter("nbDaysAvailable")));
            if (person.getNbDaysAvailable() < 0) {
                throw new NumberFormatException();
            }
            
            personFacade.persist(person);
	
            request.setAttribute("info_notification", person.getFirstName() + " " + person.getLastName() + " has been created.");
            request.getRequestDispatcher(EXECUTED_VIEW()).forward(request, response);
            
        } catch (NumberFormatException e) {
            request.setAttribute("error_notification", "Nb days available must be a positive number");
            initialRequest(request, response);
            
        } catch (Exception e) {
            request.setAttribute("error_notification", e.getMessage());
            initialRequest(request, response);
        }
    }
    
}
