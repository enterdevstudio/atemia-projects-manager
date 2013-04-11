<%@ include file="header.jsp" %>
	    
	    <header>
		<form method="post" action="deletePerson">
		    <input type="hidden" name="person-id" value="${person.id}" />
		    <input type="submit" name="delete" value="Delete" />
		</form>
		<form method="post" action="editPerson">
		    <input type="hidden" name="person-id" value="${person.id}" />
		    <input type="submit" name="update" value="Update" />
		</form>
		<h1>${person.firstName} ${person.lastName}</h1>
	    </header>

	    <c:choose>
		<c:when test="${empty person.projects}">
		    <p>This person does not participate to a project for the moment.</p>
		</c:when>
		<c:otherwise>
		    <p>This person participates to the following projects:</p>
		    <table>
			<tr>
			    <th>ID</th>
			    <th>Name</th>
			    <th>Department</th>
			</tr>
			<c:forEach var="project" items="${person.projects}" >
			<tr>
			    <td><a href="displayProject?project-id=${project.id}">${project.id}</a></td>
			    <td>${project.name}</td>
			    <td>${project.department}</td>
			</tr>
			</c:forEach>
		    </table>
		</c:otherwise>
	    </c:choose>
	    
<%@ include file="footer.jsp" %>
