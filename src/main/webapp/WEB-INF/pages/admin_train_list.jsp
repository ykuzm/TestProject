<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Full list of trains</title>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
</head>
<body>
<section class="accountsection">
    <div class="maindiv">
        <div class="trainlistdiv">
            <c:if test="${trainList == null}">
                <div class="incorrectinputtext">Sorry! No trains in the system.</div>
            </c:if>
            <c:if test="${trainList != null}">
                <table class="table">
                    <caption class="tablecaption2">
                        All registered trains.
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
                                <a href="/railway/user/trainschedule-${train.number}"><button class="button5">View schedule</button></a>
                            </td>
                            <td>
                                <a href="/railway/admin/trainuser-${train.number}"><button class="button5">View passengers</button></a>
                            </td>
                            <td>
                                <a href="/railway/admin/addschedule-${train.number}"><button class="button5">Add schedule</button></a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
            <div class="buttonblock">
                <a href="/railway/admin"><button class="button">Account</button></a>
            </div>
        </div>
    </div>
</section>
<script type="text/javascript" src="/static/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="/static/js/vertical_displacement.js"></script>
</body>
</html>
