<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>电影票</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mgr_head.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tickets_style.css" />
</head>
<body>
<div class="content">
    <%@include file="my_head.jsp" %>
    <c:if test="${fn:length(tickets) <= 0}">
        <p class="empty_list">暂无电影票</p>
    </c:if>
    <div class="list">
        <ul>
            <c:forEach items="${tickets }" var="ticket">
                <li>
                    <a href="${pageContext.request.contextPath}/movie/detail?movie=${ticket.movieName}">
                        <img class="pic" src="${pageContext.request.contextPath}/img?file=${ticket.picName}" title="${ticket.movieName}">
                    </a>
                    <div class="info">
                        <p class="movie_name">
                            <a href="${pageContext.request.contextPath}/movie/detail?movie=${ticket.movieName}">${ticket.movieName}</a>
                                &nbsp;&nbsp;${ticket.ticketCount}张
                        </p>
                        <p class="movie_info">${ticket.playTime}~${ticket.endTime}&nbsp;&nbsp;${ticket.language}</p>
                        <p class="hall_info">${ticket.hallName}&nbsp;&nbsp;${ticket.hallType}&nbsp;&nbsp;
                            <c:forEach items="${ticket.seats}" var="seat">
                                ${seat}&nbsp;
                            </c:forEach>
                        </p>
                        
                        <p class="money">￥${ticket.totalPrice}</p>
                        <p class="order_id">订单号&nbsp;&nbsp;${ticket.orderId}</p>
                        <p class="buy_time">购买时间&nbsp;&nbsp;${ticket.buyTime}</p>
                    </div>
                    <p class="state">${ticket.state}</p>
                    <form action="${pageContext.request.contextPath}/evaluate" method="post">
                        <input type="hidden" name="movie" value="${ticket.movieName }">
                        <input type="submit" class="blue_a write" value="写影评">
                    </form>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
</body>
</html>
