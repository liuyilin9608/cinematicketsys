<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员登录</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form.css">
    <style type="text/css">
        .content {
            width: 400px;
            height: 300px;           
        }
    </style>
</head>
<body>
<div class="content">
    <form action="${pageContext.request.contextPath}/admin/login" method="post">
        <p class="title">管理员登录</p>
        <p><input type="text" name="username" class="input_box" title="用户名" placeholder="用户名" value="${empty username ? "" : username}"></p>
        <p><input type="password" name="password" class="input_box" title="密码" placeholder="密码"></p>
        <p><input type="submit" class="submit" value="登录"></p>
        <p class="error_msg">${empty msg ? "" : msg }</p>
    </form>
</div>
</body>
</html>
