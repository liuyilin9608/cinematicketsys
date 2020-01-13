<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="nav_bar">
    <div class="title">${title}</div>
    <div class="nav_right">
        <c:if test="${empty sessionScope.user }">
            <a href="${pageContext.request.contextPath}/home" class="guest">首页</a>
        </c:if>
        <c:if test="${!empty sessionScope.user }">
            <a href="${pageContext.request.contextPath}/home" class="logined">首页</a><span class="divider"> | </span>
            <a href="${pageContext.request.contextPath}/my">个人中心</a>
        </c:if>
    </div>
</div>
