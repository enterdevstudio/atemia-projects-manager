<%@ include file="header.jsp" %>
	    
	    <header>
		<form method="post" action="deletePerson">
		    <input type="hidden" name="person-id" value="${person.id}" />
		    <input type="submit" name="delete" value="Delete" />
		</form>
		<form method="post" action="editPerson">
		    <input type="hidden" name="person-id" value="${person.id}" />
		    <input type="submit" name="update" value="Update" />
		</form>
		<h1>${person.firstName} ${person.lastName}</h1>
	    </header>
            
            <fmt:formatNumber var="ratio" value="${person.nbDaysAffected / person.nbDaysAvailable * 100}" maxFractionDigits="2" />
            <p>${person.firstName} has ${person.nbDaysAffected} affected days on ${person.nbDaysAvailable} days available this year (${ratio}%).<br />
            Days are dispatched between ${person.production}% in production, ${person.prospection}% in prospection,
            ${person.gestion}% in gestion and ${person.communication}% in communication</p>

            <c:choose>
		<c:when test="${empty person.managedProjects}">
		    <p>This person does not manage any project for the moment.</p>
		</c:when>
		<c:otherwise>
                    <p>Managed projects:
                        <c:forEach var="project" items="${person.managedProjects}" >
                            <a href="displayProject?project-id=${project.id}">${project.name}</a>, 
                        </c:forEach>
                    </p>
		</c:otherwise>
	    </c:choose>
            
            
	    <c:choose>
		<c:when test="${empty person.projects}">
		    <p>This person does not participate to a project for the moment.</p>
		</c:when>
		<c:otherwise>
                    <table>
                        <tr>
                            <td class="no-border">
                                <table>
                                    <tr>
                                        <th>Name</th>
                                        <th>Department</th>
                                        <th>Nb Days Affected</th>
                                        <th>Nb Days Sold</th>
                                    </tr>
                                    <c:forEach var="project" items="${person.projects}" >
                                    <tr>
                                        <td><a href="displayProject?project-id=${project.id}">${project.name}</a></td>
                                        <td>${project.department}</td>
                                        <td>${project.getNbDaysAffected(person.id)}</td>
                                        <td>${project.nbDaysSold}</td>
                                    </tr>
                                    </c:forEach>
                                </table>
                            </td>
                            <td class="no-border">
                                <table id="person_week">
                                    <tr>
                                        <th>Week</th>
                                        <th>Nb Days Affected</th>
                                    </tr>
                                    <c:forEach var="week" items="${person.weeks}" >
                                    <tr>
                                        <td>${week}</td>
                                        <td>${person.getNbDaysAffected(week)}</td>
                                    </tr>
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>
                     </table>
		</c:otherwise>
	    </c:choose>
	    
<%@ include file="footer.jsp" %>
