<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Personal account</title>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
</head>
<body>
    <section class="accountsection">
        <div class="logoutdiv">
            <div class="welcometext">You logged in as ${passenger.firstName} ${passenger.secondName}.</div>
            <a href="/railway/logout"><button class="logoutbutton">Log out</button></a>
        </div>
        <div class="accountdiv">
            <h1 class="text">
                Menu
            </h1>
            <div class="buttonblock"><a href="/railway/account/trainsearch"><button class="button2">Train search</button></a></div>
            <div class="buttonblock"><a href="/railway/account/station"><button class="button2">Station info</button></a></div>
            <div class="buttonblock"><a href="/railway/account/buyticket"><button class="button2">Buy ticket</button></a></div>
            <div class="buttonblock"><a href="/railway/account/purchasedtickets"><button class="button2">Purchased tickets</button></a></div>
        </div>
    </section>
    <script type="text/javascript" src="/static/js/jquery-1.11.2.js"></script>
    <script type="text/javascript" src="/static/js/script_js.js"></script>
</body>
</html>