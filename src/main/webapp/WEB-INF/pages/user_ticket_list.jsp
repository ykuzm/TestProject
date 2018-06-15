<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Your list of tickets!</title>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
</head>
<body>
<section class="accountsection">
    <div class="maindiv">
        <div class="incorrectlogdiv">
            <c:if test="${trainList.size() == 0}">
                <div class="incorrectinputtext">Sorry! You hadnt bought any tickets yet.</div>
            </c:if>
            <c:if test="${trainList.size() != 0}">
                <table class="table">
                    <caption class="tablecaption2">
                        Train numbers, which you purchsed tickets on.
                    </caption>
                    <tr>
                        <th class="tableheader">Train number</th>
                    </tr>
                    <c:forEach  var="train" items="${trainList}">
                        <tr>
                            <td class="tablecell">
                                № <c:out value="${train.number}" />
                            </td>
                            <td>
                                <a href="/railway/user/trainschedule-${train.number}"><button class="button5">Full schedule</button></a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
            <div class="buttonblock">
                <c:if test="${role == 'admin'}">
                    <a href="/railway/admin"><button class="button">Account</button></a>
                </c:if>
                <c:if test="${role == 'user'}">
                    <a href="/railway/user"><button class="button">Account</button></a>
                </c:if>
            </div>
        </div>
    </div>
</section>
<script type="text/javascript" src="/static/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="/static/js/vertical_displacement.js"></script>
</body>
</html>
