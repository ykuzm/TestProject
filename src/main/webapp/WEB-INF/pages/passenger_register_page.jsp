<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
    <spring:form modelAttribute="regpassenger" method="post" action="/userssystem/register/result">
        Login: <spring:input title="Login" path="login" /> <br>
        First name: <spring:input title="First name" path="firstName" /> <br>
        Second name: <spring:input title="Second name" path="secondName" /> <br>
        Day: <spring:input title="Day" path="day" /> <br>
        Month: <spring:input title="Month" path="month" /> <br>
        Year: <spring:input title="Year" path="year" /> <br>
        Password: <spring:input title="Password" path="password" /> <br>
        <spring:button>Register</spring:button>
    </spring:form>
</body>
</html>
