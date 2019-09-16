<%--
  Created by IntelliJ IDEA.
  User: Hp
  Date: 16.09.2019
  Time: 21:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Showrooms</title>
    <c:import url="/jsp/components/dependencies.jsp"></c:import>
    <c:import url="/jsp/components/navbar.jsp"></c:import>
</head>
<body>
<div class="container" style="height: 100%">
    <h1>Showrooms</h1>
    <c:if test="${requestScope.showrooms!=null}">
        <form action="${pageContext.request.contextPath}/controller" method="GET">
            <label>
                <select class="custom-select custom-select-lg mb-3" name="showroomId">
                    <label>Select your showroom</label>
                    <c:forEach var="showroom" items="${showrooms}" varStatus="loop">
                        <option value="${showroom.id}">${showroom.name}</option>
                    </c:forEach>
                </select>
                <input type="hidden" name="command" value="GET_ALL_EXPO_BY_SHOWROOM_ID">
                <button type="submit" class="btn btn-dark">Submit
                </button>
            </label>
        </form>
    </c:if>
</div>

</body>
<c:import url="/jsp/components/footer.jsp"></c:import>
</html>
