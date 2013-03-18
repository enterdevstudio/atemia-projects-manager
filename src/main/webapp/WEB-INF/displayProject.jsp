	    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp" %>
	    
	    <header>
		<form method="post" action="editProject">
		    <input type="hidden" name="project-id" value="${project.id}" />
		    <input type="submit" name="update" value="Update" />
		    <input type="submit" name="delete" value="Delete" />
		</form>
		<h1>Project</h1>
	    </header>
	    <ul>
		<li><strong>Number :</strong> ${project.id}</li>
		<li><strong>Name :</strong> ${project.name}</li>
		<li><strong>Department :</strong> ${project.department}</li>
		<li><strong>Nb of hours sold :</strong> ${project.nbHoursSold}</li>
		<li><strong>Workers :</strong>
	    <c:choose>
		<c:when test="${empty project.workers}">
		    This project has no workers for the moment.
		</c:when>
		<c:otherwise>
		<c:forEach var="person" items="${project.workers}">
		    <a href="displayPerson?person-id=${person.id}">${person.firstName} ${person.lastName}</a>
		</c:forEach>
		</li>
		</c:otherwise>
	    </c:choose>
	    </ul>
		
	    <form method="post" action="addWorkerToProject">
		<input type="hidden" name="project-id" value="${project.id}" />
		<input type="submit" name="addWorker" value="Add a worker" />
	    </form>
		
	    <table>
		<tr>
		    <th>Week</th>
		<c:forEach var="person" items="${project.workers}">
		    <th>${person.firstName} ${person.lastName}</th>
		</c:forEach>
		</tr>
	    <c:forEach var="week" items="${project.weeks}">
		<tr>
		    <td>${week.value.number}</td>			
		<c:forEach var="job" items="${week.value.job}">
		    <td>${job.value}</td>
		</c:forEach>
		</tr>
	    </c:forEach>
	    </table>
			
	    <%@ include file="footer.jsp" %>
