<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Scedule of trains</title>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
</head>
<body>
<section class="accountsection">
    <div class="scheduleinfodiv">
        <c:if test="${trainMap.size() == 0}">
            <div class="incorrectinputtext">Sorry! There are no trains going from station ${station1.name} to
                 ${station2.name} on ${date}.</div>
        </c:if>
        <c:if test="${trainMap.size() > 0}">
        <table class="table">
            <caption class="tablecaption2">
                Trains going from station ${station1.name} to station ${station2.name} on ${date}.
            </caption>
            <tr>
                <th class="tableheader">Train number</th>
                <th class="tableheader">Departure time</th>
            </tr>
            <c:forEach  var="train" items="${trainMap}">
                <tr>
                    <td class="tablecell">
                        № <c:out value="${train.key}" />
                    </td>
                    <td class="tablecell">
                        <c:out value="${train.value}" />
                    </td>
                    <td>
                        <a href="/railway/passenger/buyticket/result-${train.key}"><button class="button5">Buy ticket</button></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        </c:if>
        <div class="buttonblock">
            <a href="/railway/passenger/trainsearch"><button class="button2">Train search</button></a>
        </div>
        <div class="buttonblock">
            <c:if test="${role == true}">
                <a href="/railway/admin"><button class="button2">Account</button></a>
            </c:if>
            <c:if test="${role == false}">
                <a href="/railway/passenger"><button class="button2">Account</button></a>
            </c:if>
        </div>
    </div>
</section>
<script type="text/javascript" src="/static/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="/static/js/script_js.js"></script>
</body>
</html>
