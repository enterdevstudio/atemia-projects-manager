	    <%@ include file="header.jsp" %>
	    
		<header>
			<form method="post">
				<input type="submit" name="update" value="Update" />
				<input type="submit" name="delete" value="Delete" />
			</form>
			<h1>${person.firstName} ${person.lastName}</h1>
		</header>
	    
	    <%@ include file="footer.jsp" %>
