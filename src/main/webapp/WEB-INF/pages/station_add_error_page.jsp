<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>You cant add station!</title>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
</head>
<body>
<section class="accountsection">
    <div class="incorrectlogdiv">
        <div class="incorrectinputtext">Sorry! ${exception}</div>
        <div class="buttonblock">
            <a href="/railway/account/addstation"><button class="button">Add station</button></a>
            <a href="/railway/account"><button class="button">Account</button></a>
        </div>
    </div>
</section>
<script type="text/javascript" src="/static/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="/static/js/script_js.js"></script>
</body>
</html>
