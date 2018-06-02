<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Choose train</title>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
</head>
<body>
<section class="accountsection">
    <div class="ticketbuydiv">
        <spring:form modelAttribute="train" method="post" action="/railway/admin/trainpassenger/result">
            <div class="trainnumberinputline">
                <p>Train number</p>
                <spring:input id="trainnumberinput" class="smallinputfield" title="number" path="number"
                              onkeyup="javascript: checkTrainNumber(); return false;" />
            </div>
            <div class="reghint">Input train number (from 1 to 999)</div>
            <div class="buttonblock">
                <spring:button id="ticketbuybutton" class="button" disabled="true">View passengers</spring:button>
            </div>
        </spring:form>
        <div class="buttonblock">
            <a href="/railway/admin"><button class="button">Account</button></a>
        </div>
    </div>
</section>
<script type="text/javascript" src="/static/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="/static/js/script_js.js"></script>
<script type="text/javascript" src="/static/js/input_log_valid_check.js"></script>
</body>
</html>
