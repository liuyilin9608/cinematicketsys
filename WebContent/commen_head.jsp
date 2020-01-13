<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<title>Get Shit Done Kit by Creative Tim</title>
    <link href="${pageContext.request.contextPath}/UI/bootstrap3/css/bootstrap.css" rel="stylesheet" />
	  <link href="${pageContext.request.contextPath}/UI/assets/css/gsdk.css" rel="stylesheet" />  
    <link href="${pageContext.request.contextPath}/UI/assets/css/demo.css" rel="stylesheet" /> 
    
</head>
<body>
	<div id="navbar-full">
    <div class="container">
        <nav class="navbar navbar-ct-blue navbar-transparent navbar-fixed-top" role="navigation">
          
          <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a href="#">
                     <div class="logo-container">
                        <div class="logo">
                            <img src="${pageContext.request.contextPath}/UI/assets/img/new_logo.png">
                        </div>
                        <div class="brand" style="margin-top:15px;">
                           	user
                        </div>
                    </div>
                </a>
            </div>
        
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
              <ul class="nav navbar-nav navbar-right">
			            <c:if test="${empty sessionScope.user }">
			                <li><a href="${pageContext.request.contextPath}/admin">后台管理</a></li>
			                <li><a href="${pageContext.request.contextPath}/login">登录</a></li>
			                <li><a href="${pageContext.request.contextPath}/reg">注册</a></li>
			            </c:if>
			            <c:if test="${!empty sessionScope.user }">
			                <li><a href="${pageContext.request.contextPath}/admin">后台管理</a></li>
			                <li><a href="${pageContext.request.contextPath}/my">个人中心</a></li>
			                <li><a href="${pageContext.request.contextPath}/logout" onclick="return confirmAction('确定退出吗？')">退出</a></li>
			            </c:if>
               </ul>
              
            </div><!-- /.navbar-collapse -->
          </div><!-- /.container-fluid -->
        </nav>
    </div><!--  end container-->
    
    <div class='blurred-container'>
        <div class="motto">
            <div>Get</div>
            <div class="border no-right-border">Sh</div><div class="border">it</div>
            <div>Done</div>
        </div>
        <div class="img-src" style="background-image: url('${pageContext.request.contextPath}/UI/assets/img/cover_4.jpg')"></div>
        <div class='img-src blur' style="background-image: url('${pageContext.request.contextPath}/UI/assets/img/cover_4_blur.jpg')"></div>
    </div>
    
</div>     
</body>
 <script src="${pageContext.request.contextPath}/UI/jquery/jquery-1.10.2.js" type="text/javascript"></script>
  	<script src="${pageContext.request.contextPath}/UI/assets/js/jquery-ui-1.10.4.custom.min.js" type="text/javascript"></script>
  	<script src="${pageContext.request.contextPath}/UI/bootstrap3/js/bootstrap.js" type="text/javascript"></script>
  	<script src="${pageContext.request.contextPath}/UI/assets/js/gsdk-checkbox.js"></script>
  	<script src="${pageContext.request.contextPath}/UI/assets/js/gsdk-radio.js"></script>
  	<script src="${pageContext.request.contextPath}/UI/assets/js/gsdk-bootstrapswitch.js"></script>
  	<script src="${pageContext.request.contextPath}/UI/assets/js/get-shit-done.js"></script>
    <script src="${pageContext.request.contextPath}/UI/assets/js/custom.js"></script>
</html>