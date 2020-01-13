<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>排片</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form.css">
    <style>
        .content {
            width: 400px;
            height: 405px;
        }
        .content select {
            width: 250px;
            height: 40px;
            margin-top: 15px;
            padding-left: 5px;
        }
    </style>
    <c:if test="${!empty success}">
        <script>
            alert("排片成功!");
            window.location.href="${pageContext.request.contextPath}/admin/movies";
        </script>
    </c:if>
    <script>
        function checkDateFormat() {
            var playTime = document.getElementsByName("play_time");
            var timeStr = playTime[0].value;
            if (/^\d{4}\/\d{2}\/\d{2} \d{2}:\d{2}$/.test(timeStr)) {
                playTime[0].value = new Date(timeStr + ":00").getTime();
                return true
            }
            var errorMsg = document.getElementsByClassName("error_msg");
            errorMsg[0].innerHTML = "放映时间格式不正确";
            return false
        }
    </script>
</head>
<body>
<div class="content">
    <form action="${pageContext.request.contextPath}/admin/plans/add" method="post" onsubmit="return checkDateFormat()">
        <p class="title">排片</p>
        <p><input type="text" class="input_box" title="片名" disabled="disabled" value="${movie_name}"></p>
        <p><input name="movie_name" type="hidden" class="input_box" value="${movie_name}"></p>
        <p>
            <select name="hall_name" title="选择影厅">
            <c:forEach items="${halls}" var="hall">
                <option value="${hall.name}" ${hall.name == hall_name ? "selected='selected'" : ""}>${hall.name}</option>
            </c:forEach>
            </select>
        </p>
        <p><input type="text" name="price" title="单价(￥)" placeholder="单价(￥)" class="input_box" value="${empty price ? "" : (price > 0 ? price: "")}"></p>
        <p><input type="text" name="play_time" class="input_box" title="放映时间，格式：2017/01/01 00:00" placeholder="放映时间，格式：2017/01/01 00:00"
                  value="${empty play_time ? "" : play_time}"></p>
        <p><input type="submit" class="submit" value="排片"></p>
        <p class="error_msg">${empty msg ? "" : msg }</p>
    </form>
</div>
</body>
<script src="${pageContext.request.contextPath}/js/my.js"></script>
<script>    
    var playTime = document.getElementsByName("play_time");
    if (/^\d+$/.test(playTime[0].value)) {
        playTime[0].value = formatDate(parseInt(playTime[0].value));
    }
</script>
</html>
