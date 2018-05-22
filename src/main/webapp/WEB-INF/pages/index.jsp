<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Intro page</title>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
</head>
<body>
<section class="logsection">
    <div class="logdiv">
        <h1 class="text">
            Hello, guest! To look at trains schedule or buy any tickets, you should log in or register
        </h1>
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
