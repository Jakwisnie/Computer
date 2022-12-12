<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page session="false"%>
<html>
<head>
    <title></title>
</head>
<body>
<button onclick="document.location='../home'">Powrot</button>
Edycja komputera o id ${id}
<form action="${pageContext.request.contextPath}/updateAfter" method="post">
    Nazwa:<input type="text" name="name"value="${name}"><br>
    Cena USD: <input type="number" name="price_usd"value="${price_usd}"><br>
    <input type="submit" value="Zaktualizuj">
</form>
</body>
</html>