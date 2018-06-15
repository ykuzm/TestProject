<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Road scheme</title>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
</head>
<body>
<section class="accountsection">
    <div class="maindiv">
        <div class="incorrectinputtext">Road scheme</div>
        <div id="container" class="container" style="width: 800px; height: 550px; background-color: lightgray;
         margin: 0px auto"></div>
        <div class="buttonblock">
            <a href="/railway/admin"><button class="button">Account</button></a>
        </div>
    </div>
</section>
<script type="text/javascript" src="/static/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="/static/js/graphics_min.js"></script>
<script type="text/javascript" src="/static/js/vertical_displacement.js"></script>
<script>
    var stage = acgraph.create('container');
    var rectWidth = $('.container').width();
    var rectHeight = $('.container').height();
    var gap = rectWidth/50;
    var frame = stage.rect(gap, gap, rectWidth - 2*gap, rectHeight - 2*gap);
    var kx = (rectWidth - 2*gap) / (${rightCoordX} - ${leftCoordX} + 4);
    var ky = (rectHeight - 2*gap) / (${bottomCoordY} - ${topCoordY} + 4);
    layer_text = stage.layer();
    <c:forEach var="station" items="${stations}">
        stage.circle(gap + (${station.coordX} - ${leftCoordX} + 2)*kx, gap + (${station.coordY} -
                ${topCoordY} + 2)*ky, 5).fill("black");
        layer_text.text(gap + (${station.coordX} - ${leftCoordX} + 2)*kx - ${station.name.length()}*7.5/2,
            gap + (${station.coordY} - ${topCoordY} + 2)*ky + 3, '${station.name}',
            {fontSize: 15,
             color: "green",
             opacity: 20});
    </c:forEach>
    <c:forEach var="road" items="${roadCoordScheme}">
        stage.path().moveTo(gap + (${road.coordX1} - ${leftCoordX} + 2)*kx,
            gap + (${road.coordY1} - ${topCoordY} + 2)*ky)
            .lineTo(gap + (${road.coordX2}- ${leftCoordX} + 2)*kx,
            gap + (${road.coordY2} - ${topCoordY} + 2)*ky).close();
    </c:forEach>
</script>
</body>
</html>
