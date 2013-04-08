	    <%@ include file="header.jsp" %>

		<header>
		    <h1>Project</h1>
		</header>
		<form method="post">
		    <p><label for="input-number">Name:</label></p>
		    <p>
			<input type="text" name="name" value="${project.name}" id="input-name" required autofocus />
		    </p>

		    <p><label for="input-department">Departement:</label></p>
		    <p>
			<input type="text" name="department" value="${project.department}" id="input-department" required />
		    </p>

		    <p><label for="input-nbDaysSold">Number of days sold:</label></p>
		    <p>
			<input type="text" name="nbDaysSold" value="${project.nbDaysSold}" id="input-nbDaysSold" required />
		    </p>
                    
                    <p>Start week:</p>
		    <p>
                        <label for="input-startWeek">Week Number:</label>
			<input type="week" name="startWeek" value="${project.startWeek}" id="input-startWeek" required />
                        <label for="input-startYear">Year:</label>
			<input type="number" name="startYear" value="${project.startYear}" id="input-startYear" required />
		    </p>
                    
                    <p>End week:</p>
		    <p>
                        <label for="input-endWeek">Week Number:</label>
			<input type="week" name="endWeek" value="${project.endWeek}" id="input-endWeek" required />
                        <label for="input-endYear">Year:</label>
			<input type="number" name="endYear" value="${project.endYear}" id="input-endYear" required />
		    </p>

		    <p>
			<input type="submit" name="execute" value="Validate" />
		    </p>
		</form>
			
	    <%@ include file="footer.jsp" %>
