<%@ include file="header.jsp" %>
	    
	    <header>
		<h1>Editing activity of ${activity.worker.firstName} ${activity.worker.lastName}</h1>
	    </header>
            <p>This worker already has ${activity.worker.getNbDaysConges(activity.week)} days unavailable
                and ${activity.worker.getNbDaysAffected(activity.week).nbDaysWork} days work this week (${activity.week}).</p>                
            <form method="post">
                <input type="hidden" name="activity-id" value="${activity.id}" />
                <table>
                    <tr>
                        <th colspan="2">Please distribute the days of the week between these categories:</th>
                    </tr>
                    <tr class="production">
                        <td>
                            <label for="input_production">Production:</label>
                        </td>
                        <td>
                            <input id="input_production" type="number" name="production"
                                   value="${activity.production}" required autofocus />
                        </td>
                    </tr>
                    <tr class="terrain">
                        <td>
                            <label for="input-terrain">Terrain:</label>
                        </td>			
                        <td>
                            <input id="input-terrain" type="number" name="terrain"
                                   value="${activity.terrain}" required autofocus />
                        </td>
                    </tr>
                    <tr class="copil">
                        <td>
                            <label for="input-copil">Copil:</label>
                        </td>
                        <td>
                            <input id="input-copil" type="number" name="copil"
                                   value="${activity.copil}" required />
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="border: 0px;">
                            <input type="submit" name="execute" value="Validate" />
                        </td>
                    </tr>
                </table>
            </form>
	    
<%@ include file="footer.jsp" %>