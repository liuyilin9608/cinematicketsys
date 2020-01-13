<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改资料</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mgr_head.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form.css">
    <style type="text/css">
        .content {
            width: 1000px;
            height: 300px;
            margin: 20px auto;
            background: #efe;
        }
        .content form {
            margin-top: 30px;
        }
        .nav_bar {
            margin: 0;
          
        }
        .homefoot{
			width: auto;
			height:100px;
			text-align: center;
			background: #111;
			line-height: 100px;
			margin-top: 40px;
	}
    </style>
    <c:if test="${!empty success}">
        <script type="text/javascript">
            alert("修改成功");
            window.location.href = "${pageContext.request.contextPath}/my/update";
        </script>
    </c:if>
</head>
<body>
<%@include file="commen_head.jsp" %>
<div class="main" style="overflow:hidden;">
<div class="content">
    <%@include file="my_head.jsp" %>
    <form action="${pageContext.request.contextPath}/my/update" method="post">
        <input type="hidden" name="username" value="${sessionScope.user.username}">
        <p><input type="text" name="nickname" class="input_box" title="昵称" placeholder="昵称" value="${nickname == null ? sessionScope.user.nickname : nickname}"></p>
        <p><input type="text" name="phone" class="input_box" title="手机号" placeholder="手机号" value="${phone == null ? sessionScope.user.phone : phone}"></p>
        <p><input type="submit" class="submit" value="修改"></p>
        <p class="error_msg">${empty msg ? "" : msg }</p>
    </form>
</div>
</div>
<div class="homefoot">
 &copy; <script>document.write(new Date().getFullYear())</script> Get Shit Done Kit by  Creative Tim, More Templates <a href="http://www.cssmoban.com/" target="_blank">难毕业工作室</a> - Collect from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">难毕业工作室</a>.
</div>
</body>
</html>
