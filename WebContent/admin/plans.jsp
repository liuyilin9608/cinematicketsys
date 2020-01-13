<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>排片管理</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mgr_head.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tool_bar.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css"/>
    <script src="${pageContext.request.contextPath}/js/my.js"></script>
    <style>
        .list table th {
            width: 18%;
        }
        .list table .hall_name {
            width: 20%;
        }
        .list table .price {
            width: 12%;
        }
        .list table .movie_name {
            width: 34%;
        }
        .list table .action_col {
            width: 16%;
        }
        .list .blue_a {
            margin-left: 20px;
        }
        .list .play_state {
            font-weight: bold;
            font-size: 18px;
        }
    </style>
</head>
<body>
<%@include file="head.jsp" %>
<div class="tool_bar">
    <div class="search">
        <input type="text" name="keyword" title="片名关键字" placeholder="片名关键字" class="input_box" value="${empty keyword ? "" : keyword}">
        <button class="search_action" onclick="search('${pageContext.request.contextPath}/admin/plans')">搜索</button>
    </div>
</div>
<div class="list">
    <table>
        <tr>
            <th class="movie_name">影片</th>
            <th class="hall_name">影厅</th>
            <th class="price">单价</th>
            <th>放映时间</th>
            <th class="action_col"></th>
        </tr>
        <c:if test="${fn:length(plans) <= 0}">
            <td colspan="5">
                暂无排片
            </td>
        </c:if>
        <c:forEach items="${plans }" var="plan" varStatus="item">
        <tbody>
            <tr${item.count % 2 != 0 ? " class='odd'" : ""}>
                <td><a href="${pageContext.request.contextPath}/movie/detail?movie=${plan.movie_name}">${plan.movie_name}</a></td>
                <td>${plan.hall_name}</td>
                <td>￥${plan.price}</td>
                <td class="play_time">${plan.play_time}</td>
                <td>
                    <c:if test="${plan.played}">
                        <span class="play_state">已放映</span>
                    </c:if>
                    <c:if test="${!plan.played}">
                        <a class="blue_a" href="${pageContext.request.contextPath}/admin/plans/cancel?plan=${plan.movie_name}_${plan.hall_name}_${plan.play_time}"
                           onclick="return confirmAction('确定取消吗？')">取消</a>
                    </c:if>
                </td>
            </tr>
        <tbody>
        </c:forEach>
    </table>
</div>
</body>
<script>
    var playTime = document.getElementsByClassName("play_time");
    for (var i = 0; i < playTime.length; i++) {
        if (/^\d+$/.test(playTime[i].innerHTML)) {
            playTime[i].innerHTML = formatDate(parseInt(playTime[i].innerHTML));
        }
    }
    
    encodeAllURI();
</script>
</html>
