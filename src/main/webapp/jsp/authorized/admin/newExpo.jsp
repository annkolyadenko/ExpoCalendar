<%--
  Created by IntelliJ IDEA.
  User: Hp
  Date: 16.09.2019
  Time: 21:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/jsp/components/i18n.jsp" %>
<html>
<head>
    <title>New Expo</title>
    <c:import url="/jsp/components/dependencies.jsp"></c:import>
    <c:import url="/jsp/components/navbar.jsp"></c:import>
</head>
<body>
<div class="container" style="height: 100%">
    <h1><fmt:message key="status.newExpo" bundle="${rb}"/></h1>
    <br>
    <form action="controller" method="POST">
        <label>
            <select class="custom-select custom-select-lg mb-3" name="showroomId">
                <label>Select your showroom</label>
                <c:forEach var="showroom" items="${showrooms}" varStatus="loop">
                    <option value="${showroom.id}">${showroom.name}</option>
                </c:forEach>
            </select>
            <select class="custom-select custom-select-lg mb-3" name="themeId">
                <label>Select your theme</label>
                <c:forEach var="theme" items="${themes}" varStatus="loop">
                    <option value="${theme.id}">${theme.name}</option>
                </c:forEach>
            </select>
            <%--<label for="date">Select your date</label>--%>
            <input class="form-control" type="datetime-local" id="datetime" name="chosenDate" value=""
                   min="${today}">
            <br>
            <%--<label>--%>
            <input class="form-control" type="text" placeholder="Enter price" name="price" value="">
            <br>
            <input class="form-control" type="text" placeholder="Enter info" name="info" value="">
            <br>
            <%--</label>--%>
            <input type="hidden" name="command" value="APPROVE_NEW_EXPO">
            <button type="submit" class="btn btn-dark">Submit</button>
        </label>
    </form>
</div>
</body>
<c:import url="/jsp/components/footer.jsp"></c:import>
</html>
