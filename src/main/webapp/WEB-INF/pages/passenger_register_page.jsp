<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
    <link rel="stylesheet" href="../../static/css/style.css" type="text/css"/>
    <script type="text/javascript" src="../../static/js/jquery-1.11.2.js"></script>
    <script type="text/javascript" src="../../static/js/script_js.js"></script>
</head>
<body>
<section class="logsection">
    <div class="regdiv">
        <spring:form modelAttribute="passenger" method="post" action="/railway/register/result">
            <div class="reginputline">
                <p>Login</p>
                <spring:input class="inputfield" title="Login" path="login" />
            </div>
            <div class="reghint">Use only letters and numbers</div>
            <div class="reginputline">
                <p>First name</p>
                <spring:input class="inputfield" title="First name" path="firstName" />
                <div class="reghint">Use only letters, first letter in upper-case</div>
            </div>
            <div class="reginputline">
                <p>Second name</p>
                <spring:input class="inputfield" title="Second name" path="secondName" />
                <div class="reghint">Use only letters, first letter in upper-case</div>
            </div>
            <div class="reginputline">
                <p>Day</p>
                <spring:input class="inputfield" title="Day" path="day" />
                <div class="reghint">Number should be between 1 and 31</div>
            </div>
            <div class="reginputline">
                <p>Month</p>
                <spring:input class="inputfield" title="Month" path="month" />
                <div class="reghint">Number should be between 1 and 12</div>
            </div>
            <div class="reginputline">
                <p>Year</p>
                <spring:input class="inputfield" title="Year" path="year" />
                <div class="reghint">Number should be between 1900 and 2000</div>
            </div>
            <div class="reginputline">
                <p>Password</p>
                <spring:input class="inputfield" title="Password" path="password" />
                <div class="reghint">Use only letters and numbers</div>
            </div>
            <div class="buttonblock">
                <spring:button class="button">Register</spring:button>
            </div>
        </spring:form>
    </div>
</section>
</body>
</html>
