	    <%@ include file="header.jsp" %>
	    
	    <form method="post">
		<fieldset>		    
		    <p><label for="input-nom">Nom :</label></p>
		    <p>
			<input type="text" name="nom" value="${client.nom}" id="input-nom" required autofocus />
		    </p>

		    <p><label for="input-prenom">Pr�nom :</label></p>
		    <p>
			<input type="text" name="prenom" value="${client.prenom}" id="input-prenom" required />
		    </p>
		</fieldset>
	    </form>
		    
	    <%@ include file="footer.jsp" %>
