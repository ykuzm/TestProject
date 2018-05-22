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
            <a href="/railway/account/station"><button class="button">Station info</button></a>
            <a href="/railway/account"><button class="button">Account</button></a>
        </div>
    </div>
</section>
<script type="text/javascript" src="/static/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="/static/js/script_js.js"></script>
</body>
</html>
