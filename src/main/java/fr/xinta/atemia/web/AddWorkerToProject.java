package fr.xinta.atemia.web;

import fr.xinta.atemia.db.entity.Person;
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
	
	String id = request.getParameter("project-id");
	Project project = projectFacade.find(id);
	
	if (project != null) {
            String workerId = request.getParameter("person-id").split(" ")[0];
            Person worker = personFacade.find(workerId);
            if (worker != null) {
                if (!project.getWorkers().contains(worker)) {
                    project.AddWorker(worker);
                    projectFacade.merge(project);
                } else {
                    request.setAttribute("error_notification", "This worker already works on this project! Aborting.");
                }
            } else {
                request.setAttribute("error_notification", "No person has the id " + workerId + ". Aborting.");
            }
            // Display the project
            request.getRequestDispatcher("displayProject?project-id=" + id).forward(request, response);
        } else {
	    request.setAttribute("error_notification", "No project has the id " + id + ". Aborting.");
            request.getRequestDispatcher("listProject").forward(request, response);
	}
    }
}
