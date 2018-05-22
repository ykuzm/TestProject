<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>There is no such station</title>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
</head>
<body>
<section class="accountsection">
    <div class="incorrectlogdiv">
        <div class="incorrectinputtext">Sorry! ${exception}</div>
        <div class="buttonblock">
            <a href="/railway/account/trainsearch"><button class="button4">Train search</button></a>
        </div>
        <div class="buttonblock">
            <a href="/railway/account/station"><button class="button4">Station info</button></a>
        </div>
        <div class="buttonblock">
            <a href="/railway/account"><button class="button4">Account</button></a>
        </div>
    </div>
</section>
<script type="text/javascript" src="/static/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="/static/js/script_js.js"></script>
</body>
</html>
