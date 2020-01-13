<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>影厅设置</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mgr_head.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/halls_style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css"/>
    <script src="${pageContext.request.contextPath}/js/my.js"></script>
    <style>
        .list table th {
            width: 25%;
        }
    </style>
</head>
<body>
<%@include file="head.jsp" %>
<div class="add">
    <form action="${pageContext.request.contextPath}/admin/halls/add" method="post">
        <div class="left">
            <input type="text" name="name" class="input_box hall_name" title="影厅名" placeholder="影厅名" 
                   value="${empty name ? "" : name }">
            <input type="text" name="rows" class="input_box" title="总排数" placeholder="总排数" 
                   value="${empty rows || rows <= 0 ? "" : rows }">
            <input type="text" name="columns" class="input_box" title="总列数" placeholder="总列数" 
                   value="${empty columns || columns <= 0  ? "" : columns }">
            <select name="type" title="选择影厅类型" class="hall_type">
                <option>2D</option>
                <option>3D</option>
                <option>4D</option>
            </select>
            <span class="error_msg">${empty msg ? "" : msg }</span>
        </div>
        <input type="submit" value="添加" class="s_add">
    </form>
</div>
<div class="list">
    <table>
        <tr>
            <th>影厅名</th>
            <th>座数</th>
            <th>类型</th>
            <th></th>
        </tr>
        <c:if test="${fn:length(halls) <= 0}">
            <td colspan="4">
                暂无影厅
            </td>
        </c:if>
        <c:forEach items="${halls }" var="hall" varStatus="item">
            <tbody>
                <tr${item.count % 2 != 0 ? " class='odd'" : ""}>        
                    <td>${hall.name }</td>
                    <td>${hall.rows * hall.columns}座(${hall.rows}排${hall.columns}列)</td>
                    <td>${hall.type }</td>
                    <td>
                        <a class="blue_a" href="${pageContext.request.contextPath}/admin/halls/delete?hall=${hall.name}" 
                           onclick="return confirmAction('确定删除吗？')">删除</a>
                    </td>
                </tr>
            <tbody>
        </c:forEach>
    </table>
</div>
</body>
<script>
    encodeAllURI();
</script>
</html>
