<%--
  Created by IntelliJ IDEA.
  User: Hp
  Date: 26.08.2019
  Time: 22:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="<c:url value="/jsp/common/shitHappens.jsp" />"><img
            src="<c:url value="/resources/images/navbar.jpeg" />"
            width="140" height="100" alt="fibonacci drawing"></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ctg:if-user role="empty">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="#">About</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                       data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">
                        Sign in
                    </a>
                    <form class="dropdown-menu p-1" action="controller" method="POST">
                        <input type="hidden" name="command" value="login"/>
                        <div class="form-group">
                            <label for="dropdownFormEmail">Email address</label>
                            <input type="email" class="form-control" id="dropdownFormEmail" name="email"
                                   placeholder="email@example.com">
                        </div>
                        <div class="form-group">
                            <label for="dropdownFormPassword">Password</label>
                            <input type="password" class="form-control" id="dropdownFormPassword" name="password"
                                   placeholder="Password">
                        </div>
                        <div class="form-group">
                            <div class="form-check">
                                <input type="checkbox" class="form-check-input" id="dropdownCheck">
                                <label class="form-check-label" for="dropdownCheck">
                                    Remember me
                                </label>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-dark">Sign in</button>
                    </form>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Sign up</a>
                </li>
            </ul>
            <ul class="navbar-nav navbar-right">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown1" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Language
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="#">Слава Україні</a>
                        <a class="dropdown-item" href="#">Glory to Ukraine</a>
                    </div>
                </li>
            </ul>
        </ctg:if-user>
        <ctg:if-user role="visitor">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="<c:url value="/controller?command=RETURN_TO_HOMEPAGE" />">Home<span
                            class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/controller?command=GET_ALL_THEMES" />">Calendar</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">My tickets</a>
                </li>
            </ul>
            <ul class="navbar-nav navbar-right">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownVisitor2" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Language
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="#">Слава Україні</a>
                        <a class="dropdown-item" href="#">Glory to Ukraine</a>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/controller?command=LOGOUT" />">Log out</a>
                </li>
            </ul>
            <span class="navbar-text">
            <%@ include file="/jsp/components/header.jsp" %>
        </span>
        </ctg:if-user>
        <ctg:if-user role="administrator">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="#">Home<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Statistics</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Create expo</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Create theme</a>
                </li>
            </ul>
            <ul class="navbar-nav navbar-right">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownAdmin2" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Language
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="#">Слава Україні</a>
                        <a class="dropdown-item" href="#">Glory to Ukraine</a>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/controller?command=LOGOUT" />">Sign out</a>
                </li>
            </ul>
            <span class="navbar-text">
            <%@ include file="/jsp/components/header.jsp" %>
        </span>
        </ctg:if-user>
    </div>
</nav>

