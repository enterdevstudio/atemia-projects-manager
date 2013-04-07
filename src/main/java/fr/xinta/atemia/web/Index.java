package fr.xinta.atemia.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Index", urlPatterns = {"/index"})
public class Index extends AbstractServlet {

    @Override
    protected String INITIAL_VIEW() {
	return VIEWS_PATH + "index.jsp";
    }

    @Override
    protected String EXECUTED_VIEW() {
	return INITIAL_VIEW();
    }

    @Override
    protected void initialRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	
        request.setAttribute("nbPersons", personFacade.count());
        request.setAttribute("nbProjects", projectFacade.count());
        //TODO calcul charge de la semaine 
        //Nombre de semaines affectée dans l'appli /
        //nombre de semaine travaillées par an x nb personne
        request.setAttribute("ratioWeeks", "x (y%)");
        
	request.getRequestDispatcher(INITIAL_VIEW()).forward(request, response);
    }

    @Override
    protected void executeRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	
	initialRequest(request, response);
    }
    
}
