
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
    $(".accordion").accordion({
        heightStyle: "content",
        collapsible: true
    });
});

