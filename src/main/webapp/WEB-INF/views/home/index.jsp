<%--
  Date: 9/22/2023
  Time: 9:07 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Trang chủ</title>
    <style>
        a {
            text-decoration: none;
        }
    </style>
</head>
<body>
<h1>hello</h1>
<a href="<c:url value='/persons' />">Quản lý Person</a>
<br/>
<%--<a href="<c:url value='/upload' />">Quản lý Ảnh</a>--%>
<a href="<%=request.getContextPath()%>/upload-file/index.html">Quản lý Ảnh</a>
</body>
</html>
