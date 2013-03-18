package fr.xinta.atemia.web;

import fr.xinta.atemia.db.entity.Project;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AddWorkerToProject", urlPatterns = {"/addWorkerToProject"})
public class AddWorkerToProject extends AbstractServlet {

    @Override
    protected String INITIAL_VIEW() {
	return VIEWS_PATH + "addWorkerToProject.jsp";
    }

    @Override
    protected String EXECUTED_VIEW() {
	return VIEWS_PATH + "displayProject.jsp";
    }

    @Override
    protected void initialRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void executeRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	Project project = projectFacade.find(request.getParameter("project-id"));
	
	request.setAttribute("project", project);
	request.getRequestDispatcher(EXECUTED_VIEW()).forward(request, response);
    }
}
