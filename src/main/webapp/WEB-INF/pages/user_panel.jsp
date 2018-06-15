<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <div class="welcometext">
                You logged in as ${user.firstName} ${user.secondName}.
                <c:if test="${user.admin == true}">(user role)</c:if>
            </div>
            <a href="/railway/logout"><button class="logoutbutton">Log out</button></a>
            <c:if test="${user.admin == true}">
                <a href="/railway/admin/rolechange">
                    <button class="logoutbutton">Change role</button>
                </a>
            </c:if>
        </div>
        <div class="maindiv">
            <div class="accountdiv">
                <h1 class="text">
                    Menu
                </h1>
                <div class="buttonblock"><a href="/railway/user/trainsearch"><button class="button2">Train search</button></a></div>
                <div class="buttonblock"><a href="/railway/user/station"><button class="button2">Station info</button></a></div>
                <div class="buttonblock"><a href="/railway/user/purchasedtickets"><button class="button2">Purchased tickets</button></a></div>
            </div>
        </div>
    </section>
    <script type="text/javascript" src="/static/js/jquery-1.11.2.js"></script>
    <script type="text/javascript" src="/static/js/vertical_displacement.js"></script>
</body>
</html>