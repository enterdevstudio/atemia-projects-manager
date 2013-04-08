package fr.xinta.atemia.web;

import fr.xinta.atemia.db.entity.Project;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CreateProject", urlPatterns = {"/createProject"})
public class CreateProject extends AbstractServlet {

    @Override
    protected String INITIAL_VIEW() {
	return VIEWS_PATH + "editProject.jsp";
    }

    @Override
    protected String EXECUTED_VIEW() {
	return VIEWS_PATH + "displayProject.jsp";
    }

    @Override
    protected void initialRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	
	request.getRequestDispatcher(INITIAL_VIEW()).forward(request, response);
    }

    @Override
    protected void executeRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	
	Project project = new Project();
	project.setName(request.getParameter("name"));
	project.setDepartment(request.getParameter("department"));
	project.setNbDaysSold(Integer.parseInt(request.getParameter("nbDaysSold")));
	project.initWeeks(
                Integer.parseInt(request.getParameter("startWeek")),
                Integer.parseInt(request.getParameter("startYear")),
                Integer.parseInt(request.getParameter("endWeek")),
                Integer.parseInt(request.getParameter("endYear")));
	projectFacade.persist(project);
	
	request.setAttribute("project", project);
	request.getRequestDispatcher(EXECUTED_VIEW()).forward(request, response);
    }    
}
