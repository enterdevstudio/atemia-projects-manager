package fr.xinta.atemia.web;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractServlet extends HttpServlet {

    public static final String VIEWS_PATH = "/WEB-INF/";

    //Méthode car on ne peut pas mettre d'attributs en abstract  
    /**
     * Représente la vue affichée avant execution de la requête (En règle
     * générale, un formulaire)
     *
     * @return le path de la vue (de la forme "/WEB-INF/maVue.jsp")
     */
    protected abstract String INITIAL_VIEW();
    
    //Méthode car on ne peut pas mettre d'attributs en abstract    
    /**
     * Représente la vue affichée après execution de la requête (En règle
     * générale, l'affichage de l'objet créé / édité / supprimé)
     *
     * @return le path de la vue (de la forme "/WEB-INF/maVue.jsp")
     */
    protected abstract String EXECUTED_VIEW();

    /**
     * Méthode exécutée si on a pas validé de requête. Traitement à faire :
     * préremplir le formulaire, etc...
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected abstract void initialRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException;

    /**
     * Méthode exécuté à la validation de la requête Il faut vérifier les champs
     * remplis puis effectuer le traitement correspondant (créer, éditer,
     * supprimer..)
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected abstract void executeRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException;
    
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
	
	try {
	    if (request.getParameter("execute") != null) {
		
		//On a validé une requête à executer
		executeRequest(request, response);		
	    } else {
		
		//On a pas encore validé de requête
		initialRequest(request, response);
	    }
	} catch (ServletException ex) {
	    Logger.getLogger(AbstractServlet.class.getName()).log(Level.SEVERE, null, ex);
	} catch (IOException ex) {
	    Logger.getLogger(AbstractServlet.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	processRequest(request, response);
    }
}
