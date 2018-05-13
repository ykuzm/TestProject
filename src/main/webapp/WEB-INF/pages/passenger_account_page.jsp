<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Personal account</title>
    <link rel="stylesheet" href="../../static/css/style.css" type="text/css"/>
    <script type="text/javascript" src="../../static/js/jquery-1.11.2.js"></script>
    <script type="text/javascript" src="../../static/js/script_js.js"></script>
</head>
<body>
    <section class="accountsection">
        <div class="logoutdiv">
            <div class="welcometext">Welcome, ${passenger.firstName} ${passenger.secondName}!</div>
            <a href="/railway/"><button class="logoutbutton">Log out</button></a>
        </div>
        <div class="accountdiv">
            <div class="buttonblock"><a href="/railway/trainsearch"><button class="button2">Train search</button></a></div>
            <div class="buttonblock"><a href="/railway/stationinfo"><button class="button2">Station info</button></a></div>
            <div class="buttonblock"><a href="/railway/buyticket"><button class="button2">Buy ticket</button></a></div>
            <div class="buttonblock"><a href="/railway/purchasedtickets"><button class="button2">Purchased tickets</button></a></div>
        </div>
    </section>
</body>
</html>