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
		<li><strong>Name:</strong> ${project.name}</li>
		<li><strong>Department:</strong> ${project.department}</li>
		<li><strong>Manager:</strong> <a href="displayPerson?person-id=${project.manager.id}">${project.manager.firstName} ${project.manager.lastName}</a></li>
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

            <div class="center">
                <table>
                    <tr>
                        <td style="border: 0px;">Caption : </td>
                        <td class="production">Production</td>
                        <td class="terrain">Terrain</td>
                        <td class="copil">Copil</td>
                        <td class="conges">Cong�s</td>
                    </tr>
                </table>
		
                <c:set var="weeks" value="${project.weeks}" />
                <c:set var="firstWeek" value="${weeks.get(0)}" />            
                <div class="accordion">
                    <h3>${firstWeek.year}-W${firstWeek.number}</h3>
                    <div>
                        <table id="week-table">
                            <tr>
                                <th>Week</th>
                            <c:forEach var="person" items="${project.workers}">
                                <th colspan="4">${person.firstName} ${person.lastName}</th>
                            </c:forEach>
                            </tr>
                        <c:forEach var="week" items="${weeks}">
                            <c:if test="${week.number == 1 || week.number == 14 || week.number == 27 || week.number == 40}">
                        </table>
                    </div>

                    <h3>${week.year}-W${week.number}</h3>
                    <div>
                        <table>
                            <tr>
                                <th>Week</th>
                            <c:forEach var="person" items="${project.workers}">
                                <th colspan="4">${person.firstName} ${person.lastName}</th>
                            </c:forEach>
                            </tr>
                        </c:if>      
                        <tr>
                            <th>${week}</th>		
                        <c:forEach var="person" items="${project.workers}">
                            <c:set var="activity" value="${project.getActivity(person, week)}" />
                            <c:set var="conges" value="${person.getNbDaysConges(week.toString())}" />
                            <c:choose>
                                <c:when test="${activity.nbDaysWork == 0}">
                                    <td colspan="3">
                                        <a href="editActivity?activity-id=${activity.id}&amp;project-id=${project.id}">
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
                                    </c:if>
                                </c:otherwise>
                            </c:choose>

                                <td<c:if test="${conges > 0}"> class="conges"</c:if>>
                                    <a href="manageConges?person-id=${person.id}&amp;week=${week.toString()}">${conges}</a>
                                </td>
                            </c:forEach>
                            </tr>
                        </c:forEach>
                        </table>
                    </div>
                </div>
            </div>	
	    <%@ include file="footer.jsp" %>
