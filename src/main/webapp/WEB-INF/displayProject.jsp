	    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp" %>
	    
	    <header>
		<form method="post" action="editProject">
		    <input type="hidden" name="project-id" value="${project.id}" />
		    <input type="submit" name="update" value="Update" />
		    <input type="submit" name="delete" value="Delete" />
		</form>
		<h1>Project</h1>
	    </header>
	    <ul>
		<li><strong>Number :</strong> ${project.id}</li>
		<li><strong>Name :</strong> ${project.name}</li>
		<li><strong>Department :</strong> ${project.department}</li>
		<li><strong>Nb of days sold :</strong> ${project.nbDaysSold}</li>
		<li><strong>Workers :</strong>
	    <c:choose>
		<c:when test="${empty project.workers}">
		    This project has no workers for the moment.
		</c:when>
		<c:otherwise>
		<c:forEach var="person" items="${project.workers}">
		    <a href="displayPerson?person-id=${person.id}">${person.firstName} ${person.lastName}</a>
		</c:forEach>
		</li>
		</c:otherwise>
	    </c:choose>
	    </ul>
		
	    <form method="post" action="addWorkerToProject">
		<input type="hidden" name="project-id" value="${project.id}" />
		<input type="submit" name="addWorker" value="Add a worker" />
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
		
	    <table>
		<tr>
		    <th>Week</th>
		<c:forEach var="person" items="${project.workers}">
                    <th>${person.firstName} ${person.lastName}</th>
		</c:forEach>
		</tr>
	    <c:forEach var="week" items="${project.weeks}">
		<tr>
		    <td>${week.number}</td>			
		<c:forEach var="job" items="${week.job}">                    
                    <td>
                    <c:if test="${job[0] > 0}">
                        <span class="production">${job[0]}</span>
                    </c:if><c:if test="${job[1] > 0}">
                        <span class="terrain">${job[1]}</span>
                    </c:if><c:if test="${job[2] > 0}">
                        <span class="copil">${job[2]}</span>
                    </c:if><c:if test="${job[3] > 0}">
                        <span class="conges">${job[3]}</span>
                    </c:if>
                    </td>
		</c:forEach>
		</tr>
	    </c:forEach>
	    </table>
			
	    <%@ include file="footer.jsp" %>
