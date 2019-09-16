<%--
  Created by IntelliJ IDEA.
  User: Hp
  Date: 16.09.2019
  Time: 20:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Tickets</title>
    <c:import url="/jsp/components/dependencies.jsp"></c:import>
    <c:import url="/jsp/components/navbar.jsp"></c:import>
</head>
<body>
<div class="container" style="height: 100%">
    <h1>Purchase</h1>
    <c:if test="${requestScope.tickets!=null}">
        <table class="table table-sm table-hover table-bordered text-center">
            <thead>
            <tr>
                <th scope="col">Ticket#</th>
                <th scope="col">Info</th>
                <th scope="col">Showroom</th>
                <th scope="col">Price</th>
                <th scope="col">Tickets amount</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="ticket" items="${tickets}" varStatus="loop">
                <tr>
                    <td>${ticket.id}</td>
                    <td>${ticket.expo.info}</td>
                    <td>${ticket.expo.showroom.info}</td>
                    <td>${ticket.expo.price}</td>
                    <td>${ticket.amount}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
</body>
<c:import url="/jsp/components/footer.jsp"></c:import>
</html>
