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
    <title>Statistic</title>
    <c:import url="/jsp/components/dependencies.jsp"></c:import>
    <c:import url="/jsp/components/navbar.jsp"></c:import>
</head>
<body>
<div class="container" style="height: 100%">
    <h1><fmt:message key="status.stats" bundle="${rb}"/></h1>
    <br>
    <c:set var="statistic" value="${requestScope.statistic}"/>
    <c:if test="${empty statistic}">
        <form action="controller" method="GET">
            <input type="hidden" name="command" value="GET_STATS_BY_EXPO_PAGEABLE">
            <input type="hidden" name="currentPage" value="1">
            <div class="form-group">
                <div class="container center_div">
                    <label for="record">Select # of expos per page:</label>
                    <div class="col-sm-4">
                        <select class="form-control" id="record" name="limit">
                            <option value="3" selected>3</option>
                            <option value="6">6</option>
                            <option value="9">9</option>
                        </select>
                    </div>
                </div>
                <br>
            </div>
            <div class="col-md-4 text-center">
                <button type="submit" class="btn btn-dark">select</button>
            </div>
        </form>
    </c:if>
    <c:if test="${not empty statistic}">
        <h2>Expos table</h2>
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
        <nav aria-label="Page navigation">
            <ul class="pagination justify-content-center">
                <c:if test="${requestScope.currentPage != 1}">
                    <form action="controller" method="GET">
                        <input type="hidden" name="command" value="GET_STATS_BY_EXPO_PAGEABLE"/>
                        <input type="hidden" name="limit" value="${requestScope.limit}"/>
                        <input type="hidden" name="currentPage" value="${requestScope.currentPage-1}"/>
                        <button type="submit" class="btn btn-dark">Previous</button>
                    </form>
                </c:if>
                <c:forEach begin="1" end="${requestScope.numberOfPages}" var="i">
                    <c:choose>
                        <c:when test="${requestScope.currentPage eq i}">
                            <li class="page-item active"><a class="page-link">
                                    ${i} <span class="sr-only">(current)</span></a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <form action="controller" method="GET" class="page-item">
                                <input type="hidden" name="command" value="GET_STATS_BY_EXPO_PAGEABLE"/>
                                <input type="hidden" name="limit" value="${requestScope.limit}"/>
                                <input type="hidden" name="currentPage" value="${i}"/>
                                <button type="submit" class="btn btn-dark">${i}</button>
                            </form>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${requestScope.currentPage lt requestScope.numberOfPages}">
                    <form action="controller" method="GET">
                        <input type="hidden" name="command" value="GET_STATS_BY_EXPO_PAGEABLE"/>
                        <input type="hidden" name="limit" value="${requestScope.limit}"/>
                        <input type="hidden" name="currentPage" value="${requestScope.currentPage+1}"/>
                        <button type="submit" class="btn btn-dark">Next</button>
                    </form>
                </c:if>
            </ul>
        </nav>
    </c:if>
</div>
</body>
<c:import url="/jsp/components/footer.jsp"></c:import>
</html>
