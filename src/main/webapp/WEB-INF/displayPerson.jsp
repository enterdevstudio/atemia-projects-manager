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
		<form method="post" action="manageConges">
		    <input type="hidden" name="person-id" value="${person.id}" />
		    <input type="submit" name="conges" value="Availability" />
		</form>
		<h1>${person.firstName} ${person.lastName}</h1>
	    </header>
                    
            <c:set var="weeks" value="${person.weeks}" />
	    <c:if test="${not empty weeks}">
                <table id="person_week">
                    <tr>
                        <th>Week</th>
                        <th colspan="3">Nb Days Affected</th>
                        <th>Unavailable</th>
                        <th>Total</th>
                    </tr>
                    <c:forEach var="week" items="${weeks}" >
                    <c:set var="activity" value="${person.getNbDaysAffected(week)}" />
                    <c:set var="conges" value="${person.getNbDaysConges(week.toString())}" />
                    <tr>
                        <td>${week}</td>
                        <c:choose>
                            <c:when test="${activity.nbDaysWork == 0}">
                                <td colspan="3">0.0</td>
                            </c:when>
                            <c:otherwise>
                                <c:if test="${activity.production > 0}">               
                                <td class="production" colspan="${activity.productionColspan}">
                                    ${activity.production}
                                </td>
                                </c:if><c:if test="${activity.terrain > 0}">
                                <td class="terrain" colspan="${activity.terrainColspan}">
                                    ${activity.terrain}
                                </td>
                                </c:if><c:if test="${activity.copil > 0}">
                                <td class="copil" colspan="${activity.copilColspan}">
                                    ${activity.copil}
                                </td>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                        <td<c:if test="${conges > 0}"> class="conges"</c:if>>
                            <a href="manageConges?person-id=${person.id}&amp;week=${week.toString()}">${conges}</a>
                        </td>
                        <td<c:if test="${activity.nbDaysWork + conges > 5}">
                            style="background-color: red;"
                        </c:if>>${activity.nbDaysWork + conges}</td>
                    </tr>
                    </c:forEach>
                </table>
            </c:if>
            
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
		</c:otherwise>
	    </c:choose>
	    
<%@ include file="footer.jsp" %>
