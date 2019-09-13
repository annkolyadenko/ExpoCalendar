<%--
  Created by IntelliJ IDEA.
  User: Hp
  Date: 09.09.2019
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Themes</title>
    <c:import url="/jsp/components/dependencies.jsp"></c:import>
    <c:import url="/jsp/components/navbar.jsp"></c:import>
</head>
<body>
<div class="container" style="height: 100%">
    <h1>Themes</h1>
    <c:if test="${requestScope.themes!=null}">
        <form action="${pageContext.request.contextPath}/controller" method="GET">
            <label>
                <select class="custom-select custom-select-lg mb-3" name="themeId">
                    <label>Select your theme</label>
                    <c:forEach var="theme" items="${themes}" varStatus="loop">
                        <option value="${theme.id}">${theme.name}</option>
                    </c:forEach>
                </select>
                <p>
                    <label for="date">Select your date</label>
                    <input class="form-control" type="date" id="date" name="chosenDate" value=""
                           min="${today}">
                </p>
                <input type="hidden" name="command" value="GET_ALL_EXPO_BY_THEME_ID_AND_DATE">
                <button type="submit" class="btn btn-dark">Submit
                </button>
            </label>
        </form>
    </c:if>
</div>
</body>
<c:import url="/jsp/components/footer.jsp"></c:import>
</html>

