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
    <div class="incorrectlogdiv">
        <div class="incorrectinputtext">Sorry, but your input data is incorrect. Follow the instructions!</div>
        <div class="buttonblock">
            <a href="/railway/login"><button class="button">Log in</button></a>
            <a href="/railway/register"><button class="button">Registration</button></a>
        </div>
    </div>
</section>
<script type="text/javascript" src="/static/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="/static/js/script_js.js"></script>
</body>
</html>

