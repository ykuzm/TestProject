<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Station info page</title>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
</head>
<body>
<section class="accountsection">
    <div class="maindiv">
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
                                <fmt:formatDate value="${trainMap.value}" pattern="dd-MM-yyyy HH:mm" />
                            </td>
                            <td>
                                <a href="/railway/user/trainschedule-${trainMap.key}"><button class="button5">Full schedule</button></a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
            <div class="buttonblock">
                <c:if test="${role == 'admin'}">
                    <a href="/railway/admin"><button class="button2">Account</button></a>
                </c:if>
                <c:if test="${role == 'user'}">
                    <a href="/railway/user"><button class="button2">Account</button></a>
                </c:if>
            </div>
        </div>
    </div>
</section>
<script type="text/javascript" src="/static/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="/static/js/vertical_displacement.js"></script>
</body>
</html>
