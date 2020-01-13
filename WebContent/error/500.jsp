<%@ page pageEncoding="UTF-8" isErrorPage="true"%>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        .content {
            width: 400px;
            height: 200px;
            margin: 200px auto;
            background: white;
            text-align: center;
            padding-top: 40px;
        }
    </style>
</head>

<body>
    <div class="content">
        <p>服务出错了!</p>
        <p>出错原因：${pageContext.exception.localizedMessage}</p>
    </div>
</body>
</html>
