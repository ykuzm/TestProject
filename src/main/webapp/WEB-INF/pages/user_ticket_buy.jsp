<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thank you!</title>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
</head>
<body>
<section class="accountsection">
    <div class="maindiv">
        <div class="incorrectlogdiv">
            <div class="incorrectinputtext">You have successfully purchased your ticket!</div>
            <div class="buttonblock">
                <c:if test="${role == 'admin'}">
                    <a href="/railway/admin"><button class="button2">Account</button></a>
                </c:if>
                <c:if test="${role == 'user'}">
                    <a href="/railway/user"><button class="button2">Account</button></a>
                </c:if>
            </div>
        </div>
    </div>
</section>
<script type="text/javascript" src="/static/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="/static/js/vertical_displacement.js"></script>
</body>
</html>
