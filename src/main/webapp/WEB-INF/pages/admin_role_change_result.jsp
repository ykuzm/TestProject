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
<section class="accountsection">
    <div class="incorrectlogdiv">
        <div class="loginsuccessfullpage">Role successfully changed!</div>
        <c:if test="${role == true}">
            <meta http-equiv="refresh" content="1;http://localhost:8080/railway/admin" />
        </c:if>
        <c:if test="${role == false}">
            <meta http-equiv="refresh" content="1;http://localhost:8080/railway/passenger" />
        </c:if>
    </div>
</section>
<script type="text/javascript" src="/static/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="/static/js/script_js.js"></script>
</body>
</html>
