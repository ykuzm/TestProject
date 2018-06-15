<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add station</title>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
</head>
<body>
<section class="accountsection">
    <div class="maindiv">
        <div class="stationnamediv">
            <h1 class="text">
                Station add
            </h1>
            <spring:form modelAttribute="roadAdd" method="post" action="/railway/admin/roadadd/result">
                <div class="trainnumberinputline">
                    <p>First station name</p>
                    <spring:input id="stationname1" class="largeinputfield" title="name" path="station1.name"
                                  onkeyup="javascript: checkStationName1(); return false;" />
                </div>
                <div class="reghint">Use only letters and -, first letter in upper-case</div>
                <div class="trainnumberinputline">
                    <p>Second station name</p>
                    <spring:input id="stationname2" class="largeinputfield" title="name" path="station2.name"
                                  onkeyup="javascript: checkStationName2(); return false;" />
                </div>
                <div class="reghint">Use only letters and -, first letter in upper-case</div>
                <div class="buttonblock">
                    <spring:button id="button" class="button" disabled="true">Add road</spring:button>
                </div>
            </spring:form>
            <div class="buttonblock">
                <a href="/railway/admin"><button class="button">Account</button></a>
            </div>
        </div>
    </div>
</section>
<script type="text/javascript" src="/static/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="/static/js/vertical_displacement.js"></script>
<script type="text/javascript" src="/static/js/road_add_valid_check.js"></script>
</body>
</html>
