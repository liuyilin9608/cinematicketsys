<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${add ? "添加影片" : "修改影片"}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form.css">
    <style>
        .content {
            width: 600px;
            height: 750px;
            margin:80px auto;
        }
        .content .input_box {
            width: 450px;
        }
        .content .img {
            width: 450px;
            height: 40px;
            margin-top: 15px;
        }
        .content textarea {
            width: 450px;
            resize: none;
        }
    </style>
</head>
<body>
<div class="content">
    <form action="${pageContext.request.contextPath}/admin/movies/${add ? "add" : "update"}" method="post" enctype="multipart/form-data" >
        <p class="title">${add ? "添加影片" : "修改影片"}</p>
        <c:if test="${!add}">
            <input name="id" type="hidden" value="${id}">
            <p><input type="text" class="input_box" title="片名" placeholder="片名"
                      value="${empty name ? "" : name}" disabled="disabled"></p>
            <input name="name" type="hidden" value="${name}">
        </c:if>
        <c:if test="${add}">
            <p><input type="text" name="name" class="input_box" title="片名" placeholder="片名"
                      value="${empty name ? "" : name}"></p>
        </c:if>        
        <p><input type="text" name="director" class="input_box" title="导演" placeholder="导演"
                  value="${empty director ? "" : director}"></p>
        <p><input type="text" name="protagonist" class="input_box" title="主演" placeholder="主演"
                  value="${empty protagonist ? "" : protagonist}"></p>
        <p><input type="text" name="type" class="input_box" title="类型" placeholder="类型"
                  value="${empty type ? "" : type}"></p>
        <p><input type="text" name="region" class="input_box" title="国家 / 地区" placeholder="国家 / 地区"
                  value="${empty region ? "" : region}"></p>
        <p><input type="text" name="language" class="input_box" title="语言" placeholder="语言"
                  value="${empty language ? "" : language}"></p>
        <c:if test="${!add}">
            <p><input type="text" class="input_box" title="片长(分钟)" placeholder="片长(分钟)"
                      value="${empty duration ? "" : duration}" disabled="disabled"></p>
            <input name="duration" type="hidden" value="${duration}">
        </c:if>
        <c:if test="${add}">
            <p><input type="text" name="duration" class="input_box" title="片长(分钟)" placeholder="片长(分钟)"
                      value="${empty duration ? "" : duration}"></p>
        </c:if>        
        <p><input type="file" name="file" class="img" title="选择影片封面图片"></p>
        <p><textarea rows="8" title="剧情简介" placeholder="剧情简介" name="synopsis">${empty synopsis ? "" : synopsis}</textarea></p>
        <p><input type="submit" class="submit" value="${add ? "添加" : "修改"}"></p>
        <p class="error_msg">${empty msg ? "" : msg }</p>
    </form>
</div>
</body>
</html>
