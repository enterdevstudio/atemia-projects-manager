<%@ include file="header.jsp" %>
	    
	    <header>
		<h1>Projects in the application</h1>
	    </header>

	    <c:choose>
		<c:when test="${empty projects}">
                    <p>There is no projects in the application for the moment. <a href="createProject">Create one.</a></p>
		</c:when>
		<c:otherwise>
		    <table>
			<tr>
			    <th>Name</th>
			    <th>Department</th>
			    <th>Number of days affected</th>
			    <th>Number of days sold</th>
			</tr>
			<c:forEach var="project" items="${projects}">
			<tr>
			    <td>
				<a href="displayProject?project-id=${project.id}">
				    <c:out value="${project.name}" />
				</a>
			    </td>
			    <td><c:out value="${project.department}" /></td>
			    <td><c:out value="${project.nbDaysAffected}" /></td>
			    <td><c:out value="${project.nbDaysSold}" /></td>
			</tr>
			</c:forEach>			    
		    </table>
		</c:otherwise>
	    </c:choose>
	    
<%@ include file="footer.jsp" %>