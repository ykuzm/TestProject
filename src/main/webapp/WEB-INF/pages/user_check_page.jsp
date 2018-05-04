<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <spring:form modelAttribute="userfromserver" method="post" action="/userssystem/check">
        Name: <spring:input title="name" path="name" /> <br>
        Password: <spring:input title="password" path="password" /> <br>
        <spring:button>Check user</spring:button>
    </spring:form>
</body>
</html>
