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
                
                <p>These days are dispatched between:</p>
		<p>
                    <label for="input-production">Production:</label>
		    <input type="number" name="production" value="${person.production}" id="input-production"
                           min="0" max="100" required />%
                </p>
                <p>
                    <label for="input-prospection">Prospection:</label>
		    <input type="number" name="prospection" value="${person.prospection}" id="input-prospection"
                           min="0" max="100" required />%
                </p>
                <p>                    
                    <label for="input-gestion">Gestion:</label>
		    <input type="number" name="gestion" value="${person.gestion}" id="input-gestion"
                           min="0" max="100" required />%
                </p>
                <p>   
                    <label for="input-communication">Communication:</label>
		    <input type="number" name="communication" value="${person.communication}" id="input-communication"
                           min="0" max="100" required />%                    
		</p>
                
		<p>
		    <input type="submit" name="execute" value="Validate" />
		</p>
	    </form>
		    
<%@ include file="footer.jsp" %>
