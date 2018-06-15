<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add station</title>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
</head>
<body>
<section class="accountsection">
    <div class="maindiv">
        <div class="stationnamediv">
            <h1 class="text">
                Station add
            </h1>
            <spring:form modelAttribute="station" method="post" action="/railway/admin/stationadd/result">
                <div class="trainnumberinputline">
                    <p>Station name</p>
                    <spring:input id="stationname" class="largeinputfield" title="name" path="name"
                                  onkeyup="javascript: checkStationName(); return false;" />
                </div>
                <div class="reghint">Use letters, numbers, - and space</div>
                <div class="trainnumberinputline">
                    <p>Coordinate X</p>
                    <spring:input id="coordX" class="largeinputfield" title="coordX" path="coordX"
                                  onkeyup="javascript: checkCoordX(); return false;" />
                </div>
                <div class="reghint">Input coordinate X (from 1 to 50)</div>
                <div class="trainnumberinputline">
                    <p>Coordinate Y</p>
                    <spring:input id="coordY" class="largeinputfield" title="coordY" path="coordY"
                                  onkeyup="javascript: checkCoordY(); return false;" />
                </div>
                <div class="reghint">Input coordinate Y (from 1 to 50)</div>
                <div class="buttonblock">
                    <spring:button id="button" class="button" disabled="true">Add station</spring:button>
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
<script type="text/javascript" src="/static/js/station_add_valid_check.js"></script>
</body>
</html>
