<%--
  Created by IntelliJ IDEA.
  User: frank
  Date: 20/07/2022
  Time: 22:46
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ordine #${ordine.id} completato</title>
    <%@include file="/WEB-INF/includes/links.html" %>
</head>
<body>
    <%@include file="/WEB-INF/includes/navbar.jsp" %>
    <main id="result">
        <h1>Ordine Confermato!</h1>
        <p>
            L'ordine è stato completato con successo, controlla il suo stato quando vuoi nella scheda "Ordini"
            del tuo account.
        </p>
    </main>
    <%@include file="/WEB-INF/includes/footer.jsp" %>
</body>
</html>
