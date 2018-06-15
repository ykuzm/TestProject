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
    <div class="maindiv">
        <div class="incorrectlogdiv">
            <c:if test="${userList.size() == 0}">
                <div class="incorrectinputtext">Sorry! There are no passengers on train №${trainNumber}.</div>
            </c:if>
            <c:if test="${userList.size() > 0}">
                <table class="table">
                    <caption class="tablecaption2">
                        Information about passengers on train №${trainNumber}.
                    </caption>
                    <tr>
                        <th class="tableheader">Firstname</th>
                        <th class="tableheader">Secondname</th>
                        <th class="tableheader">Birthdate</th>
                    </tr>
                    <c:forEach  var="userList" items="${userList}">
                        <tr>
                            <td class="tablecell">
                                <c:out value="${userList.firstName}" />
                            </td>
                            <td class="tablecell">
                                <c:out value="${userList.secondName}" />
                            </td>
                            <td class="tablecell">
                                <fmt:formatDate value="${userList.birthDate}" pattern="dd-MM-yyyy" />
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
            <div class="buttonblock">
                <a href="/railway/admin/viewalltrains"><button class="button4">Choose another train</button></a>
            </div>
            <div class="buttonblock">
                <a href="/railway/admin"><button class="button4">Account</button></a>
            </div>
        </div>
    </div>
</section>
<script type="text/javascript" src="/static/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="/static/js/vertical_displacement.js"></script>
</body>
</html>
