<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Train schedule</title>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
</head>
<body>
<section class="accountsection">
    <div class="incorrectlogdiv">
        <c:if test="${scheduleMap.size() == 0}">
            <div class="incorrectinputtext">Sorry! There are no schedule for train №${trainNumber} yet.</div>
        </c:if>
        <c:if test="${scheduleMap.size() > 0}">
            <table class="table">
                <caption class="tablecaption2">
                    Train №${trainNumber} schedule.
                </caption>
                <tr>
                    <th class="tablecell">
                        Station name
                    </th>
                    <th class="tablecell">
                        Departure time
                    </th>
                </tr>
                <c:forEach  var="scheduleMap" items="${scheduleMap}">
                    <tr>
                        <td class="tablecell">
                            <c:out value="${scheduleMap.key}" />
                        </td>
                        <td class="tablecell">
                            <fmt:formatDate value="${scheduleMap.value}" pattern="dd-MM-yyyy HH:mm" />
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <div class="buttonblock">
            <a href="/railway/account"><button class="button4">Account</button></a>
        </div>
    </div>
</section>
<script type="text/javascript" src="/static/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="/static/js/script_js.js"></script>
</body>
</html>
