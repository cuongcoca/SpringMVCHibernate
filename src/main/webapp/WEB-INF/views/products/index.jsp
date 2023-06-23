<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Danh sách sản phẩm</title>
  <script src="<%=request.getContextPath()%>/resources/angular/angular.min.js"></script>
  <script src="<%=request.getContextPath()%>/resources/project/products/index.js"></script>
</head>
<body ng-app="myApp" ng-controller="myCtrl">
Danh sách sản phẩm
<table>
  <tr>
    <th>id</th>
    <th>name</th>
    <th>price</th>
    <th>#</th>
  </tr>
  <tr ng-repeat="item in listData">
    <th>{{item.id}}</th>
    <th>{{item.name}}</th>
    <th>{{item.price}}</th>
    <th>Full Name: {{firstName + " " + lastName}}</th>
  </tr>
</table>
</body>
</html>
<%--<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>--%>
<%--<script type="text/javascript">--%>
<%--  $.ajax--%>
<%--  ({--%>
<%--    url: "search",--%>
<%--    type: "get",--%>
<%--    async: false,--%>
<%--    success: function(data)--%>
<%--    {--%>
<%--      console.log(data);--%>
<%--    }--%>
<%--  });--%>
<%--</script>--%>
