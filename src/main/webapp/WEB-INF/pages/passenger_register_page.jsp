<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="/static/css/jquery_ui.css" />
</head>
<body>
<section class="logsection">
    <div class="regdiv">
        <h1 class="text">
            Registration form
        </h1>
        <spring:form modelAttribute="passenger" method="post" action="/railway/register/result">
            <div class="reginputline">
                <p>Login</p>
                <spring:input id="reglogininput" class="reginputfield" title="Login" path="login"
                              onkeyup="javascript: checkLogin(); return false;" />
            </div>
            <div class="reghint">Use only letters and numbers, 4 symbols at least</div>
            <div class="reginputline">
                <p>First name</p>
                <spring:input id="regfnameinput" class="reginputfield" title="First name" path="firstName"
                              onkeyup="javascript: checkFName(); return false;" />
                <div class="reghint">Use only letters, first letter in upper-case</div>
            </div>
            <div class="reginputline">
                <p>Second name</p>
                <spring:input id="regsnameinput" class="reginputfield" title="Second name" path="secondName"
                              onkeyup="javascript: checkSName(); return false;" />
                <div class="reghint">Use only letters, first letter in upper-case</div>
            </div>
            <div class="reginputline">
                <p>Birth date</p>
                <spring:input id="regbirthdateinput" class="reginputfield" title="BirthDate" path="birthDate"
                              onchange="javascript: checkBirthDate(); return false;" />
                <div class="reghint">Pick the your birth date (e.g. 03-May-1984)</div>
            </div>
            <div class="reginputline">
                <p>Password</p>
                <spring:input id="regpasswordinput" class="reginputfield" title="Password" path="password"
                              onkeyup="javascript: checkPassword(); return false;" />
                <div class="reghint">Use only letters and number, 4 symbols at least</div>
            </div>
            <div class="buttonblock">
                <spring:button id="regbutton" class="button" disabled="true">Register</spring:button>
            </div>
        </spring:form>
        <div class="buttonblock">
            <a href="/railway/"><button class="button">Start page</button></a>
        </div>
    </div>
</section>
    <script type="text/javascript" src="/static/js/jquery-1.11.2.js"></script>
    <script type="text/javascript" src="/static/js/jquery_ui_v1.9.2.js"></script>
    <script type="text/javascript" src="/static/js/script_js.js"></script>
    <script type="text/javascript" src="/static/js/input_reg_valid_check.js"></script>
    <script>
        $(function() {
           $( "#regbirthdateinput" ).datepicker({dateFormat:"dd-MM-yy"});
        });
    </script>
</body>
</html>