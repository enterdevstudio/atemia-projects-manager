	    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp" %>
	    
	    <header>
		<form method="post" action="deleteProject">
		    <input type="hidden" name="project-id" value="${project.id}" />
		    <input type="submit" name="delete" value="Delete" />
		</form>
		<form method="post" action="editProject">
		    <input type="hidden" name="project-id" value="${project.id}" />
		    <input type="submit" name="update" value="Update" />
		</form>
		<h1>Project</h1>
	    </header>
		
	    <form id="addWorkerForm" method="post" action="addWorkerToProject">
		<input type="hidden" name="project-id" value="${project.id}" />
                <p><label for="input-id">Add a worker to this project:</label></p>
                <p>
                    <input type="text" name="person-id" id="input-id"
                           list="list-id" autocomplete="off" required />
                    <datalist id="list-id">
                    <c:forEach var="person" items="${persons}" >
                        <option value="${person.id} ${person.firstName} ${person.lastName}">
                    </c:forEach>
                    </datalist>
                    <input type="submit" name="execute" value="Add a worker" />
                </p>
	    </form>
                    
            <fmt:formatNumber var="ratio" value="${project.nbDaysAffected / project.nbDaysSold * 100}" maxFractionDigits="2" />
	    <ul>
		<li><strong>Number:</strong> ${project.id}</li>
		<li><strong>Name:</strong> ${project.name}</li>
		<li><strong>Department:</strong> ${project.department}</li>
		<li><strong>Nb of days sold:</strong> ${project.nbDaysSold} - <strong>Affected:</strong> ${project.nbDaysAffected}
                    - <strong>Ratio:</strong> ${ratio}%</li>
		<li><strong>Start Week:</strong> ${project.startWeek}</li>
		<li><strong>End Week:</strong> ${project.endWeek}</li>
		<li><strong>Workers:</strong>
	    <c:choose>
		<c:when test="${empty project.workers}">
		    This project has no workers for the moment.
		</c:when>
		<c:otherwise>
		<c:forEach var="worker" items="${project.workers}">
		    <a href="displayPerson?person-id=${worker.id}">${worker.firstName} ${worker.lastName}</a>
		</c:forEach>
		</li>
		</c:otherwise>
	    </c:choose>
	    </ul>               

            <table>
                <tr>
                    <td style="border: 0px;">Caption : </td>
                    <td class="production">Production</td>
                    <td class="terrain">Terrain</td>
                    <td class="copil">Copil</td>
                    <td class="conges">Congés</td>
                </tr>
            </table>
		
	    <table id="week-table">
		<tr>
		    <th>Week</th>
		<c:forEach var="person" items="${project.workers}">
                    <th colspan="4">${person.firstName} ${person.lastName}</th>
		</c:forEach>
		</tr>
	    <c:forEach var="week" items="${project.weeks}">
		<tr>
		    <th>${week.year}-W${week.number}</th>		
		<c:forEach var="person" items="${project.workers}">
                    <c:set var="activity" value="${project.getActivity(person, week)}" />
                    
                    <c:choose>
                        <c:when test="${empty activity || activity.nbDaysSet == 0}">
                            <td colspan="4">
                                <a href="editActivity?week=${week.number}&amp;year=${week.year}&amp;person-id=${person.id}&amp;project-id=${project.id}">
                                    +
                                </a>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${activity.production > 0}">               
                            <td class="production" colspan="${activity.productionColspan}">
                                <a href="editActivity?activity-id=${activity.id}&amp;project-id=${project.id}">
                                    ${activity.production}
                                </a>
                            </td>
                            </c:if><c:if test="${activity.terrain > 0}">
                            <td class="terrain" colspan="${activity.terrainColspan}">
                                <a href="editActivity?activity-id=${activity.id}&amp;project-id=${project.id}">
                                    ${activity.terrain}
                                </a>
                            </td>
                            </c:if><c:if test="${activity.copil > 0}">
                            <td class="copil" colspan="${activity.copilColspan}">
                                <a href="editActivity?activity-id=${activity.id}&amp;project-id=${project.id}">
                                    ${activity.copil}
                                </a>
                            </td>
                            </c:if><c:if test="${activity.conges > 0}">
                            <td class="conges" colspan="${activity.congesColspan}">
                                <a href="editActivity?activity-id=${activity.id}&amp;project-id=${project.id}">
                                    ${activity.conges}
                                </a>
                            </td>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
		</c:forEach>
		</tr>
	    </c:forEach>
	    </table>
			
	    <%@ include file="footer.jsp" %>
