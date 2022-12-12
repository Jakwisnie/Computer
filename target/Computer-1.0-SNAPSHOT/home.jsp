<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page session="false"%>
<html>
<head>
    <title></title>
</head>
<body>

<button onclick="document.location='addRandom'">Dodaj losowy</button><br>
<button onclick="document.location='view'">Wyswietl wszystkie</button><br>
<button onclick="document.location='add'">Dodaj pojedynczy</button><br>
<button onclick="document.location='facture'">Stwórz fakture</button>
<h3>Komputery z aktualnej sesji</h3>
<h3>${output}</h3>
</body>
</html>