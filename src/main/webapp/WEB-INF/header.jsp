<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html lang="en-US">
    <head>
	<title>Atemia - Projects Manager</title>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="./design/style.css"/>
	<link rel="stylesheet" href="./design/template.css"/>
	<link rel="shortcut icon" type="image/x-icon" href="./design/favicon.ico" />
    </head>
    <body>
	<header>
	    <h1>Atemia Projects Manager</h1>
	</header>
	
	<menu id="menu" type="toolbar">
	    <li><h3>Persons</h3>
		<menu>
		    <li><a href="createPerson">Create</a></li>
		    <li><a href="rechercherClient">Rechercher</a></li>
		    <li><a href="afficherTousClients">Lister</a></li>
		</menu>
	    </li>
	    <li><h3>Projects</h3>
		<menu>
		    <li><a href="rechercherCompte">Rechercher</a></li>
		    <li><a href="afficherTousComptes">Lister</a></li>
		</menu>
	    </li>
	</menu>

	<section id="content">