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
    <title>New Theme</title>
    <c:import url="/jsp/components/dependencies.jsp"></c:import>
    <c:import url="/jsp/components/navbar.jsp"></c:import>
</head>
<body>
<div class="container" style="height: 100%">
    <h1><fmt:message key="status.newTheme" bundle="${rb}"/></h1>
    <form action="controller" method="POST">
        <label>
            <input class="form-control" type="text" placeholder="Enter new theme" name="theme" value="">
            <br>
            <input type="hidden" name="command" value="APPROVE_NEW_THEME">
            <button type="submit" class="btn btn-dark">Submit
            </button>
        </label>
    </form>
    <c:if test="${requestScope.isError}">
        <div class="form-group">
            <div class="col-lg-offset-2 col-lg-10">
                <p style="color: red">${requestScope.errorMessage}</p>
            </div>
        </div>
    </c:if>
    <c:if test="${requestScope.status}">
        <div class="form-group">
            <div class="col-lg-offset-2 col-lg-10">
                <p>${requestScope.status}</p>
            </div>
        </div>
    </c:if>
</div>
</body>
<c:import url="/jsp/components/footer.jsp"></c:import>
</html>
