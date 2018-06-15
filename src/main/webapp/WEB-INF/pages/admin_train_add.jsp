<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add train</title>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="/static/css/anytime_5.2.0.css" />
</head>
<body>
<section class="accountsection">
    <div class="maindiv">
        <div class="stationnamediv">
            <h1 class="text">
                Train add panel
            </h1>
            <spring:form modelAttribute="trainAdd" multiple="true" method="post" action="/railway/admin/trainadd/result">
                <div class="trainnumberinputline">
                    <p>Train number</p>
                    <spring:input id="trainnumber" class="largeinputfield" title="number" path="train.number"
                                  onkeyup="javascript: checkTrainNumber(); return false;" />
                </div>
                <div class="reghint">Input train number (from 1 to 999)</div>
                <div class="trainnumberinputline">
                    <p>Number of seats</p>
                    <spring:input id="capacity" class="largeinputfield" title="capacity" path="train.capacity"
                                  onkeyup="javascript: checkTrainCapacity(); return false;" />
                </div>
                <div class="reghint">Input number of seats (from 1 to 999)</div>
                <div class="trainnumberinputline">
                    <p>Velocity</p>
                    <spring:input id="velocity" class="largeinputfield" title="velocity" path="train.velocity"
                                  onkeyup="javascript: checkTrainVelocity(); return false;" />
                </div>
                <div class="reghint">Input train velocity (unit per hour from 0.1 to 1.0)</div>
                <div class="trainnumberinputline">
                    <p>First station name</p>
                    <spring:input id="stationname1" class="largeinputfield" title="name" path="station1.name"
                                  onkeyup="javascript: checkStationName1(); return false;" />
                </div>
                <div class="reghint">Use letters, numbers, - and space</div>
                <div class="trainnumberinputline">
                    <p>Second station name</p>
                    <spring:input id="stationname2" class="largeinputfield" title="name" path="station2.name"
                                  onkeyup="javascript: checkStationName2(); return false;" />
                </div>
                <div class="reghint">Use letters, numbers, - and space</div>
                <div class="trainnumberinputline">
                    <p>Departure date</p>
                    <spring:input id="departuredate" class="largeinputfield" title="departuredate" path="departureDate"
                                  onchange="javascript: checkDepatureDate(); return false;" />
                    <div class="reghint">Pick departure date (e.g. 25-May-2018)</div>
                </div>
                <div class="buttonblock">
                    <spring:button id="button" class="button" disabled="true">Add train</spring:button>
                </div>
            </spring:form>
            <div class="buttonblock">
                <a href="/railway/admin"><button class="button">Account</button></a>
            </div>
        </div>
    </div>
</section>
<script type="text/javascript" src="/static/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="/static/js/anytime_5.2.0.js"></script>
<script type="text/javascript" src="/static/js/vertical_displacement.js"></script>
<script type="text/javascript" src="/static/js/train_add_valid_check.js"></script>
<script>
    AnyTime.picker( "departuredate",
        {
            format: "%d-%M-%Z %H:%i", firstDOW: 1,
            earliest: new Date().setDate(new Date().getDate() + 1),
            latest: new Date().setMonth(new Date().getMonth() + 2)
        } )
</script>
</body>
</html>
