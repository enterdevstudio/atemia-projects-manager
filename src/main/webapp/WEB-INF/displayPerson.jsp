<%@ include file="header.jsp" %>
	    
	    <header>
		<form method="post">
		    <input type="submit" name="update" value="Update" />
		    <input type="submit" name="delete" value="Delete" />
		</form>
		<h1>${person.firstName} ${person.lastName}</h1>
	    </header>

	    <p>This person participates to the following projects:</p>
	    <table>
		<tr>
		    <th>ID</th>
		    <th>Name</th>
		    <th>Department</th>
		</tr>
		<c:forEach var="project" items="${person.projects}" >
		<tr>
		    <td><a href="displayProject?project-id=${project.projectId}">${project.projectId}</a></td>
		    <td>${project.name}</td>
		    <td>${project.department}</td>
		</tr>
		</c:forEach>
	    </table>
	    
<%@ include file="footer.jsp" %>
