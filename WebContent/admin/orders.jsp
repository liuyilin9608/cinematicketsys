<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单管理</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mgr_head.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tool_bar.css"/>
    <script src="${pageContext.request.contextPath}/js/my.js"></script>
    <style>
        .list table .blue_a {
            margin: 6px;
        }
        .list table .action {
            padding-left: 40px;
        }
    </style>
    <script>
        function confirmDel(node) {
            if (confirm("确定删除吗？")) {
                window.location.href = "${pageContext.request.contextPath}/admin/orders/delete?id=" + node.name;
            }
        }
        
        function update(node) {
            var amount = prompt("修改金额:");
            if (/^\d+\.\d+$/.test(amount) || /^\d+$/.test(amount)) {
                window.location.href = "${pageContext.request.contextPath}/admin/orders/update?id=" + node.name + "&amount=" + amount;
            } else {
                alert("金额只能是数字");
            }
        }
    </script>
</head>
<body>
<%@include file="head.jsp" %>
<div class="tool_bar">
    <div class="search">
        <input type="text" name="keyword" title="订单号" placeholder="订单号" class="input_box"
               value="${empty keyword ? "" : keyword}">
        <button class="search_action" onclick="search('${pageContext.request.contextPath}/admin/orders')">搜索</button>
    </div>
</div>
<div class="list">
    <table>
        <tr>
            <th class="order_id">订单号</th>
            <th>用户</th>
            <th>下单时间</th>
            <th>金额</th>
            <th class="act"></th>
        </tr>
        <c:if test="${fn:length(orders) <= 0}">
            <td colspan="4">
                暂无订单
            </td>
        </c:if>
        <c:forEach items="${orders }" var="order" varStatus="item">
            <tbody>
            <tr${item.count % 2 != 0 ? " class='odd'" : ""}>
                <td>${order.id}</td>
                <td>${order.username}</td>
                <td class="create_time">${order.create_time}</td>
                <td>￥${order.amount}</td>
                <td class="action">
                    <button class="blue_a" name="${order.id}" onclick="update(this)">修改</button>
                    <button class="blue_a" name="${order.id}" onclick="confirmDel(this)">删除</button>
                </td>
            </tr>
            </tbody>
        </c:forEach>
    </table>
</div>
</body>
<script>
    var nodeList = document.getElementsByClassName("create_time");
    for (var i = 0; i < nodeList.length; i++) {
        if (/^\d+$/.test(nodeList[i].innerHTML)) {
            nodeList[i].innerHTML = formatDate(parseInt(nodeList[i].innerHTML));
        }
    }
</script>
</html>
