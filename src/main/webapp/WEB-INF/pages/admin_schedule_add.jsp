<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add schedule</title>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
</head>
<body>
<section class="accountsection">
    <div class="maindiv">
        <div class="stationnamediv">
            <spring:form modelAttribute="station" method="post" action="/railway/admin/addschedule/result-${trainNumber}">
                <div class="trainnumberinputline">
                    <p>Destination station</p>
                    <spring:input id="stationname" class="largeinputfield" title="name" path="name"
                                  onkeyup="javascript: checkStationName(); return false;" />
                </div>
                <div class="reghint">Use only letters and -, first letter in upper-case</div>
                <div class="buttonblock">
                    <spring:button id="button" class="button" disabled="true">Add schedule</spring:button>
                </div>
            </spring:form>
            <div class="buttonblock">
                <a href="/railway/admin"><button class="button">Account</button></a>
            </div>
        </div>
    </div>
</section>
<script type="text/javascript" src="/static/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="/static/js/vertical_displacement.js"></script>
<script type="text/javascript" src="/static/js/station_valid_check.js"></script>
</body>
</html>
