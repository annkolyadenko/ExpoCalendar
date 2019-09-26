<%--
  Created by IntelliJ IDEA.
  User: Hp
  Date: 26.09.2019
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Error 403</title>
    <c:import url="/jsp/components/dependencies.jsp"></c:import>
</head>
<body>
<div class="container" style="height: 100%">
    <div class="row">
        <div class="col-md-12">
            <div class="error-template">
                <h1>
                    Sorry!</h1>
                <h2>
                    403 Forbidden Error</h2>
                <div class="error-details">
                    Unfortunately, accessing the page or resource you were trying to reach is absolutely forbidden for
                    security reason.
                </div>
                <div class="error-actions">
                    <a href="<c:url value="/index.jsp" />" class="btn btn-dark btn-lg"><span
                            class="glyphicon glyphicon-home"></span>
                        Get back to reality </a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<c:import url="/jsp/components/footer.jsp"></c:import>
</html>
