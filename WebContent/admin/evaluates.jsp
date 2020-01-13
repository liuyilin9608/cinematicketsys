<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>影评管理</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mgr_head.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tool_bar.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/evaluates_style.css"/>
    <script src="${pageContext.request.contextPath}/js/my.js"></script>
</head>
<body>
<%@include file="head.jsp" %>
<div class="tool_bar">
    <div class="search">
        <input type="text" name="keyword" title="用户名或片名关键字" placeholder="用户名或片名关键字" class="input_box"
               value="${empty keyword ? "" : keyword}">
        <button class="search_action" onclick="search('${pageContext.request.contextPath}/admin/evaluates')">搜索</button>
    </div>
</div>
<div class="content">
    <c:if test="${fn:length(evamap) <= 0}">
        <p class="empty_list">暂无影评</p>
    </c:if>
    <ul>
        <c:forEach items="${evamap}" var="it">
            <li class="item">
                <div class="ev_title">
                    <p class="movie_name"><a href="${pageContext.request.contextPath}/movie/detail?movie=${it.key.movie_name}">${it.key.movie_name}</a></p>
                    <p class="username">${it.key.username}</p>
                    <p class="evaluate_time">${it.key.time}</p>
                    <a class="blue_a" href="${pageContext.request.contextPath}/admin/evaluates/delete?i=${it.key.movie_name}_${it.key.username}_${it.key.time}" 
                          onclick="return confirmAction('确定删除吗？')">删除</a>
                </div>
                <p class="evaluate_txt">${it.key.content}</p>
                <ul>
                    <c:forEach items="${it.value}" var="reply">
                        <li class="reply_li">
                            <div class="ev_title reply_title">
                                <p class="username un">${reply.username}</p>
                                <p class="evaluate_time">${reply.time}</p>
                            </div>
                            <p class="evaluate_txt">回复&nbsp;${it.key.nickname}：&nbsp;${reply.content}</p>
                        </li>
                    </c:forEach>
                </ul>
            </li>
        </c:forEach>
    </ul>
</div>
</body>
<script>
    var evaluateTime = document.getElementsByClassName("evaluate_time");
    for (var i = 0; i < evaluateTime.length; i++) {
        if (/^\d+$/.test(evaluateTime[i].innerHTML)) {
            evaluateTime[i].innerHTML = formatDate(parseInt(evaluateTime[i].innerHTML));
        }
    }
    encodeAllURI();
</script>
</html>
