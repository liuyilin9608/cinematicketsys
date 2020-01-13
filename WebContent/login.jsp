<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form.css">
    <style type="text/css">
        .content {
            width: 400px;
            height: 350px;
        }
        .code {
            width: 144px;
            padding-left: 10px;
            background: rgba(0, 0, 0, .1);
            height: 40px;
            border: solid #ccc 1px;
            border-radius: 3px;
        }
        .div_code {
            width: 144px;
            height: 40px;
            float: left;
            margin-top: 15px;
            margin-left: 75px;
        }
        .div_valicate_img {
            width: 100px;
            height: 40px;
            float: right;
            margin-right: 75px;
            margin-top: 15px;
        }
    </style>
    <script type="text/javascript">
        function changeImg(codeImg) {
            codeImg = document.getElementById("valicateImg");
            codeImg.src = codeImg.src + "?time=" + new Date().getTime();
        }
    </script>
</head>
<body>
<div class="content">
    <form action="${pageContext.request.contextPath}/login" method="post">
        <c:if test="${!empty from}">
            <input type="hidden" name="from" value="${from}">
        </c:if>
        <p class="title">用户登录</p>
        <p><input type="text" name="username" class="input_box" title="用户名" placeholder="用户名" value="${username}"></p>
        <p><input type="password" name="password" class="input_box" title="密码" placeholder="密码" value="${password}"></p>
        <div class="div_code"><input type="text" name="valicateCode" class="code" title="验证码" placeholder="验证码"></div>
        <div class="div_valicate_img"><img src="${pageContext.request.contextPath }/code" id="valicateImg" onclick="changeImg()"></div>
        <p><input type="submit" class="submit" value="登录"></p>
        <p class="error_msg">${empty msg ? "" : msg }</p>
    </form>
</div>
</body>
</html>
