<%@ page import="com.demo.entity.Sale" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>票房统计</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mgr_head.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css"/>
    <script src="${pageContext.request.contextPath}/js/my.js"></script>
    <script src="${pageContext.request.contextPath}/js/echarts.common.min.js"></script>
    <style>
        .content {
            width: 1000px;
            margin: 20px auto;
            background: white;
        }
        .nav_bar {
            margin: 0;
            border-bottom: 1px solid #65B336;
        }
        .space {
            width: 100%;
            height: 10px;
            background: #E9E9E9;
        }
        table {
            width: 100%;
            border-spacing: 0
        }
        table td {
            padding: 18px;
            border: 0;
            text-align: center;
        }
        table th {
            width: 33%;
            padding: 10px;
        }
        table .movie_name {
            width: 34%;
        }
        #echart {
            width: 100%;
            height: 600px;
        }
    </style>
</head>
<body>
<div class="content">
    <div class="nav_bar">
        <div class="nav_title">
            <ul class="menu">
                <li>
                    <a href="${pageContext.request.contextPath}/home">影片</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/sales" class="chked">票房统计</a>
                </li>
            </ul>
        </div>
        <div class="nav_right">
            <c:if test="${empty sessionScope.user }">
                <a href="${pageContext.request.contextPath}/admin">后台管理</a><span class="divider"> | </span>
                <a href="${pageContext.request.contextPath}/login?from=/sales">登录</a><span class="divider"> | </span><a href="${pageContext.request.contextPath}/reg">注册</a>
            </c:if>
            <c:if test="${!empty sessionScope.user }">
                <a href="${pageContext.request.contextPath}/admin">后台管理</a><span class="divider"> | </span>
                <a href="${pageContext.request.contextPath}/my">个人中心</a><span class="divider"> | </span>
                <a href="${pageContext.request.contextPath}/logout?from=/sales" onclick="return confirmAction('确定退出吗？')">退出</a>
            </c:if>
        </div>
    </div>
    
    <div id="echart"></div>
    
    <div class="space"></div>
    
    <table>
        <tr>
            <th class="movie_name">片名</th>
            <th>实时票房(元)</th>
            <th>累计票房(元)</th>
        </tr>
        <c:if test="${fn:length(sales) <= 0}">
            <td colspan="3">
                暂无票房
            </td>
        </c:if>
        <c:forEach var="sale" items="${sales}" varStatus="item">
            <tr${item.count % 2 != 0 ? " class='odd'" : ""}>
                <td><a href="${pageContext.request.contextPath}/movie/detail?movie=${sale.movieName}">${sale.movieName}</a></td>
                <td>${sale.realtime}</td>
                <td>${sale.total}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
<script>
    encodeAllURI();

    var yAxisData = [];
    var seriesData = [];
    <%
    List<Sale> valueList = (List<Sale>) request.getAttribute("sales");
    Collections.reverse(valueList);
    double others = 0;
    for(int i = 0; i < valueList.size(); i++) {
        Sale sale = valueList.get(i);
        if (i < 10) {%>
    yAxisData.push("<%=sale.movieName%>");
    seriesData.push(<%=sale.realtime%>);
    <%} else {
            others += sale.realtime;
        }
    } 
    if (valueList.size() > 10) {%>
    yAxisData.push("其它");
    seriesData.push(<%=others%>);
    <%}%>
    
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('echart'));

    option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        legend: {
            data: ['实时票房(元)']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'value',
            boundaryGap: [0, 0.01]
        },
        yAxis: {
            type: 'category',
            data: yAxisData
        },
        series: [
            {
                name: '实时票房(元)',
                type: 'bar',
                color: '#37A2DA',
                data: seriesData
            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>
</html>
