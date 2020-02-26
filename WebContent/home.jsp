<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>XXX影院</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mgr_head.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home_style.css"/>
    <script src="${pageContext.request.contextPath}/js/my.js"></script>
</head>
<body>
<jsp:include page="commen_head.jsp"></jsp:include>
<div class="space-30"></div>
<div class="main" style="overflow:hidden;">
<div class="content">
    <div class="nav_bar">
        <div class="nav_title">
            <ul class="menu">
                <li>
                    <a href="${pageContext.request.contextPath}/home" class="chked">近期热映</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/sales">票房统计</a>
                </li>
            </ul>
        </div>
       
    </div>
    <c:if test="${fn:length(movies) <= 0}">
        <p class="empty_list">暂无影片</p>
    </c:if>
    <ul class="list">
        <c:forEach items="${movies }" var="movie">
            <li>
                <img class="pic" src="${pageContext.request.contextPath}/images/${movie.pic_name}" title="${movie.name}">
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
                <div class="action_block">
                    <c:if test="${empty sessionScope}">
                        <a class="blue_a" href="${pageContext.request.contextPath}/login">购票</a>
                    </c:if>
                    <c:if test="${!empty sessionScope}">
                        <a class="blue_a" href="${pageContext.request.contextPath}/sessions?movie=${movie.name}">购票</a>                        
                    </c:if>
                    <a class="blue_a" href="${pageContext.request.contextPath}/movie/detail?movie=${movie.name}">详情</a>
                </div>
            </li>
        </c:forEach>
    </ul>
</div>
</div>
<jsp:include page="commen_foot.jsp"></jsp:include>
</body>
<script src="${pageContext.request.contextPath}/js/clamp.min.js"></script>
<script>
    var nodeList = document.getElementsByClassName("synopsis");
    for (var i = 0; i < nodeList.length; i++) {
        $clamp(nodeList[i], {clamp: 3});
    }    
    encodeAllURI();//中文转码
</script>
</html>
