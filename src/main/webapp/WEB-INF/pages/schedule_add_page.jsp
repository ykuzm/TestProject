<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add schedule</title>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="/static/css/anytime_5.2.0.css" />
</head>
<body>
<section class="accountsection">
    <div class="stationnamediv">
        <spring:form modelAttribute="scheduleAdd" method="post" action="/railway/admin/addschedule/result-${trainNumber}">
            <div class="trainnumberinputline">
                <p>Destination station</p>
                <spring:input id="stationnameinput3" class="largeinputfield" title="name" path="station.name"
                              onkeyup="javascript: checkStation3Name(); return false;" />
            </div>
            <div class="reghint">Use only letters and -, first letter in upper-case</div>
            <div class="trainnumberinputline">
                <p>Departure date</p>
                <spring:input id="departuredate1" class="largeinputfield" title="departuredate" path="date"
                              onchange="javascript: checkDepatureDate1(); return false;" />
                <div class="reghint">Pick departure date (e.g. 25-May-2018 12:35)</div>
            </div>
            <div class="buttonblock">
                <spring:button id="scheduleaddbutton" class="button" disabled="true">Add schedule</spring:button>
            </div>
        </spring:form>
        <div class="buttonblock">
            <a href="/railway/admin"><button class="button">Account</button></a>
        </div>
    </div>
</section>
<script type="text/javascript" src="/static/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="/static/js/script_js.js"></script>
<script type="text/javascript" src="/static/js/anytime_5.2.0.js"></script>
<script type="text/javascript" src="/static/js/input_log_valid_check.js"></script>
<script>
    AnyTime.picker( "departuredate1",
        { format: "%d-%M-%Z %H:%i", firstDOW: 1 } )
</script>
</body>
</html>
