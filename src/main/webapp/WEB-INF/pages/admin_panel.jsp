<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin account</title>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
</head>
<body>
    <section class="accountsection">
        <div class="logoutdiv">
            <div class="welcometext">You logged in as ${user.firstName} ${user.secondName}! (admin role)</div>
            <a href="/railway/logout"><button class="logoutbutton">Log out</button></a>
            <a href="/railway/admin/rolechange"><button class="logoutbutton">Change role</button></a>
        </div>
        <div class="maindiv">
            <div class="adminaccountdiv">
                <h1 class="text">
                    Menu
                </h1>
                <div class="buttonblock"><a href="/railway/admin/stationadd"><button class="button3">Add station</button></a></div>
                <div class="buttonblock"><a href="/railway/admin/roadadd"><button class="button3">Add road</button></a></div>
                <div class="buttonblock"><a href="/railway/admin/trainadd"><button class="button3">Add train</button></a></div>
                <div class="buttonblock"><a href="/railway/admin/viewalltrains"><button class="button3">View all trains</button></a></div>
                <div class="buttonblock"><a href="/railway/admin/viewroadscheme"><button class="button3">View road scheme</button></a></div>
            </div>
        </div>
    </section>
    <script type="text/javascript" src="/static/js/jquery-1.11.2.js"></script>
    <script type="text/javascript" src="/static/js/vertical_displacement.js"></script>
</body>
</html>