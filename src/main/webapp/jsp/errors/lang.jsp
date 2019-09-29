<%--
  Created by IntelliJ IDEA.
  User: Hp
  Date: 18.09.2019
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Language</title>
    <c:import url="/jsp/components/dependencies.jsp"></c:import>
    <c:import url="/jsp/components/navbar.jsp"></c:import>
</head>
<body>
<div class="container" style="height: 100%">
    <h1>Сталася халепа!</h1>
    <h1>Нажаль, ця мова не підтримується </h1>
    <br>
    <a href="<c:url value="/controller?command=LOCALIZATION&language=Ukrainian" />">Українська</a>
    <br>
    <a href="<c:url value="/controller?command=LOCALIZATION&language=English" />">English</a>
</div>
</body>
<c:import url="/jsp/components/footer.jsp"></c:import>
</html>
