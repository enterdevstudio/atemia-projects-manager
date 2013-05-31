<%@ include file="header.jsp" %>
	    
	    <header>
		<h1>Manage availability of ${person.firstName} ${person.lastName}</h1>
	    </header>
            
                <table>
                    <tr>
                        <th>Week</th>
                        <th>Nb Days Unavailable</th>
                    </tr>
                    <tr>
                        <form method="post">
                            <input type="hidden" name="person-id" value="${person.id}" />
                            <td>
                                <input id="input-week" class="datepicker" type="text" value="${week}"
                                       name="week" autocomplete="off" style="width: 80px;" required />
                            </td>
                            <td>
                                <input type="number" id="input-nbDays" name="nbDays" style="width: 80px;"
                                       value="${person.getNbDaysConges(week.toString())}" required />
                            </td>
                            <td style="border: 0px;">
                                <input type="submit" name="setDays" value="Set" />
                            </td>
                        </form>
                    </tr>
                    <c:forEach items="${person.conges}" var="pair">
                    <tr>
                        <td>${pair.key}</td>
                        <td>${pair.value}</td>
                    </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="2" style="border: 0px; padding-top: 30px;">
                            <form method="post">
                                <input type="hidden" name="person-id" value="${person.id}" />
                                <input type="submit" name="execute" value="Exit" />
                            </form>
                        </td>
                    </tr>
                </table>
            
<%@ include file="footer.jsp" %>
