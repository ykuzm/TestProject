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
    <div class="incorrectlogdiv">
        <c:if test="${trainList == null}">
            <div class="incorrectinputtext">Sorry! You hadnt bought any tickets yet.</div>
        </c:if>
        <c:if test="${trainList != null}">
        <table class="table">
            <caption class="tablecaption">
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
                </tr>
            </c:forEach>
        </table>
        </c:if>
        <div class="buttonblock">
            <a href="/railway/account/buyticket"><button class="button">Buy another ticket</button></a>
            <a href="/railway/account"><button class="button">Account</button></a>
        </div>
    </div>
</section>
<script type="text/javascript" src="/static/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="/static/js/script_js.js"></script>
</body>
</html>