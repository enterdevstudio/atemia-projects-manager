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
		<c:forEach var="person" items="${project.workers}" >
		    <a href="#">${person.firstName} ${person.lastName}</a>
		</c:forEach></li>
	    </ul>
			
	    <%@ include file="footer.jsp" %>
