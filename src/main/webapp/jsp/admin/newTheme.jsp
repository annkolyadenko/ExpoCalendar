<%--
  Created by IntelliJ IDEA.
  User: Hp
  Date: 16.09.2019
  Time: 21:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>New Theme</title>
    <c:import url="/jsp/components/dependencies.jsp"></c:import>
    <c:import url="/jsp/components/navbar.jsp"></c:import>
</head>
<body>
<div class="container" style="height: 100%">
    <h1>New Theme</h1>
    <form action="controller" method="POST">
        <label>
            <input class="form-control" type="text" placeholder="Enter new theme" name="theme" value="">
            <br>
            <input type="hidden" name="command" value="APPROVE_NEW_THEME">
            <button type="submit" class="btn btn-dark">Submit
            </button>
        </label>
    </form>
</div>
</body>
<c:import url="/jsp/components/footer.jsp"></c:import>
</html>
