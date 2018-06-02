<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Buy ticket</title>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
</head>
<body>
<section class="accountsection">
    <div class="ticketbuydiv">
        <spring:form modelAttribute="train" method="post" action="/railway/passenger/buyticket/result">
            <div class="trainnumberinputline">
                <p>Train number</p>
                <spring:input id="trainnumberinput" class="smallinputfield" title="number" path="number"
                              onkeyup="javascript: checkTrainNumber(); return false;" />
            </div>
            <div class="reghint">Input train number (from 1 to 999)</div>
            <div class="buttonblock">
                <spring:button id="ticketbuybutton" class="button" disabled="true">Buy</spring:button>
            </div>
        </spring:form>
        <div class="buttonblock">
            <c:if test="${role == true}">
                <a href="/railway/admin"><button class="button">Account</button></a>
            </c:if>
            <c:if test="${role == false}">
                <a href="/railway/passenger"><button class="button">Account</button></a>
            </c:if>
        </div>
    </div>
</section>
<script type="text/javascript" src="/static/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="/static/js/script_js.js"></script>
<script type="text/javascript" src="/static/js/input_log_valid_check.js"></script>
</body>
</html>
