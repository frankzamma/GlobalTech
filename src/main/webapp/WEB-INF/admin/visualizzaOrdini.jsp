<%@ page import="model.Ordine" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Annalaura
  Date: 09/07/2022
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ordini</title>
    <%@include file="../includes/links.html"%>
</head>
<body>
<%@include file="../includes/navbar.jsp"%>
<%
List<Ordine> ordini = (List<Ordine>) request.getAttribute("ordini");
%>

<!--
Gli stati dell'ordine sono:
IN ATTESA DI CONFERMA (conferma ordine e rifiuta ordine)
CONFERMATO (spedisci con numero di tracking)
COMPLETATO
-->
<br>
<table>
    <tr>
        <th>ID</th>
        <th>Prezzo</th>
        <!--<th>Data</th>-->
        <th>Pagamento</th>
        <th>Stato</th>
    </tr>
    <%
    for (Ordine o : ordini){
    %>
    <tr>
        <td><%=o.getId()%></td>
        <td><%=o.getPrezzoTotale()%></td>
        <!--fare o.getData-->
        <td><%=o.getModalitaPagamento()%></td>
        <td>
        <form action="stato-spedizione">
            <input type="hidden" id="idOrdine" name="idOrdine" value="<%=o.getId()%>">
            <%
        if (o.getStato().equals("In attesa di conferma")){
        %>
            In attesa di conferma
        <input type="submit" id="stato1" name="stato1" value="Conferma">
        <%}%>
        <%if (o.getStato().equals("Confermato")){
        %>
            In attesa di spedizione
            <input type="text" id="numTracking" name="numTracking" placeholder="Numero di tracking" required>
            <input type="submit" id="stato2" name="stato2" value="Spedisci">

            <%}%>
        <%if (o.getStato().equals("Completato")){%>
            Completato
        <%}%>
        </form>
        </td>
        <%}%>
    </tr>

</table>

</body>
</html>