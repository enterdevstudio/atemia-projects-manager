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
                    <th>Persons</th>
                    <td><a href="listPerson">${nbPersons}</a></td>
                </tr>
                <tr>
                    <th>Projects</th>
                    <td><a href="listProject">${nbProjects}</a></td>
                </tr>
                <tr>
                    <th>Working ratio (days)</th>
                    <fmt:formatNumber var="ratio" value="${daysWorked / daysAvailable * 100}" maxFractionDigits="2" />
                    <td>${daysWorked} / ${daysAvailable} (${ratio}%)</td>
                </tr>
            </table>
<!--                <div id="chart1" style="height:200px;width:400px;"></div>
                <script type="text/javascript">
                $(document).ready(function(){
                    var data = [
                        
                      ['Heavy Industry', 12],['Retail', 9], ['Light Industry', 14],
                      ['Out of home', 16],['Commuting', 7], ['Orientation', 9]
                    ];
                    var plot1 = jQuery.jqplot ('chart1', [data],
                      {
                        seriesDefaults: {
                          // Make this a pie chart.
                          renderer: jQuery.jqplot.PieRenderer,
                          rendererOptions: {
                            // Put data labels on the pie slices.
                            // By default, labels show the percentage of the slice.
                            showDataLabels: true
                          }
                        },
                        legend: { show:true, location: 'e' }
                      }
                    );
                  });
</script>-->
	    
<%@ include file="footer.jsp" %>
