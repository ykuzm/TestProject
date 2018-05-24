<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Passenger info</title>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
</head>
<body>
<section class="accountsection">
    <div class="incorrectlogdiv">
        <c:if test="${passengerList.size() == 0}">
            <div class="incorrectinputtext">Sorry! There are no passengers on train №${trainNumber}.</div>
        </c:if>
        <c:if test="${passengerList.size() > 0}">
        <table class="table">
            <caption class="tablecaption2">
                Information about passengers on train №${trainNumber}.
            </caption>
            <tr>
                <th class="tableheader">Firstname</th>
                <th class="tableheader">Secondname</th>
                <th class="tableheader">Birthdate</th>
            </tr>
            <c:forEach  var="passengerList" items="${passengerList}">
                <tr>
                    <td class="tablecell">
                        <c:out value="${passengerList.firstName}" />
                    </td>
                    <td class="tablecell">
                        <c:out value="${passengerList.secondName}" />
                    </td>
                    <td class="tablecell">
                        <fmt:formatDate value="${passengerList.birthDate}" pattern="dd-MM-yyyy" />
                    </td>
                </tr>
            </c:forEach>
        </table>
        </c:if>
        <div class="buttonblock">
            <a href="/railway/account/viewalltrains"><button class="button4">Choose another train</button></a>
        </div>
        <div class="buttonblock">
            <a href="/railway/account"><button class="button4">Account</button></a>
        </div>
    </div>
</section>
<script type="text/javascript" src="/static/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="/static/js/script_js.js"></script>
</body>
</html>