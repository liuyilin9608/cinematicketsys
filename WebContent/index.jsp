<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script>
        function goToHomePage() {
            window.location.href="${pageContext.request.contextPath}/home";
        }
    </script>
</head>
<body onload="goToHomePage()">

</body>
</html>
