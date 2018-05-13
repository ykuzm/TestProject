<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Account already exists</title>
    <link rel="stylesheet" href="../../static/css/style.css" type="text/css"/>
    <script type="text/javascript" src="../../static/js/jquery-1.11.2.js"></script>
    <script type="text/javascript" src="../../static/js/script_js.js"></script>
</head>
<body>
<section class="logsection">
    <div class="incorrectlogdiv">
        <div class="incorrectinputtext">Sorry! But account with this login already exists. Try another one.</div>
        <div class="buttonblock">
            <a href="/railway/register"><button class="button">Registration</button></a>
        </div>
    </div>
</section>
</body>
</html>
