<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log in</title>
</head>
<body>
    <spring:form modelAttribute="logpassenger" method="post" action="/userssystem/login/result">
        Login: <spring:input title="Login" path="login" /> <br>
        Password: <spring:input title="Password" path="password" /> <br>
        <spring:button>Log in!</spring:button>
    </spring:form>
</body>
</html>
