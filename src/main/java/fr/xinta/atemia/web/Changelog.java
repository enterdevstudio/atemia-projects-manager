package fr.xinta.atemia.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Changelog", urlPatterns = {"/changelog"})
public class Changelog extends AbstractServlet {

    @Override
    protected String INITIAL_VIEW() { return VIEWS_PATH + "changelog.jsp"; }

    @Override
    protected String EXECUTED_VIEW() { return INITIAL_VIEW(); }

    @Override
    protected void initialRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(INITIAL_VIEW()).forward(request, response);
    }

    @Override
    protected void executeRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        initialRequest(request, response);
    }
    
}
