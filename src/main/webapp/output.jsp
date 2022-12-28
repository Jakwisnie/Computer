<%--
  Created by IntelliJ IDEA.
  User: komp
  Date: 24.10.2022
  Time: 21:30
  To change this template use File | Settings | File Templates.
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="app.Computer"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<button onclick="document.location='home'">Powrot</button><br>
Zobacz wszystkie
<button onclick="document.location='viewbydateasc'">Data rosnąco</button>
<button onclick="document.location='viewbydatedesc'">Data malejaco</button>
<button onclick="document.location='viewbynameasc'">Nazwa rosnaco</button>
<button onclick="document.location='viewbynamedesc'">Nazwa malejaco</button>

<form action="${pageContext.request.contextPath}/findbytext" method="POST">
    Nazwa:<input type="text" name="name"><br>
    <input type="submit" value="Find">
</form>

<%
    Computer[] computers = ((Computer[])request.getAttribute("computers"));

    for(Computer computer : computers )
    {
        out.print("Id: " + computer.getId());
        out.print("<br/>");
        out.print("Nazwa: " + computer.getName());
        out.print("<br/>");
        out.print("Data ksiegowania: " + computer.getDate());
        out.print("<br/>");
        out.print("Cena PLN: " + computer.getPrice_pln());
        out.print("<br/>");
        out.print("Cena USD: " + computer.getPrice_usd());
        out.print("<br/>");
        out.print("<input type='button' onclick=location.href='update/"+computer.getId()+"' name='update'  value='update' />");
        out.print("<br/>");
        out.print("<input type='button' onclick=location.href='delete/"+computer.getId()+"' name='delete'  value='delete' />");
        out.print("<br/>");
    }




%>
</body>
</html>
