<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Incorrect login</title>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
</head>
<body>
<section class="logsection">
    <div class="maindiv">
        <div class="incorrectlogdiv">
            <div class="incorrectinputtext">Sorry! But your pair login-password is incorrect.
                Try again to log in or register.</div>
            <div class="buttonblock">
                <a href="/railway/login"><button class="button2">Log in</button></a>
            </div>
            <div class="buttonblock">
                <a href="/railway/register"><button class="button2">Registration</button></a>
            </div>
            <div class="buttonblock">
                <a href="/railway"><button class="button2">Start page</button></a>
            </div>
        </div>
    </div>
</section>
<script type="text/javascript" src="/static/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="/static/js/vertical_displacement.js"></script>
</body>
</html>
