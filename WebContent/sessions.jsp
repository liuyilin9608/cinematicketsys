<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>选择场次</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/head.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sessions_style.css"/>
    <script src="${pageContext.request.contextPath}/js/my.js"></script>
</head>
<body>
<div class="content">
    <%@include file="head.jsp"%>
    <div class="list">
        <ul>
            <c:forEach items="${sessions}" var="session" varStatus="item">
                <li${item.count % 2 != 0 ? " class='odd'" : ""}>
                    <p class="col date">${session.date}</p>
                    <div class="col">
                        <p class="play_time">${session.time}</p>
                        <p class="over_time">${session.overTime}散场</p>
                    </div>
                    <div class="col">
                        <p class="hall_name">${session.hallName}</p>
                        <p class="seats">${session.hallType}</p>
                    </div>
                    <p class="col price">￥${session.price}</p>
                    <div class="col action">
                        <a class="select_seat" href="${pageContext.request.contextPath}/seat?session=${session.planInfo}">选座</a>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
</body>
<script>
    encodeAllURI();
</script>
</html>
