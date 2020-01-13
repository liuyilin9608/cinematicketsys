<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form.css">
    <style>
        .content {
            width: 400px;
            height: 520px;
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

        .div_submit {
            width: 250px;
            height: 40px;
            margin: 0 auto;
        }
        
        .error_msg {
            margin-top: 90px;
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
    <form action="${pageContext.request.contextPath}/reg" method="post">
        <p class="title">用户注册</p>
        <p><input type="text" name="username" class="input_box" title="用户名" placeholder="用户名"
                  value="${empty regUser.username ? "" : regUser.username}"></p>
        <p><input type="password" name="password" class="input_box" title="密码" placeholder="密码"
                  value="${empty regUser.password ? "" : regUser.password}"></p>
        <p><input type="password" name="confirmPwd" class="input_box" title="确认密码" placeholder="确认密码"
                  value="${empty confirmPwd ? "" : confirmPwd}"></p>
        <p><input type="text" name="nickname" class="input_box" title="昵称" placeholder="昵称"
                  value="${empty regUser.nickname ? "" : regUser.nickname}"></p>
        <p><input type="text" name="phone" class="input_box" title="手机号" placeholder="手机号"
                  value="${empty regUser.phone ? "" : regUser.phone}"></p>
        <div class="div_code"><input type="text" name="valicateCode" class="code" title="验证码" placeholder="验证码"></div>
        <div class="div_valicate_img"><img src="${pageContext.request.contextPath }/code" id="valicateImg" onclick="changeImg()"></div>
        <div class="div_submit"><input type="submit" class="submit" value="注册"></div>
        <p class="error_msg">${empty msg ? "" : msg }</p>
    </form>
</div>
</body>
</html>
