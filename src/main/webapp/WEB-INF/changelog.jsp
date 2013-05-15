<%@ include file="header.jsp" %>
	    
	    <header>
		<h1>Changelog</h1>
	    </header>

            <h2>v1.2</h2>

            <ul>
                <li>Add ratio to listPerson page</li>
                <li>Add possiblity to link a person to a project as a manager</li>
                <li>Add categories (prod, prospec, gestion, comm) to dispatch days of a person</li>
                <li>Add a changelog to the application (you are reading it!)</li>
                <li>Fix person tables presentation</li>
            </ul>

            <h2>v1.1</h2>

            <ul>
                <li>Add NbDaysSold per project on person page</li>
                <li>Add NbDaysAffected per week on person page</li>
                <li>Add NbDaysAffected per person on projects list page</li>
                <li>Update Logo</li>
                <li>Fix 5 days available per week per person #8</li>
                <li>Fix activities not created when update project #7</li>
                <li>Fix nbDaysSold now accepting float number</li>
                <li>Fix no more 5 days in production by default</li>
                <li>Refactor model (Week, Activity, Person and Project)</li>
            </ul>
            
<%@ include file="footer.jsp" %>