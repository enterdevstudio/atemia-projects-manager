<%@ include file="header.jsp" %>
	    
	    <header>
		<h1>Projects in the application</h1>
	    </header>


	    <c:choose>
		<c:when test="${empty projects}">
		    <p>There is no projects in the application for the moment.</p>
		</c:when>
		<c:otherwise>
		    <table>
			<tr>
			    <th>ID</th>
			    <th>Name</th>
			    <th>Department</th>
			    <th>Number of hours sold</th>
			</tr>
			<c:forEach var="project" items="${projects}">
			<tr>
			    <td><a href="displayProject?project-id=${project.projectId}">
				    <c:out value="${project.projectId}" />
				</a></td>
			    <td><c:out value="${project.name}" /></td>
			    <td><c:out value="${project.department}" /></td>
			    <td><c:out value="${project.nbHoursSold}" /></td>
			</tr>
			</c:forEach>			    
		    </table>
		</c:otherwise>
	    </c:choose>
	    
<%@ include file="footer.jsp" %>