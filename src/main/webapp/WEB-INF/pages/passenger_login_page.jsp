<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Log in</title>
    <link rel="stylesheet" href="../../static/css/style.css" type="text/css"/>
    <script type="text/javascript" src="../../static/js/jquery-1.11.2.js"></script>
    <script type="text/javascript" src="../../static/js/script_js.js"></script>
</head>
<body>
<section class="logsection">
    <div class="logdiv">
        <spring:form modelAttribute="passenger" method="post" action="/railway/login/result">
            <div class="loginputline">
                <p>Login</p>
                <spring:input class="inputfield" title="Login" path="login" />
            </div>
            <div class="loginputline">
                <p>Password</p>
                <spring:input class="inputfield" title="Password" path="password" />
            </div>
            <div class="buttonblock">
                <spring:button class="button">Submit</spring:button>
            </div>
        </spring:form>
    </div>
</section>
</body>
</html>
