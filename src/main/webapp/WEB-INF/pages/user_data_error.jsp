<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Data error</title>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
</head>
<body>
<section class="accountsection">
    <c:if test="${role == null}">
        <div class="logoutdiv">
            <a href="/railway/login"><button class="logoutbutton">Log in</button></a>
            <a href="/railway/register"><button class="logoutbutton">Register</button></a>
        </div>
    </c:if>
    <div class="maindiv">
        <div class="incorrectlogdiv">
            <div class="incorrectinputtext">Sorry! ${exception}</div>
            <div class="buttonblock">
                <c:if test="${role == 'admin'}">
                    <a href="/railway/admin"><button class="button">Account</button></a>
                </c:if>
                <c:if test="${role == 'user'}">
                    <a href="/railway/user"><button class="button">Account</button></a>
                </c:if>
                <c:if test="${role == null}">
                    <a href="/railway"><button class="button2">Start page</button></a>
                </c:if>
            </div>
        </div>
    </div>
</section>
<script type="text/javascript" src="/static/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="/static/js/vertical_displacement.js"></script>
</body>
</html>