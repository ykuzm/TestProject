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
            <div class="welcometext">You logged in as ${passenger.firstName} ${passenger.secondName}! (admin role)</div>
            <a href="/railway/logout"><button class="logoutbutton">Log out</button></a>
            <a href="/railway/admin/rolechange"><button class="logoutbutton">Change role</button></a>
        </div>
        <div class="adminaccountdiv">
            <h1 class="text">
                Menu
            </h1>
            <div class="buttonblock"><a href="/railway/admin/addstation"><button class="button3">Add station</button></a></div>
            <div class="buttonblock"><a href="/railway/admin/addtrain"><button class="button3">Add train</button></a></div>
            <div class="buttonblock"><a href="/railway/admin/trainpassenger"><button class="button3">Train passengers</button></a></div>
            <div class="buttonblock"><a href="/railway/admin/viewalltrains"><button class="button3">View all trains</button></a></div>
        </div>
    </section>
    <script type="text/javascript" src="/static/js/jquery-1.11.2.js"></script>
    <script type="text/javascript" src="/static/js/script_js.js"></script>
</body>
</html>