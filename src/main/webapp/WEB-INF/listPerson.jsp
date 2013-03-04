<%@ include file="header.jsp" %>
	    
	    <header>
		<h1>Persons in the application</h1>
	    </header>


	    <c:choose>
		<c:when test="${empty persons}">
		    <p>There is nobody in the application for the moment.</p>
		</c:when>
		<c:otherwise>
		    <table>
			<tr>
			    <th>ID</th>
			    <th>First Name</th>
			    <th>Last Name</th>
			</tr>
			<c:forEach var="person" items="${persons}" >
			<tr>
			    <td><a href="displayPerson?personId=${person.personId}">
				    <c:out value="${person.personId}" />
				</a></td>
			    <td><c:out value="${person.firstName}" /></td>
			    <td><c:out value="${person.lastName}" /></td>
			</tr>
			</c:forEach>			    
		    </table>
		</c:otherwise>
	    </c:choose>
	    
<%@ include file="footer.jsp" %>