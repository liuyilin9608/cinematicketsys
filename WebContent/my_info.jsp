<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.demo.entity.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人信息</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mgr_head.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
    <style>
        .content {
            width: 1000px;
            height: 300px;
            background: #efe;
            margin: 0 auto;
            position: relative;
            margin-bottom: 60px;
           
        }
        .content table {
            padding-top: 16px;
            width: 320px;
            margin-left:50px;
            margin: 0 auto;
            margin-left:50px;
            color: #333;
            border-spacing: 0;
        }
        .content table td {
            padding: 14px 0;
          
            width:100px;
           	
        }
        .content table .title {
            color: #333;
            font-weight: bold;
            width: 90px;
        }
        .homefoot{
		width: auto;
		height:100px;
		text-align: center;
		background: #111;
		line-height: 100px;
		
	}
    </style>
</head>
<body>
 <%@include file="commen_head.jsp" %>
<div class="main" style="overflow:hidden;">
<div class="content">    
    <%@include file="my_head.jsp" %>
    <table>
        <tr>
            <td class="title">用户名:</td>
            <td>${sessionScope.user.username}</td>
        </tr>
        <tr>
            <td class="title">昵称:</td>
            <td>${sessionScope.user.nickname}</td>
        </tr>
        <tr>
            <td class="title">手机号:</td>
            <td>${sessionScope.user.phone}</td>
        </tr>
        <tr>
            <td class="title">注册时间:</td>
            <td><%
                User user = (User) request.getSession().getAttribute("user");
                out.print(new SimpleDateFormat("yyyy/MM/dd HH:mm").format(user.reg_time)); %></td>
        </tr>
    </table>
</div>
<div class="homefoot">
 &copy; <script>document.write(new Date().getFullYear())</script> Get Shit Done Kit by  Creative Tim, More Templates <a href="http://www.cssmoban.com/" target="_blank">难毕业工作室</a> - Collect from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">难毕业工作室</a>.
</div>
</html>
