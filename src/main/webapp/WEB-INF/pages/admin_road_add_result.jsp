<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Correct station add</title>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
</head>
<body>
<section class="accountsection">
    <div class="maindiv">
        <div class="incorrectlogdiv">
            <div class="incorrectinputtext">You have successfully added road from ${roadAdd.station1.name}
                 to ${roadAdd.station2.name}!</div>
            <div class="buttonblock">
                <a href="/railway/admin/roadadd"><button class="button2">Add another road</button></a>
            </div>
            <div class="buttonblock">
                <a href="/railway/admin"><button class="button2">Account</button></a>
            </div>
        </div>
    </div>
</section>
<script type="text/javascript" src="/static/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="/static/js/vertical_displacement.js"></script>
</body>
</html>
