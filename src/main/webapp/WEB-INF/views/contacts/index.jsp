<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 6/25/2023
  Time: 10:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Contacts</title>
    <link rel="shortcut icon" href="https://upload.wikimedia.org/wikipedia/commons/thumb/e/ea/BMW_logo_%28white_%2B_grey_background_square%29.svg/2048px-BMW_logo_%28white_%2B_grey_background_square%29.svg.png">
    <script src="<%=request.getContextPath()%>/resources/angular/angular.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/project/contacts/index.js"></script>
</head>
<body ng-app="myApp" ng-controller="myCtrl">
Danh sách danh bạ
<table>
    <tr>
        <th>Id</th>
        <th>Số điện thoại</th>
        <th>Họ và tên</th>
        <th>Ngày sinh</th>
        <th>Địa chỉ</th>
        <th>Ghi chú</th>
    </tr>
    <tr ng-repeat="item in listData">
        <th>{{item.id}}</th>
        <th>{{item.phone}}</th>
        <th>{{item.fullName}}</th>
        <th>{{item.birthday | date: 'dd/MM/yyyy'}}</th>
        <th>{{item.address}}</th>
        <th>{{item.note}}</th>
    </tr>
</table>


<table>
    <tr>
        <th>Số điện thoại</th>
        <th>Họ và tên</th>
        <th>Ngày sinh</th>
        <th>Địa chỉ</th>
        <th>Ghi chú</th>
    </tr>
    <tr>
        <th><input type="text" ng-model="contacts.phone"></th>
        <th><input type="text" ng-model="contacts.fullName"></th>
        <th><input type="date" ng-model="contacts.birthday"></th>
        <th><input type="text" ng-model="contacts.address"></th>
        <th><input type="text" ng-model="contacts.note"></th>
    </tr>
</table>
<button ng-click="addContacts()">ADD CONTACTS</button>

</body>
</html>
