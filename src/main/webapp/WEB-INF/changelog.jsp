<%@ include file="header.jsp" %>
	    
	    <header>
		<h1>Changelog</h1>
	    </header>

            <h2>v2.0</h2>
            <ul>
                <li>Weeks on project page are displayed using an accordion</li>
                <li>Accordion now groups per trimester</li>
                <li>height of the menu was reduced</li>
            </ul>

            <h2>v1.9</h2>
            <ul>
                <li>Weeks on person page are displayed using an accordion</li>
                <li>Days unavailable are now indicated on the project page.</li>
                <li>It's now possible to manage availability table of the person page or the project page</li>
            </ul>

            <h2>v1.8</h2>
            <ul>
                <li>Days unavailable are not processed separatly on the person page</li>
                <li>It's now possible to add more than 5 days on a week (7max) but you have a warning</li>
            </ul>

            <h2>v1.7</h2>
            <ul>
                <li>Add repartition of the day in the week on the person page</li>
                <li>Fix activities were not deleted correctly when updating project</li>
                <li>Fix regression activities were not created when a worker was added.</li>
            </ul>

            <h2>v1.6</h2>
            <ul>
                <li>Congés are now updated in every projects of a person</li>
                <li>Font family is now "the sans"</li>
                <li>Fix datepicker return the number of the week without 0 when nb < 10</li>
                <li>Fix unable to change the start week and the end week of a project (activities are now adapted)</li>
            </ul>

            <h2>v1.5</h2>
            <ul>
                <li>Add «communication» and «développement de produits» to department</li>
                <li>Count congés in week table on person page</li>
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