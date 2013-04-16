package fr.xinta.atemia.web;

import fr.xinta.atemia.db.entity.Person;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ListPerson", urlPatterns = {"/listPerson"})
public class ListPerson extends AbstractServlet {

    @Override
    protected String INITIAL_VIEW() {
	return VIEWS_PATH + "listPerson.jsp";
    }

    @Override
    protected String EXECUTED_VIEW() {
	return INITIAL_VIEW();
    }

    @Override
    protected void initialRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	request.setAttribute("persons", personFacade.findAll());
	request.getRequestDispatcher(INITIAL_VIEW()).forward(request, response);
    }

    @Override
    protected void executeRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	initialRequest(request, response);
    }    
}
