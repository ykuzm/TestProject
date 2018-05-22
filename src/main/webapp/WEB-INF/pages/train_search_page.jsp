<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Choose station</title>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="/static/css/jquery_ui.css" />
</head>
<body>
<section class="accountsection">
    <div class="stationnamediv">
        <spring:form modelAttribute="trainSearch" method="post" action="/railway/account/trainsearch/info">
            <div class="trainnumberinputline">
                <p>Departure station</p>
                <spring:input id="stationnameinput1" class="largeinputfield" title="name1" path="station1.name"
                              onkeyup="javascript: checkStation1Name(); return false;" />
            </div>
            <div class="reghint">Use only letters and -, first letter in upper-case</div>
            <div class="trainnumberinputline">
                <p>Destination station</p>
                <spring:input id="stationnameinput2" class="largeinputfield" title="name2" path="station2.name"
                              onkeyup="javascript: checkStation2Name(); return false;" />
            </div>
            <div class="reghint">Use only letters and -, first letter in upper-case</div>
            <div class="trainnumberinputline">
                <p>Departure date</p>
                <spring:input id="departuredate" class="largeinputfield" title="departuredate" path="date"
                              onchange="javascript: checkDepatureDate(); return false;" />
                <div class="reghint">Pick departure date (e.g. 25-May-2018)</div>
            </div>
            <div class="buttonblock">
                <spring:button id="trainsearchbutton" class="button" disabled="true">Search trains</spring:button>
            </div>
        </spring:form>
        <div class="buttonblock">
            <a href="/railway/account"><button class="button">Account</button></a>
        </div>
    </div>
</section>
<script type="text/javascript" src="/static/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="/static/js/jquery_ui_v1.9.2.js"></script>
<script type="text/javascript" src="/static/js/script_js.js"></script>
<script type="text/javascript" src="/static/js/input_log_valid_check.js"></script>
<script>
    $(function() {
        $( "#departuredate" ).datepicker({dateFormat:"dd-MM-yy"});
    });
</script>
</body>
</html>