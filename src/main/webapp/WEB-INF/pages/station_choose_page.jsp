<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Choose station</title>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
</head>
<body>
<section class="accountsection">
    <div class="stationnamediv">
        <spring:form modelAttribute="station" method="post" action="/railway/account/station/info">
            <div class="trainnumberinputline">
                <p>Station name</p>
                <spring:input id="stationnameinput" class="largeinputfield" title="name" path="name"
                              onkeyup="javascript: checkStationName(); return false;" />
            </div>
            <div class="reghint">Use only letters and -, first letter in upper-case</div>
            <div class="buttonblock">
                <spring:button id="stationinfobutton" class="button" disabled="true">View schedule</spring:button>
            </div>
        </spring:form>
        <div class="buttonblock">
            <a href="/railway/account"><button class="button">Account</button></a>
        </div>
    </div>
</section>
<script type="text/javascript" src="/static/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="/static/js/script_js.js"></script>
<script type="text/javascript" src="/static/js/input_log_valid_check.js"></script>
</body>
</html>
