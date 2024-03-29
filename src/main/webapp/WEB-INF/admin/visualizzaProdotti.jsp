<%--
  Created by IntelliJ IDEA.
  User: frank
  Date: 21/06/2022
  Time: 22:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Visualizza prodotti</title>
    <%@include file="/WEB-INF/includes/links.html"%>
</head>
<body>
    <%@include file="/WEB-INF/includes/navbar.jsp"%>
    <br>
    <main id="result">
    <h1>
        Elenco prodotti
    </h1>
    <table id="prodotti">
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Prezzo</th>
            <th>Azione </th>
        </tr>
        <c:forEach items="${prodotti}" var="prod">
            <tr>
                <td>
                        ${prod.id}
                </td>
                <td>
                        ${prod.nome}
                </td>
                <td>
                        ${prod.prezzoListino} &euro;
                </td>
                <td>
                    <form method="get" action="gestione-prodotto">
                        <input type="hidden" name="id" value="${prod.id}">
                        <button type="submit"><i class="fa fa-external-link"></i></button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    </main>
    <%@include file="/WEB-INF/includes/footer.jsp" %>
</body>
</html>
