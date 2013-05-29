<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML>
<html lang="en-US">
    <head>
	<title>Atemia - Projects Manager</title>
	<meta charset="UTF-8" />	
	<meta name="content-type" content="text/html;" charset="utf-8" />
	<link rel="stylesheet" href="./design/style.css" />
	<link rel="stylesheet" href="./design/template.css" />
	<link rel="shortcut icon" type="image/x-icon" href="./design/favicon.ico" />
        
        <!-- jquery for the date picker -->
        <script type="text/javascript" src="./libs/jquery-2.0.0.min.js"></script>
        <script type="text/javascript" src="./libs/jquery-ui-modified.js"></script>
        <script type="text/javascript" src="./libs/jquery.jqplot.min.js"></script>
        <script type="text/javascript" src="./libs/jqplot.pieRenderer.min.js"></script>
        <link rel="stylesheet" href="./libs/jquery-ui.css" />
        <link rel="stylesheet" type="text/css" href="./libs/jquery.jqplot.css" />
    </head>
    <body>		
	<section id="panel">
	    <header>
		<a href="http://atemia.org/" target="_blank">
		    <img id="logo" src="./design/logo.png" alt="logo" title="logo" />
		</a>
		<h1><a href="index">Projects Manager</a></h1>
	    </header>

	    <menu id="menu" type="toolbar">
	    <li>
		<h2>Persons</h2>
		<menu class="submenu">
		    <li><a href="createPerson">Create</a></li>
		    <li><a href="listPerson">List</a></li>
		</menu>
	    </li>
	    <li>
		<h2>Projects</h2>
		<menu class="submenu">
		    <li><a href="createProject">Create</a></li>
		    <li><a href="listProject">List</a></li>
		</menu>
	    </li>
	    </menu>

	    <footer>
                <a href="changelog">v1.8</a><br />
		Application developed by <a href="http://www.xinta.fr">Xinta</a><br />
		Source code available <a href="https://github.com/Flaburgan/atemia-projects-manager">on Github</a>
	    </footer>
	</section>

	<section id="content">
	    <c:if test="${not empty error_notification}">
                <p class="error">Error: ${error_notification}</p>
	    </c:if>
                
	    <c:if test="${not empty warning_notification}">
                <p class="warning">Warning: ${warning_notification}</p>
	    </c:if>
                
	    <c:if test="${not empty info_notification}">
                <p class="info">${info_notification}</p>
	    </c:if>
                
            <c:if test="${not empty confirmation_message}">
                <div class="box">
                    <div class="message-confirm">
                        <p>${confirmation_message}</p>
                        <form method="post">
                            <input type="hidden" name="id" value="${id}" />
                            <input type="hidden" name="execute" value="ok" />
                            <input type="submit" name="yes" value="Yes" />
                            <input type="submit" name="no" value="No" />
                        </form>
                    </div>
                </div>
	    </c:if>
