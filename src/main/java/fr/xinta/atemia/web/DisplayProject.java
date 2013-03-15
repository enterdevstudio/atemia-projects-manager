package fr.xinta.atemia.web;

import fr.xinta.atemia.db.entity.Period;
import fr.xinta.atemia.db.entity.Person;
import fr.xinta.atemia.db.entity.Project;
import fr.xinta.atemia.db.entity.Week;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DisplayProject", urlPatterns = {"/displayProject"})
public class DisplayProject extends AbstractServlet {

    @Override
    protected String INITIAL_VIEW() {
	return VIEWS_PATH + "displayProject.jsp";
    }

    @Override
    protected String EXECUTED_VIEW() {
	return INITIAL_VIEW();
    }

    @Override
    protected void initialRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	
	String id = request.getParameter("project-id");
	//Project project = projectFacade.find(id);
	Project project = new Project();	
	Week week = new Week();
	Week week2 = new Week();
	week.setNumber(11);
	week2.setNumber(12);
	HashMap<Person, Period> job1 = week.getJob();
	HashMap<Person, Period> job2 = week2.getJob();
	project.getWeeks().add(week);
	project.getWeeks().add(week2);	
	
	Person p = new Person();
	p.setFirstName("Raphael");
	p.setLastName("Bouju");
	job1.put(p, Period.COPIL);
	job2.put(p, Period.PRODUCTION);
	
	Person p2 = new Person();
	p2.setFirstName("Antoine");
	p2.setLastName("Dup");	
	job1.put(p2, Period.PRODUCTION);		
	job2.put(p2, Period.TERRAIN);
	
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
	
	initialRequest(request, response);
    }     
}
