<%--
  Created by IntelliJ IDEA.
  User: Hp
  Date: 27.08.2019
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/components/i18n.jsp" %>
<html>
<head>
    <title>Login</title>
    <c:import url="/jsp/components/dependencies.jsp"></c:import>
    <c:import url="/jsp/components/navbar.jsp"></c:import>
</head>
<body>
<div class="container" style="height: 100%">
    <c:if test="${requestScope.isError}">
        <div class="form-group">
            <div class="col-lg-offset-2 col-lg-10">
                <p style="color: red">${requestScope.errorMessage}</p>
            </div>
        </div>
    </c:if>
</div>
</body>
<c:import url="/jsp/components/footer.jsp"></c:import>
</html>
<%--charset=UTF-8 - кодировка страницы, полученной в результате работы JSP. JSP скомпилировали,
использовав pageEncoding, вызвали – и она сгенерировала страницу в кодировке charset.
pageEncoding="UTF-8 - это кодировка текущего файла JSP.
Если один параметр опущен – другой принимается равным ему.--%>
