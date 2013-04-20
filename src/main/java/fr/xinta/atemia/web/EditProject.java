package fr.xinta.atemia.web;

import fr.xinta.atemia.db.entity.Project;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EditProject", urlPatterns = {"/editProject"})
public class EditProject extends AbstractServlet {

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
	
	// For fill the fiel with the current value
	String id = request.getParameter("project-id");
	Project project = projectFacade.find(id);
	
	if (project != null) {
	    request.setAttribute("project", project);
	} else {
	    request.setAttribute("message", "No project has the id " + id + ". Aborting.");
	}
	
	request.getRequestDispatcher(INITIAL_VIEW()).forward(request, response);
    }

    @Override
    protected void executeRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	
	String id = request.getParameter("project-id");
	Project project = projectFacade.find(id);
	
	if (project != null) {
            project.setName(request.getParameter("name"));
            project.setDepartment(request.getParameter("department"));
            project.setNbDaysSold(Integer.parseInt(request.getParameter("nbDaysSold")));
            
            String sw = request.getParameter("startWeek");
            String ew = request.getParameter("endWeek");
            int startWeek = Integer.parseInt(sw.substring(6, 8));
            int startYear = Integer.parseInt(sw.substring(0, 4));
            int endWeek = Integer.parseInt(ew.substring(6, 8));
            int endYear = Integer.parseInt(ew.substring(0, 4));
            
            if (project.getStartWeek() != startWeek ||
                    project.getStartYear() != startYear) {
                project.updateStartWeek(startWeek, startYear);
            }
            
            if (project.getEndWeek() != endWeek ||
                    project.getEndYear() != endYear) {
                project.updateEndWeek(endWeek, endYear);
            }
            
            projectFacade.merge(project);
	    request.setAttribute("project", project);
	    request.setAttribute("persons", personFacade.findAll());
	} else {
	    request.setAttribute("message", "No project has the id " + id + ". Aborting.");
	}
	
	request.getRequestDispatcher(EXECUTED_VIEW()).forward(request, response);
    }    
}
