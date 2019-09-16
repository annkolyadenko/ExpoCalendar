<%--
  Created by IntelliJ IDEA.
  User: Hp
  Date: 10.09.2019
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Calendar</title>
    <c:import url="/jsp/components/dependencies.jsp"></c:import>
    <c:import url="/jsp/components/navbar.jsp"></c:import>
</head>
<body>
<div class="container" style="height: 100%">
    <h1>Calendar</h1>
    <h5>Chosen date: ${chosenDate}</h5>
    <c:if test="${requestScope.expos!=null}">
        <table class="table table-sm table-hover table-bordered text-center">
            <thead>
            <tr>
                <th scope="col">Showroom</th>
                <th scope="col">Price</th>
                <th scope="col">Info</th>
                <th scope="col">Tickets amount</th>
                <th scope="col">Purchase ticket</th>
            </tr>
            </thead>
            <tbody>
            <form action="controller" method="POST">
                <c:forEach var="expo" items="${expos}" varStatus="loop">
                    <tr>
                        <td>${expo.showroom.name}</td>
                        <td>${expo.price}</td>
                        <td>${expo.info}</td>
                        <td>
                            <div class="form-group">
                                <label>
                                    <select class="form-control" name="ticketsAmount">
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>
                                        <option value="4">4</option>
                                    </select>
                                </label>
                            </div>
                        </td>
                        <td>
                            <input type="hidden" name="expoId" value="${expo.id}">
                            <input type="hidden" name="command" value="PURCHASE_TICKET">
                            <button class="btn btn-dark" type="submit">
                                Purchase
                            </button>

                        </td>
                    </tr>
                </c:forEach>
            </form>
            </tbody>
        </table>
    </c:if>
    <form action="${pageContext.request.contextPath}/controller" method="GET">
        <input type="hidden" name="command" value="GET_ALL_THEMES">
        <button class="btn btn-dark" type="submit">New search
        </button>
    </form>
</div>
</body>
<c:import url="/jsp/components/footer.jsp"></c:import>
</html>
