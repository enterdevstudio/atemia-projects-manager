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
			    <th>First Name</th>
			    <th>Last Name</th>
			    <th>Ratio</th>
			</tr>
			<c:forEach var="person" items="${persons}" >
                            <fmt:formatNumber var="ratio" value="${person.nbDaysAffected / person.nbDaysAvailable * 100}" maxFractionDigits="2" />
			<tr>
			    <td><a href="displayPerson?person-id=${person.id}">
				    <c:out value="${person.firstName}" />
				</a></td>
			    <td><c:out value="${person.lastName}" /></td>
                            <td>${ratio}%</td>
			</tr>
			</c:forEach>			    
		    </table>
		</c:otherwise>
	    </c:choose>
	    
<%@ include file="footer.jsp" %>