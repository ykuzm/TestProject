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
    <div class="stationnamediv">
        <h1 class="text">
            Station add
        </h1>
        <spring:form modelAttribute="station" method="post" action="/railway/account/addstation/result">
            <div class="trainnumberinputline">
                <p>Station name</p>
                <spring:input id="stationaddnameinput" class="largeinputfield" title="name" path="name"
                              onkeyup="javascript: checkAddStationName(); return false;" />
            </div>
            <div class="reghint">Use only letters and -, first letter in upper-case</div>
            <div class="buttonblock">
                <spring:button id="stationaddbutton" class="button" disabled="true">Add station</spring:button>
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
