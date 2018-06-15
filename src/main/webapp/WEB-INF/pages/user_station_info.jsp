<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Choose station</title>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
</head>
<body>
<section class="accountsection">
    <div class="maindiv">
        <div class="stationnamediv">
            <spring:form modelAttribute="station" method="post" action="/railway/user/station/info">
                <div class="trainnumberinputline">
                    <p>Station name</p>
                    <spring:input id="stationname" class="largeinputfield" title="name" path="name"
                                  onkeyup="javascript: checkStationName(); return false;" />
                </div>
                <div class="reghint">Use letters, numbers, - and space</div>
                <div class="buttonblock">
                    <spring:button id="button" class="button" disabled="true">View schedule</spring:button>
                </div>
            </spring:form>
            <div class="buttonblock">
                <c:if test="${role == 'admin'}">
                    <a href="/railway/admin"><button class="button">Account</button></a>
                </c:if>
                <c:if test="${role == 'user'}">
                    <a href="/railway/user"><button class="button">Account</button></a>
                </c:if>
            </div>
        </div>
    </div>
</section>
<script type="text/javascript" src="/static/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="/static/js/vertical_displacement.js"></script>
<script type="text/javascript" src="/static/js/station_valid_check.js"></script>
</body>
</html>
