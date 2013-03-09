<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html lang="en-US">
    <head>
	<title>Atemia - Projects Manager</title>
	<meta charset="UTF-8" />	
	<meta name="content-type" content="text/html;" charset="utf-8" />
	<link rel="stylesheet" href="./design/style.css" />
	<link rel="stylesheet" href="./design/template.css" />
	<link rel="shortcut icon" type="image/x-icon" href="./design/favicon.ico" />
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
		Application developed by <a href="http://www.xinta.fr">Xinta</a><br />
		Source code available <a href="https://github.com/Flaburgan/atemia-projects-manager">on Github</a>
	    </footer>
	</section>

	<section id="content">
