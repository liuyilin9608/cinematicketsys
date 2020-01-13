<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>影片详情</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/movie_detail_style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/head.css"/>
    <script src="${pageContext.request.contextPath}/js/my.js"></script>
    <script>
        function checkEmpty() {
            var nodeList = document.getElementsByName("evaluate_content");
            if (nodeList[0].value === "") {
                alert("评价不能为空");
                return false;
            }
            return true;
        }
        
        function reply(node) {
            var textarea = document.getElementById("eva_cont");
            textarea.innerText = "[回复]" + node.name + "[/回复]\r\n";
            textarea.focus();
            document.getElementById("eva_id").value = node.id;
            document.getElementById("write_eva").scrollIntoView()
        }
    </script>
</head>
<body>
<div class="detail">
    <%@include file="head.jsp"%>
    <div class="movie_info">
        <img class="pic" src="${pageContext.request.contextPath}/img?file=${movie.pic_name}" title="${movie.name}">
        <div class="txt">
            <p class="movie_name">${movie.name}</p>
            <p class="info">导演:&nbsp;${movie.director}</p>
            <p class="info">主演:&nbsp;${movie.protagonist}</p>
            <p class="info">类型:&nbsp;${movie.type}</p>
            <p class="info">国家&nbsp;/&nbsp;地区:&nbsp;${movie.region}</p>
            <p class="info">语言:&nbsp;${movie.language}</p>
            <p class="info">片长:&nbsp;${movie.duration}分钟</p>
            <c:if test="${empty sessionScope.user}">
                <a class="buy" href="${pageContext.request.contextPath}/login?from=/movie/detail">购票</a>
            </c:if>
            <c:if test="${!empty sessionScope.user}">
                <a class="buy" href="${pageContext.request.contextPath}/sessions?movie=${movie.name}">购票</a>
            </c:if>            
        </div>
    </div>
    <div class="synopsis">
        <p class="title">剧情简介:</p>
        <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${movie.synopsis}</p>
    </div>
</div>
<div class="evaluate">
    <div class="title_bar">
        <span class="title">影评</span>
        <c:if test="${empty sessionScope.user}">
            <a class="write" href="${pageContext.request.contextPath}/login?from=/movie/detail">写影评</a>
        </c:if>
        <c:if test="${!empty sessionScope.user}">
            <a class="write" onclick="document.getElementById('write_eva').scrollIntoView()">写影评</a>
        </c:if>
    </div>
    <c:if test="${fn:length(evamap) <= 0}">
        <p class="empty_list">暂无影评</p>
    </c:if>
    <ul>
        <c:forEach items="${evamap}" var="it">
            <li class="item">
                <div class="eva_title ev_title">
                    <p class="nickname">${it.key.nickname}</p>
                    <p class="evaluate_time">${it.key.time}</p>
                    <c:if test="${!empty sessionScope.user && it.key.username != sessionScope.user.username}">
                        <a id="${it.key.id}" name="${it.key.nickname}" class="reply write" onclick="reply(this)">回复</a>
                    </c:if>
                </div>
                <p class="cont">${it.key.content}</p>
                <ul>
                    <c:forEach items="${it.value}" var="reply">
                        <li class="reply_li">
                            <div class="eva_title re_title">
                                <p class="nickname nick">${reply.nickname}</p>
                                <p class="evaluate_time">${reply.time}</p>
                            </div>
                            <p class="cont">回复&nbsp;${it.key.nickname}：&nbsp;${reply.content}</p>
                        </li>
                    </c:forEach>
                </ul>
            </li>
        </c:forEach>        
    </ul>
    <div class="space"></div>
    <c:if test="${!empty sessionScope.user}">
        <div id="write_eva">
            <form action="${pageContext.request.contextPath}/evaluate" method="post" onsubmit="return checkEmpty()">
                <input type="hidden" name="movie" value="${movie.name}">
                <input type="hidden" id="eva_id" name="eva_id" value="">
                <p><textarea id="eva_cont" rows="8" title="写几句评价" placeholder="写几句评价" name="evaluate_content"></textarea></p>
                <p><input type="submit" class="submit" value="发表"></p>
            </form>
        </div>        
    </c:if>
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
