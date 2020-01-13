<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户管理</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mgr_head.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tool_bar.css"/>
    <script src="${pageContext.request.contextPath}/js/my.js"></script>
</head>
<body>
<%@include file="head.jsp" %>
<div class="tool_bar">
    <div class="search">
        <input type="text" name="keyword" title="用户名关键字" placeholder="用户名关键字" class="input_box" value="${empty keyword ? "" : keyword}">
        <button class="search_action" onclick="search('${pageContext.request.contextPath}/admin')">搜索</button>
    </div>
</div>
<div class="list">
    <table>
        <tr>
            <th>用户名</th>
            <th>昵称</th>
            <th>手机号</th>
            <th>注册时间</th>
            <th></th>
        </tr>
        <c:if test="${fn:length(users) <= 0}">
            <td colspan="4">
                暂无用户
            </td>
        </c:if>
        <c:forEach items="${users }" var="u" varStatus="item">
        <tbody>
            <tr${item.count % 2 != 0 ? " class='odd'" : ""}>
                <td>${u.username }</td>
                <td>${u.nickname }</td>
                <td>${u.phone }</td>
                <td class="reg_time">${u.reg_time}</td>
                <td><a class="blue_a" href="${pageContext.request.contextPath}/admin/users/delete?name=${u.username}" onclick="return confirmAction('确定删除吗？')">删除</a></td>
            </tr>
        </tbody>
        </c:forEach>
    </table>
</div>
</body>
<script>
    var nodeList = document.getElementsByClassName("reg_time");
    for (var i = 0; i < nodeList.length; i++) {
        if (/^\d+$/.test(nodeList[i].innerHTML)) {
            nodeList[i].innerHTML = formatDate(parseInt(nodeList[i].innerHTML));
        }
    }
</script>
</html>
