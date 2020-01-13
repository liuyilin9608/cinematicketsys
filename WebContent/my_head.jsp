<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="nav_bar">
    <div class="nav_title">
        <ul class="menu">
            <li>
                <c:if test="${chkItem == 1}">
                    <a href="${pageContext.request.contextPath}/my" class="chked">个人信息</a>
                </c:if>
                <c:if test="${chkItem != 1}">
                    <a href="${pageContext.request.contextPath}/my">个人信息</a>
                </c:if>
            </li>
            <li>
                <c:if test="${chkItem == 2}">
                    <a href="${pageContext.request.contextPath}/my/tickets" class="chked">电影票</a>
                </c:if>
                <c:if test="${chkItem != 2}">
                    <a href="${pageContext.request.contextPath}/my/tickets">电影票</a>
                </c:if>
            </li>
            <li>
                <c:if test="${chkItem == 3}">
                    <a href="${pageContext.request.contextPath}/my/update" class="chked">修改资料</a>
                </c:if>
                <c:if test="${chkItem != 3}">
                    <a href="${pageContext.request.contextPath}/my/update">修改资料</a>
                </c:if>
            </li>
            <li>
                <c:if test="${chkItem == 4}">
                    <a href="${pageContext.request.contextPath}/my/chgpwd" class="last chked">修改密码</a>
                </c:if>
                <c:if test="${chkItem != 4}">
                    <a href="${pageContext.request.contextPath}/my/chgpwd" class="last">修改密码</a>
                </c:if>
            </li>
        </ul>
    </div>
    <div class="nav_right">
        <a href="${pageContext.request.contextPath}/home">首页</a>
        <span class="divider"> | </span>
        <a href="${pageContext.request.contextPath}/logout">退出</a>
    </div>
</div>
