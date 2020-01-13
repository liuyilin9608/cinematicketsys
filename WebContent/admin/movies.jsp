<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>影片管理</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mgr_head.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tool_bar.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/movies_style.css"/>
    <script src="${pageContext.request.contextPath}/js/my.js"></script>
</head>
<body>
<%@include file="head.jsp" %>
<div class="tool_bar">
    <a class="add" href="${pageContext.request.contextPath}/admin/movies/add">
        <span class="icon">+</span><span class="txt">添加影片</span>
    </a>
    <div class="search">
        <input type="text" name="keyword" title="片名关键字" placeholder="片名关键字" class="input_box"
               value="${empty keyword ? "" : keyword}">
        <button class="search_action" onclick="search('${pageContext.request.contextPath}/admin/movies')">搜索</button>
    </div>
</div>
<div class="content">
    <c:if test="${fn:length(movies) <= 0}">
        <p class="empty_list">暂无影片</p>
    </c:if>
    <ul class="list">        
        <c:forEach items="${movies }" var="movie">
            <li class="item">
                <img class="pic" src="${pageContext.request.contextPath}/img?file=${movie.pic_name}" title="${movie.name}">
                <div class="txt">
                    <p class="movie_name">${movie.name}</p>
                    <p class="info">导演:&nbsp;${movie.director}</p>
                    <p class="info">主演:&nbsp;${movie.protagonist}</p>
                    <p class="info">类型:&nbsp;${movie.type}</p>
                    <p class="info">国家&nbsp;/&nbsp;地区:&nbsp;${movie.region}</p>
                    <p class="info">语言:&nbsp;${movie.language}</p>
                    <p class="info">片长:&nbsp;${movie.duration}分钟</p>
                    <p class="synopsis">剧情简介:&nbsp;${movie.synopsis}</p>
                </div>
                <div class="action">
                    <p><a href="${pageContext.request.contextPath}/admin/movies/update?movie=${movie.name }">修改</a></p> 
                    <p><a href="${pageContext.request.contextPath}/admin/movies/delete?movie=${movie.name }" 
                          onclick="return confirmAction('确定删除吗？')">删除</a></p>
                    <p><a href="${pageContext.request.contextPath}/admin/plans/add?movie=${movie.name}" class="plan">排片</a></p>
                </div>
            </li>
        </c:forEach>        
    </ul>
</div>
<script src="${pageContext.request.contextPath}/js/clamp.min.js"></script>
<script>
    var nodeList = document.getElementsByClassName("synopsis");
    for (var i = 0; i < nodeList.length; i++) {
        $clamp(nodeList[i], {clamp: 5});
    }   
    encodeAllURI();
</script>
</body>
</html>
