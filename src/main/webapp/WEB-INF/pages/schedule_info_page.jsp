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
    <div class="incorrectlogdiv">
        <c:if test="${trainMap.size() == 0}">
            <div class="incorrectinputtext">Sorry! There are no trains going from station ${station1.name} to
                 ${station2.name} on ${date}.</div>
        </c:if>
        <c:if test="${trainMap.size() > 0}">
        <table class="table">
            <caption class="tablecaption">
                Trains going from station ${station1.name} to ${station2.name} on ${date}.
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
                </tr>
            </c:forEach>
        </table>
        </c:if>
        <div class="buttonblock">
            <a href="/railway/account/trainsearch"><button class="button">Train search</button></a>
            <a href="/railway/account"><button class="button">Account</button></a>
        </div>
    </div>
</section>
<script type="text/javascript" src="/static/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="/static/js/script_js.js"></script>
</body>
</html>
