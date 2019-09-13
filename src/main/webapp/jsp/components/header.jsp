<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Hp
  Date: 07.09.2019
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Hello</title>
</head>
<body>
<div class="nav-header">
    Hello, <c:out value="${sessionScope.authorizedUser.name}"/>
    <p>Your role: <c:out value="${sessionScope.authorizedUser.role}"/>
</div>
</body>
</html>
