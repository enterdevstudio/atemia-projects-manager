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
            request.setAttribute("project", project);            
            
            try {
                project.setNbDaysSold(Integer.parseInt(request.getParameter("nbDaysSold")));
                if (project.getNbDaysSold() < 0) {
                    throw new NumberFormatException();
                }
                
                String sw = request.getParameter("startWeek");
                String ew = request.getParameter("endWeek");
                int startWeek = Integer.parseInt(sw.substring(6, 8));
                int startYear = Integer.parseInt(sw.substring(0, 4));
                int endWeek = Integer.parseInt(ew.substring(6, 8));
                int endYear = Integer.parseInt(ew.substring(0, 4));
                
                if (endYear < startYear || (endYear == startYear && endWeek < startWeek)) {
                    throw new Exception("Start week is after end week!");
                }
            
                if (project.getStartWeek() != startWeek ||
                        project.getStartYear() != startYear) {
                    project.updateStartWeek(startWeek, startYear);
                }

                if (project.getEndWeek() != endWeek ||
                        project.getEndYear() != endYear) {
                    project.updateEndWeek(endWeek, endYear);
                }

                projectFacade.merge(project);

                request.setAttribute("persons", personFacade.findAll());
                request.setAttribute("info_notification", "The project " + project.getName() + " has been updated.");
                request.getRequestDispatcher(EXECUTED_VIEW()).forward(request, response);
                
            } catch (NumberFormatException e) {
                request.setAttribute("error_notification",
                        "A field has an incorrect value. Please check if nbDaysSold is a positive number and if weeks has a good format.");
                initialRequest(request, response);
                
            } catch (StringIndexOutOfBoundsException e) {
                request.setAttribute("error_notification",
                        "Please check that start week and end week format is like 2012-W01");
                initialRequest(request, response);
                
            } catch (Exception e) {
                request.setAttribute("error_notification", e.getMessage());
                initialRequest(request, response);
            }
	} else {
	    request.setAttribute("error_notification", "No project has the id " + id + ". Aborting.");
            request.getRequestDispatcher("listProject").forward(request, response);
	}	
    }    
}
