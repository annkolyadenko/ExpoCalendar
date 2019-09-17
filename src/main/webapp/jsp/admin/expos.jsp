<%--
  Created by IntelliJ IDEA.
  User: Hp
  Date: 16.09.2019
  Time: 21:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Expos</title>
    <c:import url="/jsp/components/dependencies.jsp"></c:import>
    <c:import url="/jsp/components/navbar.jsp"></c:import>
</head>
<body>
<div class="container" style="height: 100%">
    <h1>Expos</h1>
    <br>
    <c:if test="${requestScope.expos!=null}">
        <table class="table table-sm table-hover table-bordered text-center">
            <thead>
            <tr>
                <th scope="col">Showroom</th>
                <th scope="col">Theme</th>
                <th scope="col">Date</th>
                <th scope="col">Expo info</th>
                <th scope="col">Ticket price</th>
                <th scope="col">Stats</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="expo" items="${expos}" varStatus="loop">
                <tr>
                    <td>${expo.showroom.name}</td>
                    <td>${expo.theme}</td>
                    <td>${expo.date}</td>
                    <td>${expo.info}</td>
                    <td>${expo.price}</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/controller" method="GET" name="expoId">
                            <input type="hidden" name="expoId" value="${expo.id}">
                            <input type="hidden" name="command" value="SUM_ALL_TICKETS_BY_EXPO_ID">
                            <button class="btn btn-dark" type="submit">
                                Check
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${requestScope.amount!=null}">
        <h4>Total amount: ${requestScope.amount}</h4>
    </c:if>
</div>
</body>
<c:import url="/jsp/components/footer.jsp"></c:import>
</html>
