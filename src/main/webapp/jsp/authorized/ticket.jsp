<%--
  Created by IntelliJ IDEA.
  User: Hp
  Date: 13.09.2019
  Time: 22:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Ticket</title>
    <c:import url="/jsp/components/dependencies.jsp"></c:import>
    <c:import url="/jsp/components/navbar.jsp"></c:import>
</head>
<body>
<div class="container" style="height: 100%">
    <h1>Tickets</h1>
    Thank you for purchasing tickets!
    Expo info: ${ticket.expo.info}
    Time: ${ticket.time}
    Ticket amount: ${ticket.amount}
</div>
</body>
<c:import url="/jsp/components/footer.jsp"></c:import>
</html>
