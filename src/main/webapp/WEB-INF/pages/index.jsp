<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Intro page</title>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="/static/css/anytime_5.2.0.css" />
</head>
<body>
<section class="logsection">
    <div class="logoutdiv">
        <a href="/railway/login"><button class="logoutbutton">Log in</button></a>
        <a href="/railway/register"><button class="logoutbutton">Register</button></a>
    </div>
    <div class="maindiv">
        <div class="stationnamediv">
            <spring:form modelAttribute="trainSearch" method="post" action="/railway/trainsearch/info">
                <div class="trainnumberinputline">
                    <p>Departure station</p>
                    <spring:input id="stationname1" class="largeinputfield" title="name1" path="station1.name"
                                  onkeyup="javascript: checkStation1Name(); return false;" />
                </div>
                <div class="reghint">Use letters, numbers, - and space</div>
                <div class="trainnumberinputline">
                    <p>Destination station</p>
                    <spring:input id="stationname2" class="largeinputfield" title="name2" path="station2.name"
                                  onkeyup="javascript: checkStation2Name(); return false;" />
                </div>
                <div class="reghint">Use letters, numbers, - and space</div>
                <div class="trainnumberinputline">
                    <p>Departure date</p>
                    <spring:input id="departuredate" class="largeinputfield" title="departuredate" path="date"
                                  onchange="javascript: checkDepatureDate(); return false;" />
                    <div class="reghint">Pick departure date (e.g. 25-May-2018)</div>
                </div>
                <div class="buttonblock">
                    <spring:button id="button" class="button" disabled="true">Search trains</spring:button>
                </div>
            </spring:form>
        </div>
    </div>
</section>
<script type="text/javascript" src="/static/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="/static/js/vertical_displacement.js"></script>
<script type="text/javascript" src="/static/js/anytime_5.2.0.js"></script>
<script type="text/javascript" src="/static/js/train_search_valid_check.js"></script>
<script>
    AnyTime.picker( "departuredate",
        {
            format: "%d-%M-%Z", firstDOW: 1,
            earliest: new Date(),
            latest: new Date().setMonth(new Date().getMonth() + 1)
        } )
</script>
</body>
</html>
