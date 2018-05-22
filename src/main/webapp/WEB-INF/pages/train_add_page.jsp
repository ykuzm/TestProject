<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add train</title>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
</head>
<body>
<section class="accountsection">
    <div class="ticketbuydiv">
        <h1 class="text">
            Train add panel
        </h1>
        <spring:form modelAttribute="train" method="post" action="/railway/account/addtrain/result">
            <div class="trainnumberinputline">
                <p>Train number</p>
                <spring:input id="trainaddnumberinput" class="smallinputfield" title="number" path="number"
                              onkeyup="javascript: checkTrainAddNumber(); return false;" />
            </div>
            <div class="reghint">Input train number (from 1 to 999)</div>
            <div class="trainnumberinputline">
                <p>Number of seats</p>
                <spring:input id="trainaddseatsinput" class="smallinputfield" title="freeSeats" path="freeSeats"
                              onkeyup="javascript: checkTrainAddSeats(); return false;" />
            </div>
            <div class="reghint">Input number of freaSeats (from 1 to 999)</div>
            <div class="buttonblock">
                <spring:button id="addtrainbutton" class="button" disabled="true">Add train</spring:button>
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
