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
            request.setAttribute("person", person);
                
            try {
                person.setNbDaysAvailable(Integer.parseInt(request.getParameter("nbDaysAvailable")));
                if (person.getNbDaysAvailable() < 0) {
                    throw new NumberFormatException("Nb days available must be a positive number.");
                } else if (person.getNbDaysAvailable() > 365) {
                    throw new NumberFormatException("Nb days available is greater than 365!");
                }
                
                person.setProduction(Integer.parseInt(request.getParameter("production")));
                person.setProspection(Integer.parseInt(request.getParameter("prospection")));
                person.setGestion(Integer.parseInt(request.getParameter("gestion")));
                person.setCommunication(Integer.parseInt(request.getParameter("communication")));
                if (person.getProduction() < 0 || person.getProduction() > 100 ||
                        person.getProspection()< 0 || person.getProspection()> 100 ||
                        person.getGestion()< 0 || person.getGestion()> 100 ||
                        person.getCommunication() < 0 || person.getCommunication() > 100) {
                    throw new NumberFormatException("Percentage must be between 0 and 100.");
                }
                
                if (person.getProduction() + person.getProspection()+ person.getGestion() + person.getCommunication() != 100) {
                    throw new NumberFormatException("Sum of percentages must be egal to 100.");
                }
                
                personFacade.merge(person);
                
                request.setAttribute("info_notification", person.getFirstName() + " " + person.getLastName() + " has been updated.");
                request.getRequestDispatcher(EXECUTED_VIEW()).forward(request, response);
                
            } catch (Exception e) {
                request.setAttribute("error_notification", e.getMessage());
                initialRequest(request, response);
            }      
	} else {
	    request.setAttribute("error_notification", "No person has the id " + id + ". Aborting.");
            request.getRequestDispatcher("listPerson").forward(request, response);
	}
    }
    
}
