	
        </section>
            
        <script type="text/javascript">
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
            
            
            $(function() {
                $("#person_week").accordion({
                    heightStyle: "content",
                    collapsible: true
                });
            });
        </script>
    </body>
</html>
