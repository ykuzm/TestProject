<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>No such train</title>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
</head>
<body>
<section class="accountsection">
    <div class="incorrectlogdiv">
        <div class="incorrectinputtext">Sorry! ${exception}</div>
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
</body>
</html>
