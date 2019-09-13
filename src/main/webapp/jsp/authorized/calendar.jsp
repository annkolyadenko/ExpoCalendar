<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Hp
  Date: 10.09.2019
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Calendar</title>
    <c:import url="/jsp/components/dependencies.jsp"></c:import>
    <c:import url="/jsp/components/navbar.jsp"></c:import>

</head>
<body>
<div class="container" style="height: 100%">
    <h1>Calendar</h1>
    <table class="table table-sm table-hover table-bordered text-center">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">First</th>
            <th scope="col">Last</th>
            <th scope="col">Handle</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th scope="row">1</th>
            <td>Mark</td>
            <td>Otto</td>
            <td>@mdo</td>
        </tr>
        <tr>
            <th scope="row">2</th>
            <td>Jacob</td>
            <td>Thornton</td>
            <td>@fat</td>
        </tr>
        <tr>
            <th scope="row">3</th>
            <td colspan="2">Larry the Bird</td>
            <td>@twitter</td>
        </tr>
        </tbody>
    </table>



</div>
</body>
<c:import url="/jsp/components/footer.jsp"></c:import>
</html>
