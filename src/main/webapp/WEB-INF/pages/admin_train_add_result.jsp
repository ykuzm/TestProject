<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Correct train add</title>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
</head>
<body>
<section class="accountsection">
    <div class="maindiv">
        <div class="incorrectlogdiv">
            <div class="incorrectinputtext">You have successfully added train number ${trainAdd.train.number}
                (${trainAdd.train.capacity} seats, ${trainAdd.train.velocity} velocity)
                from station ${trainAdd.station1.name} to station ${trainAdd.station2.name}!</div>
            <div class="buttonblock">
                <a href="/railway/user/trainschedule-${trainAdd.train.number}">
                    <button class="button2">View full schedule</button></a>
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
