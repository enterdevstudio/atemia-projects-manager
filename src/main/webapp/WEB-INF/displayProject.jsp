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
	    <ul>
		<li><strong>Number:</strong> ${project.id}</li>
		<li><strong>Name:</strong> ${project.name}</li>
		<li><strong>Department:</strong> ${project.department}</li>
		<li><strong>Nb of days sold:</strong> ${project.nbDaysSold}</li>
		<li><strong>Start Week:</strong> ${project.startYear}-W${project.startWeek}</li>
		<li><strong>End Week:</strong> ${project.endYear}-W${project.endWeek}</li>
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
		
	    <form method="post" action="addWorkerToProject">
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
                    <th>${person.firstName} ${person.lastName}</th>
		</c:forEach>
		</tr>
	    <c:forEach var="week" items="${project.weeks}">
		<tr>
		    <th>${week.year}-W${week.number}</th>			
		<c:forEach var="activity" items="${week.activities}">                    
                    <td>
                    <c:if test="${activity.production > 0}">
                        <span class="production">${activity.production}</span>
                    </c:if><c:if test="${activity.terrain > 0}">
                        <span class="terrain">${activity.terrain}</span>
                    </c:if><c:if test="${activity.copil > 0}">
                        <span class="copil">${activity.copil}</span>
                    </c:if><c:if test="${activity.conges > 0}">
                        <span class="conges">${activity.conges}</span>
                    </c:if>
                    </td>
		</c:forEach>
		</tr>
	    </c:forEach>
	    </table>
			
	    <%@ include file="footer.jsp" %>
