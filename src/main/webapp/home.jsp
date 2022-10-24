<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page session="false"%>
<html>
<head>
    <title>Home</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/result" method="post">
    Name:<input type="text" name="name"><br>
    Price_USD: <input type="number" name="price_usd"><br>
    <input type="submit" value="To_PLN">
</form>
</body>
</html>