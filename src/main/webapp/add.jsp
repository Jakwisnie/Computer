<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page session="false"%>
<html>
<head>
    <title></title>
</head>
<body>
<button onclick="document.location='home'">Powrot</button>
<form action="${pageContext.request.contextPath}/result" method="post">
    Nazwa:<input type="text" name="name"><br>
    Cena usd: <input type="number" name="price_usd"><br>
    <select name="date" id="date">
        <option value="a">3 Stycznia 2022</option>
        <option value="b">10 Stycznia 2022</option>
    </select><br>
    <input type="submit" value="To_PLN">
</form>
</body>
</html>