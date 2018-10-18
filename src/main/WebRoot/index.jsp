<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/10/18
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>Hello</title>
    <script src="<%=basePath%>js/jquery-2.1.0.js"></script>
    <script>
        $(document).ready(function () {
            console.log("123");
        });
    </script>
</head>
<body>
<h1>Hello World!</h1>

</body>
</html>
