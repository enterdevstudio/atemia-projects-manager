	    <%@ include file="header.jsp" %>

		<header>
		    <h1>Project</h1>
		</header>
		<form method="post">
		    <p><label for="input-number">Name :</label></p>
		    <p>
			<input type="text" name="name" value="${project.name}" id="input-name" required autofocus />
		    </p>

		    <p><label for="input-department">Departement :</label></p>
		    <p>
			<input type="text" name="department" value="${project.department}" id="input-department" required />
		    </p>

		    <p><label for="input-nbDaysSold">Number of days sold :</label></p>
		    <p>
			<input type="text" name="nbDaysSold" value="${project.nbDaysSold}" id="input-nbDaysSold" required />
		    </p>

		    <p>
			<input type="submit" name="execute" value="Validate" />
		    </p>
		</form>
			
	    <%@ include file="footer.jsp" %>
