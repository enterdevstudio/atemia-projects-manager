<%@ include file="header.jsp" %>
	    
	    <header>
		<h1>Changelog</h1>
	    </header>

            <h2>v1.5</h2>
            <ul>
                <li>Add «communication» and «développement de produits» to department</li>
                <li>Count congés in week table on person page</li>
                <li></li>
                <li></li>
            </ul>

            <h2>v1.4</h2>
            <ul>
                <li>Properly delete resources when person or project is deleted</li>
                <li>NbDaysAffected is now a float</li>
                <li>Fix error 500 when re-adding a worker in a project</li>
                <li>Fix project not added to his manager</li>
            </ul>

            <h2>v1.3</h2>
            <ul>
                <li>Days are now decimal numbers</li>
                <li>Weeks can now be selected with a datePicker</li>
                <li>Improve person view by floating right the week table</li>
                <li>Add Atemia background</li>
                <li>Add verification to weeks when create / update a project</li>
                <li>Fix production input in activity page</li>
            </ul>

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