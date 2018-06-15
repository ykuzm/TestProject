<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="/static/css/anytime_5.2.0.css" />
</head>
<body>
<section class="logsection">
    <div class="maindiv">
        <div class="regdiv">
            <h1 class="text">
                Registration form
            </h1>
            <spring:form modelAttribute="user" method="post" action="/railway/register/result">
                <div class="reginputline">
                    <p>Login</p>
                    <spring:input id="login" class="reginputfield" title="Login" path="login"
                                  onkeyup="javascript: checkLogin(); return false;" />
                </div>
                <div class="reghint">Use only letters and numbers, 4 symbols at least</div>
                <div class="reginputline">
                    <p>First name</p>
                    <spring:input id="firstname" class="reginputfield" title="First name" path="firstName"
                                  onkeyup="javascript: checkFName(); return false;" />
                    <div class="reghint">Use only letters, first letter in upper-case</div>
                </div>
                <div class="reginputline">
                    <p>Second name</p>
                    <spring:input id="secondname" class="reginputfield" title="Second name" path="secondName"
                                  onkeyup="javascript: checkSName(); return false;" />
                    <div class="reghint">Use only letters, first letter in upper-case</div>
                </div>
                <div class="reginputline">
                    <p>Birth date</p>
                    <spring:input id="birthdate" class="reginputfield" title="BirthDate" path="birthDate"
                                  onchange="javascript: checkBirthDate(); return false;" />
                    <div class="reghint">Pick the your birth date (e.g. 03-May-1984)</div>
                </div>
                <div class="reginputline">
                    <p>Password</p>
                    <spring:input id="password" class="reginputfield" type="password" title="Password" path="password"
                                  onkeyup="javascript: checkPassword(); return false;" />
                    <div class="reghint">Use only letters and number, 4 symbols at least</div>
                </div>
                <div class="reginputline">
                    <p>Password confirm</p>
                    <spring:input id="passwordconfirm" class="reginputfield" type="password" title="PasswordConfirm" path="passwordConfirm"
                                  onkeyup="javascript: checkPasswordConfirm(); return false;" />
                    <div class="reghint">Repeat your password</div>
                </div>
                <div class="buttonblock">
                    <spring:button id="button" class="button" disabled="true">Register</spring:button>
                </div>
            </spring:form>
            <div class="buttonblock">
                <a href="/railway/"><button class="button">Start page</button></a>
            </div>
        </div>
    </div>
</section>
<script type="text/javascript" src="/static/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="/static/js/vertical_displacement.js"></script>
<script type="text/javascript" src="/static/js/anytime_5.2.0.js"></script>
<script type="text/javascript" src="/static/js/registration_valid_check.js"></script>
<script>
    AnyTime.picker( "birthdate",
        {
            format: "%d-%M-%Z", firstDOW: 1,
            earliest: new Date(1900, 0, 1),
            latest: new Date().setFullYear(new Date().getFullYear() - 18)
        } )
</script>
</body>
</html>