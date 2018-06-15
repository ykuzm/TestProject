<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Scedule of trains</title>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
</head>
<body>
<section class="accountsection">
    <c:if test="${role == null}">
        <div class="logoutdiv">
            <a href="/railway/login"><button class="logoutbutton">Log in</button></a>
            <a href="/railway/register"><button class="logoutbutton">Register</button></a>
        </div>
    </c:if>
    <div class="maindiv">
        <div class="scheduleinfodiv">
            <c:if test="${trainMap.size() == 0}">
                <div class="incorrectinputtext">Sorry! There are no trains going from station ${trainSearch.station1.name}
                    to ${trainSearch.station2.name} on <fmt:formatDate value="${trainSearch.date}" pattern="dd-MM-yyyy" />.</div>
            </c:if>
            <c:if test="${trainMap.size() > 0}">
                <table class="table">
                    <caption class="tablecaption2">
                        Trains going from station ${trainSearch.station1.name} to station ${trainSearch.station2.name} on
                        <fmt:formatDate value="${trainSearch.date}" pattern="dd-MM-yyyy" />
                    </caption>
                    <tr>
                        <th class="tableheader">Train number</th>
                        <th class="tableheader">Departure time</th>
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
                                <a href="/railway/trainschedule-${trainMap.key}"><button class="button5">Full schedule</button></a>
                            </td>
                            <c:if test="${role == 'user'}">
                                <td>
                                    <a href="/railway/user/buyticket+${trainMap.key}+${trainSearch.station1.name}+${trainSearch.station2.name}">
                                        <button class="button5">Buy ticket</button>
                                    </a>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
            <div class="buttonblock">
                <c:if test="${role != null}">
                    <a href="/railway/user/trainsearch"><button class="button2">Train search</button></a>
                </c:if>
            </div>
            <div class="buttonblock">
                <c:if test="${role == 'admin'}">
                    <a href="/railway/admin"><button class="button2">Account</button></a>
                </c:if>
                <c:if test="${role == 'user'}">
                    <a href="/railway/user"><button class="button2">Account</button></a>
                </c:if>
                <c:if test="${role == null}">
                    <a href="/railway"><button class="button2">Start page</button></a>
                </c:if>
            </div>
        </div>
    </div>
</section>
<script type="text/javascript" src="/static/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="/static/js/vertical_displacement.js"></script>
</body>
</html>
