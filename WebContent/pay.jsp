<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>付款</title>    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/head.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pay_style.css"/>
    <script>
        function showDialog() {
            var content = document.getElementsByClassName("content")[0];
            content.style.opacity = 0.4;
            var btn = document.getElementById("sub");
            btn.disabled = true;
            
            var type = 0;
            var aliWay = document.getElementById("ali");
            aliWay.disabled = true;
            if (aliWay.checked) {
                type = 0;
            }
            var wx = document.getElementById("wx");
            wx.disabled = true;
            if (wx.checked) {
                type = 1;
            }
            
            if (type === 1) {
                var wechat = document.getElementsByClassName("wechat")[0];
                wechat.style.visibility = 'visible';
            } else {
                var alipay = document.getElementsByClassName("alipay")[0];
                alipay.style.visibility = 'visible';
            }
            setTimeout(function () {
                btn.disabled = false;
                wx.disabled = false;
                aliWay.disabled = false;
                var order = document.getElementById("order");
                order.submit()
            }, 2000);
        }
    </script>
</head>
<body>
    <div class="content">
        <%@include file="head.jsp"%>
        <table>
            <tr>
                <th>影片</th>
                <th>时间</th>
                <th>座位</th>
                <th>单价</th>
                <th>数量</th>
            </tr>
            <tr>
                <td>${ticket.movieName}</td>
                <td>${ticket.playTime}</td>
                <td>
                    ${ticket.hallName}&nbsp;
                    <c:forEach items="${ticket.seats}" var="seat">
                        ${seat}&nbsp;
                    </c:forEach>
                </td>
                <td class="price">￥${ticket.price}</td>
                <td>${ticket.ticketCount}</td>
            </tr>
        </table> 
        <div class="space"></div>
        <div class="pay_way">
            <p>选择支付方式:</p><br>
            <label>
                <input type="radio" id="ali" name="way" checked="checked" value="0">支付宝&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </label>
            <label>
                <input type="radio" id="wx" name="way" value="1">微信
            </label>
        </div>

        <div class="right">
            <p class="real_pay">实际支付：<span class="money price">￥${ticket.totalPrice}</span></p>
            <button class="submit" onclick="showDialog()" id="sub">确认支付</button>
        </div>
        
        <form id="order" action="${pageContext.request.contextPath}/pay" method="post">
            <input type="hidden" name="movie_name" value="${order.movie_name}">
            <input type="hidden" name="plan_id" value="${order.plan_id}">
            <input type="hidden" name="user_id" value="${order.user_id}">
            <input type="hidden" name="seat" value="${order.seat}">
            <input type="hidden" name="amount" value="${order.amount}">
            <input type="hidden" name="pay" value="pay">
        </form>
    </div>
    <div class="wechat">
        <p>使用微信扫码付款</p><br>
        <img src="images/wechat_code.png" >
    </div>
    <div class="alipay">
        <p>使用支付宝扫码付款</p><br>
        <img src="images/alipay.png" >
    </div>
</body>
</html>
