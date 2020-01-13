<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>选座购票</title>
    <c:if test="${!empty failed}">
        <script>
            alert(${failed});
            window.location.href = "${pageContext.request.contextPath}/${planId}";
        </script>
    </c:if>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/head.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/seat_style.css"/>
    <script>
        var selectNum = 0;
        var price = ${price};
        
        function changeImg(node) {
            if (node.alt !== "sold") {
                if (selectNum >= 6 && node.alt === "available") {
                    alert("一次最多购买6张")
                } else {
                    var inputNode = document.getElementsByName(node.className);
                    if (node.alt === "available") {
                        node.src = "images/selected.png";
                        node.alt = "selected";
                        inputNode[0].value = 1;
                        selectNum++;
                    } else {
                        node.src = "images/available.png";
                        node.alt = "available";
                        inputNode[0].value = 0;
                        selectNum--;
                    }
                    var amount = Math.floor(price * selectNum * 100) / 100;
                    var num = amount.toString();
                    var index = num.indexOf(".");
                    if (index < 0) {
                        index = num.length;
                        num += ".";
                    }
                    while (num.length <= index + 2) {
                        num += "0";
                    }
                    document.getElementById("total").innerText = "￥" + num;
                }                
            }
        }  
        
        function checkSeat() {
            if (selectNum <= 0) {
                alert("您还未选择座位");
                return false
            } else {                
                return true;
            }
        }
    </script>
</head>
<body>
    <div class="content">
        <%@include file="head.jsp"%>
        <div class="desc">
            <img class="desc_img" src="images/available.png"><span>可选</span>
            <img class="desc_img" src="images/sold.png"><span>已售</span>
            <img class="desc_img" src="images/selected.png"><span>已选</span>
            <p class="amount">合计:&nbsp;</p>
            <p id="total">￥0</p>
        </div>
        <p class="screen">银幕</p>
        <div class="seat_area">
            <form action="${pageContext.request.contextPath}/order" method="post" onsubmit="return checkSeat()">
                <input type="hidden" name="session" value="${session}">
                <c:forEach var="rowSeats" items="${seats}">
                    <div class="row">
                        <c:forEach var="seat" items="${rowSeats}">
                            <img src="${seat.sold ? "images/sold.png" : "images/available.png"}" alt="${seat.sold ? "sold" : "available"}" class="${seat.seat}" onclick="changeImg(this)">
                            <input name="${seat.seat}" value="0" type="hidden">
                        </c:forEach>
                    </div>
                </c:forEach>
                <p class="sub"><input type="submit" value="确定选座" class="submit"></p>
            </form>
        </div>     
        <c:if test="${!empty order}">
            <form id="order" action="${pageContext.request.contextPath}/pay" method="post">
                <input type="hidden" name="movie_name" value="${order.movie_name}">
                <input type="hidden" name="plan_id" value="${order.plan_id}">
                <input type="hidden" name="user_id" value="${order.user_id}">
                <input type="hidden" name="seat" value="${order.seat}">
                <input type="hidden" name="amount" value="${order.amount}">
            </form>            
        </c:if>
    </div>
</body>
<script>
    if (${!empty order}) {
        document.getElementById('order').submit();
    }    
</script>
</html>
