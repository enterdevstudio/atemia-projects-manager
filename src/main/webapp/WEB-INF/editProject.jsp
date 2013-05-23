	    <%@ include file="header.jsp" %>

		<header>
		    <h1>Project</h1>
		</header>
		<form method="post">
		    <input type="hidden" name="project-id" value="${project.id}" />
		    <p><label for="input-name">Name:</label></p>
		    <p>
			<input id="input-name" type="text" name="name"
                               value="${project.name}" required autofocus />
		    </p>

		    <p><label for="input-department">Department:</label></p>
		    <p>
                        <select id="input-department" name="department">
                            <option value="DPT" selected>DPT</option>
                            <option value="DEC">DEC</option>
                            <option value="Prospection">Prospection</option>
                            <option value="Gestion">Gestion</option>
                            <option value="Communication">Communication</option>
                            <option value="Développement de produits">Développement de produits</option>
                        </select>
		    </p>
                    
                    <p><label for="input-manager">Manager:</label></p>
                    <p>
                        <input type="text" name="manager-id" id="input-manager"
                               <c:if test="${not empty project.manager}">
                               value="${project.manager.id} ${project.manager.firstName} ${project.manager.lastName}"
                               </c:if>
                               list="list-manager" autocomplete="off" required />
                        <datalist id="list-manager">
                        <c:forEach var="person" items="${persons}" >
                            <option value="${person.id} ${person.firstName} ${person.lastName}">
                        </c:forEach>
                        </datalist>
                    </p>

		    <p><label for="input-nbDaysSold">Number of days sold:</label></p>
		    <p>
			<input id="input-nbDaysSold" type="number" name="nbDaysSold"
                               value="${project.nbDaysSold}" required />
		    </p>
                    
                    <p><label for="input-startWeek">Start week (2012-W01):</label></p>
		    <p>                        
                        <input id="input-startWeek" class="datepicker" type="text" name="startWeek"
                               value="${project.startWeek}" autocomplete="off" required />
                    </p>
                    
                    <p><label for="input-endWeek">End week (2012-W01):</label></p>
		    <p>
			<input id="input-endWeek" class="datepicker" type="text" name="endWeek"
                               value="${project.endWeek}" autocomplete="off" required />
                    </p>

		    <p>
			<input type="submit" name="execute" value="Validate" />
		    </p>
		</form>
            
		<script>
                    $(function() {
                        $(".datepicker").datepicker({
                            showWeek: true,
                            firstDay: 1,
                            showOtherMonths: true,
                            changeMonth: true,
                            changeYear: true,
                            dateFormat: 'yy-W'
                        });
                    });
		</script>
			
	    <%@ include file="footer.jsp" %>
