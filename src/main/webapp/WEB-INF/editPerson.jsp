<%@ include file="header.jsp" %>
	    
	    <header>
		<h1>Person</h1>
	    </header>
	    <form method="post">
                <input type="hidden" name="person-id" value="${person.id}" />
		<p><label for="input-first-name">First name :</label></p>
		<p>
		    <input type="text" name="firstName" value="${person.firstName}" id="input-first-name" required autofocus />
		</p>

		<p><label for="input-last-name">Last name :</label></p>
		<p>
		    <input type="text" name="lastName" value="${person.lastName}" id="input-last-name" required />
		</p>

		<p><label for="input-nb-days">Nb days available per year :</label></p>
		<p>
		    <input type="number" name="nbDaysAvailable" value="${person.nbDaysAvailable}" id="input-nb-days" required />
		</p>
                
		<p>
		    <input type="submit" name="execute" value="Validate" />
		</p>
	    </form>
		    
<%@ include file="footer.jsp" %>
