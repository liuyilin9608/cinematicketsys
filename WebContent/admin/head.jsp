<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="nav_bar">
    <div class="nav_title">
        <ul class="menu">
            <li>
                <c:if test="${chkItem == 1}">
                    <a href="${pageContext.request.contextPath}/admin" class="chked">用户管理</a>
                </c:if>
                <c:if test="${chkItem != 1}">
                    <a href="${pageContext.request.contextPath}/admin">用户管理</a>
                </c:if>
            </li>
            <li>
                <c:if test="${chkItem == 2}">
                    <a href="${pageContext.request.contextPath}/admin/movies" class="chked">影片管理</a>
                </c:if>
                <c:if test="${chkItem != 2}">
                    <a href="${pageContext.request.contextPath}/admin/movies">影片管理</a>
                </c:if>
            </li>
            <li>
                <c:if test="${chkItem == 3}">
                    <a href="${pageContext.request.contextPath}/admin/halls" class="chked">影厅设置</a>
                </c:if>
                <c:if test="${chkItem != 3}">
                    <a href="${pageContext.request.contextPath}/admin/halls">影厅设置</a>
                </c:if>
            </li>
            <li>
                <c:if test="${chkItem == 4}">
                    <a href="${pageContext.request.contextPath}/admin/orders" class="chked">订单管理</a>
                </c:if>
                <c:if test="${chkItem != 4}">
                    <a href="${pageContext.request.contextPath}/admin/orders">订单管理</a>
                </c:if>
            </li>
            <li>
                <c:if test="${chkItem == 5}">
                    <a href="${pageContext.request.contextPath}/admin/evaluates" class="chked">影评管理</a>
                </c:if>
                <c:if test="${chkItem != 5}">
                    <a href="${pageContext.request.contextPath}/admin/evaluates">影评管理</a>
                </c:if>
            </li>
            <li>
                <c:if test="${chkItem == 6}">
                    <a href="${pageContext.request.contextPath}/admin/plans" class="chked">排片管理</a>
                </c:if>
                <c:if test="${chkItem != 6}">
                    <a href="${pageContext.request.contextPath}/admin/plans">排片管理</a>
                </c:if>
            </li>
        </ul>
    </div>
    <div class="nav_right">
        <a href="${pageContext.request.contextPath}/" class="index">首页</a>
        <span class="divider"> | </span>
        <a href="${pageContext.request.contextPath}/admin/logout">退出</a>
    </div>
</div>
