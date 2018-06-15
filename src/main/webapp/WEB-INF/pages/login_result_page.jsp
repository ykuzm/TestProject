<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Successfull log in</title>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
</head>
<body>
<section class="logsection">
    <div class="maindiv">
        <div class="incorrectlogdiv">
            <div class="loginsuccessfullpage">Log in is successfull!</div>
            <c:if test="${role == 'admin'}">
                <meta http-equiv="refresh" content="1;http://localhost:8080/railway/admin" />
            </c:if>
            <c:if test="${role == 'user'}">
                <meta http-equiv="refresh" content="1;http://localhost:8080/railway/user" />
            </c:if>
        </div>
    </div>
</section>
<script type="text/javascript" src="/static/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="/static/js/vertical_displacement.js"></script>
</body>
</html>
