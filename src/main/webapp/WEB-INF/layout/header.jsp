<%-- 
    Document   : header
    Created on : Jul 21, 2023, 1:12:19 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/" var="action" />
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <div class="container-fluid container">
        <a class="navbar-brand" href="#">Clinic</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${action}">Trang chủ</a>
                </li>
                    <c:choose>
                        <c:when test="${pageContext.request.userPrincipal.name != null}">
                            <li class="nav-item">
                                <a class="nav-link" href="<c:url value="/" />">Chào ${pageContext.request.userPrincipal.name}</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="<c:url value="/patient-stat" />">Thống kê bệnh nhân</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="<c:url value="/revenue-stat" />">Thống kê tiền khám và tiền thuốc</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="nav-item">
                                <a class="nav-link" href="<c:url value="/login" />">Đăng nhập</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
            </ul>

            <form class="d-flex" action="${action}">
                <input class="form-control me-2" type="text" name="kw" placeholder="Nhập từ khóa...">
                <button class="btn btn-primary" type="submit">Tìm</button>
            </form>
        </div>
    </div>
</nav>