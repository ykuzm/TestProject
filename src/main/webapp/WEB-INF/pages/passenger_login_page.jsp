<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Log in</title>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
</head>
<body>
<section class="logsection">
    <div class="logdiv">
        <h1 class="text">
            Log in form
        </h1>
        <spring:form modelAttribute="passenger" method="post" action="/railway/login/result">
            <div class="loginputline">
                <p>Login</p>
                <spring:input id="loglogininput" class="inputfield" name="login" title="Login" path="login"
                              onkeyup="javascript: checkLogin(); return false;" />
            </div>
            <div class="reghint">Use only letters and numbers, 4 symbols at least</div>
            <div class="loginputline">
                <p>Password</p>
                <spring:input id="logpasswordinput" class="inputfield" name="password" title="Password" path="password"
                              onkeyup="javascript: checkPassword(); return false;" />
            </div>
            <div class="reghint">Use only letters and number, 4 symbols at least</div>
            <div class="buttonblock">
                <spring:button id="logbutton" class="button" disabled="true">Submit</spring:button>
            </div>
        </spring:form>
        <div class="buttonblock">
            <a href="/railway/"><button class="button">Start page</button></a>
        </div>
    </div>
</section>
<script type="text/javascript" src="/static/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="/static/js/script_js.js"></script>
<script type="text/javascript" src="/static/js/input_log_valid_check.js"></script>
</body>
</html>
