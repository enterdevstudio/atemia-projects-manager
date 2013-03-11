package fr.xinta.atemia.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ListProject", urlPatterns = {"/listProject"})
public class ListProject extends AbstractServlet {

    @Override
    protected String INITIAL_VIEW() {
	return VIEWS_PATH + "listProject.jsp";
    }

    @Override
    protected String EXECUTED_VIEW() {
	return INITIAL_VIEW();
    }

    @Override
    protected void initialRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
//	List<Project> projects = new ArrayList<Project>();
//	Project p = new Project();
//	p.setId(1l);
//	p.setName("Marseille 2013");
//	p.setDepartment("departement");
//	p.setNbHoursSold(1200);
//	projects.add(p);
//	
//	p = new Project();
//	p.setId(2l);
//	p.setName("Salon de l'aéronautique");
//	p.setDepartment("departement");
//	p.setNbHoursSold(800);
//	projects.add(p);
//	
//	request.setAttribute("projects", projects);
	
	request.setAttribute("projects", projectFacade.findAll());
	request.getRequestDispatcher(INITIAL_VIEW()).forward(request, response);
    }

    @Override
    protected void executeRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	initialRequest(request, response);
    }
}
