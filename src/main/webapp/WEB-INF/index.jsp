<%@ include file="header.jsp" %>
	    
	    <header>
		<h1>Home</h1>
	    </header>

	    <p>Welcome in the Atemia projects manager. This application allows you to easily
	    manage projects and human resources on a planning. We hope you'll enjoy it.</p>
            
            <table>
                <tr>
                    <th colspan="2"><h2>Global indicators</h2></th>
                </tr>
                <tr>
                    <th>Current projects</th>
                    <td>${nbProjects}</td>
                </tr>
                <tr>
                    <th>Persons</th>
                    <td>${nbPersons}</td>
                </tr>
                <tr>
                    <th>Weeks sold</th>
                    <td>${ratioWeeks}</td>
                </tr>
            </table>
	    
<%@ include file="footer.jsp" %>
