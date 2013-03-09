	    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp" %>
	    
	    <header>
		<form method="post">
		    <input type="submit" name="update" value="Update" />
		    <input type="submit" name="delete" value="Delete" />
		</form>
		<h1>Project</h1>
	    </header>
	    <ul>
		<li><strong>Number :</strong> ${project.projectId}</li>
		<li><strong>Name :</strong> ${project.name}</li>
		<li><strong>Department :</strong> ${project.department}</li>
		<li><strong>Nb of hours sold :</strong> ${project.nbHoursSold}</li>
		<li><strong>Workers :</strong>
	    <c:choose>
		<c:when test="${empty person.projects}">
		    This project has no workers for the moment.
		</c:when>
		<c:otherwise>
		<c:forEach var="person" items="${project.workers}">
		    <a href="displayPerson?personId=${person.id}">${person.firstName} ${person.lastName} </a>
		</c:forEach>
		</li>
		</c:otherwise>
	    </c:choose>
	    </ul>
			
	    <%@ include file="footer.jsp" %>
