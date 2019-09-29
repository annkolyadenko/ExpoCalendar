<%--
  Created by IntelliJ IDEA.
  User: Hp
  Date: 17.09.2019
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/jsp/components/i18n.jsp" %>
<html>
<head>
    <title>Theme Approved</title>
    <c:import url="/jsp/components/dependencies.jsp"></c:import>
    <c:import url="/jsp/components/navbar.jsp"></c:import>
</head>
<body>
<div class="container" style="height: 100%">
    <h1><fmt:message key="status.stats" bundle="${rb}"/></h1>
    <br>
    <c:if test="${requestScope.statistic!=null}">
        <table class="table table-sm table-hover table-bordered text-center">
            <thead>
            <tr>
                <th scope="col">Showroom</th>
                <th scope="col">Theme</th>
                <th scope="col">Date</th>
                <th scope="col">Expo info</th>
                <th scope="col">Ticket price</th>
                <th scope="col">Ticket amount</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="stats" items="${statistic}" varStatus="loop">
                <tr>
                    <td>${stats.key.showroom}</td>
                    <td>${stats.key.theme}</td>
                    <td>${stats.key.date}</td>
                    <td>${stats.key.info}</td>
                    <td>${stats.key.price}</td>
                    <td>${stats.value}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
</body>
<c:import url="/jsp/components/footer.jsp"></c:import>
</html>
