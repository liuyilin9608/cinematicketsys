<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改密码</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mgr_head.css" />
    <style type="text/css">
        .content {
            width: 1000px;
            height: 300px;
            margin: 20px auto;
        }
        .content form {
            margin-top: 30px;
        }
        .nav_bar {
            margin: 0;
            border-bottom: 1px solid #65B336;
        }
    </style>
    <c:if test="${!empty success}">
        <script type="text/javascript">
            alert("修改成功");
            window.location.href = "${pageContext.request.contextPath}/my/chgpwd";
        </script>
    </c:if>
</head>
<body>
<div class="content">
    <%@include file="my_head.jsp" %>
    <form action="${pageContext.request.contextPath}/my/chgpwd" method="post">        
        <p><input type="password" name="password" class="input_box" title="原密码" placeholder="原密码" value="${empty password ? "" : password}"></p>
        <p><input type="password" name="newpwd" class="input_box" title="新密码" placeholder="新密码" value="${empty newpwd ? "" : newpwd}"></p>
        <p><input type="submit" class="submit" value="修改"></p>
        <p class="error_msg">${empty msg ? "" : msg }</p>
    </form>
</div>
</body>
</html>
