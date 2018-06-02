<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Station info page</title>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
</head>
<body>
<section class="accountsection">
    <div class="incorrectlogdiv">
        <c:if test="${trainMap.size() == 0}">
            <div class="incorrectinputtext">Sorry! There are no trains going through station ${station.name}.</div>
        </c:if>
        <c:if test="${trainMap.size() > 0}">
        <table class="table">
            <caption>
                    Trains going through station ${station.name}.
            </caption>
            <tr>
                <th class="tablecell">
                    Train Number
                </th>
                <th class="tablecell">
                    Departure time
                </th>
            </tr>
            <c:forEach  var="trainMap" items="${trainMap}">
                <tr>
                    <td class="tablecell">
                        № <c:out value="${trainMap.key}" />
                    </td>
                    <td class="tablecell">
                        <c:out value="${trainMap.value}" />
                    </td>
                </tr>
            </c:forEach>
        </table>
        </c:if>
        <div class="buttonblock">
            <a href="/railway/passenger/station"><button class="button2">Station info</button></a>
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
