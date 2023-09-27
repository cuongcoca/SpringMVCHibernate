<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 9/22/2023
  Time: 9:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="<%=request.getContextPath()%>/resources/angular/angular.min.js"></script>
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.7.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/project/upload/index.js"></script>
    <script>
        let preUrl = '<%=request.getContextPath()%>';
    </script>
    <style type="text/css">
        .tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
        .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
        .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
        .tg .tg-4eph{background-color:#f9f9f9}
    </style>
</head>
<body>
<div ng-app="myApp" ng-controller="myCtrl">
    <%--    <input type="file" name="file" id="file"--%>
    <%--           class="filestyle" data-icon="false"--%>
    <%--           onchange="angular.element(this).scope().fileValidate()"--%>
    <%--           data-classButton="btn btn-default" accept="image/*"--%>
    <%--           data-classInput="form-control inline input-s"/>--%>
    <%--    <button class="btn" id="btnUpload" ng-click="uploadFile()">uploadFile</button>--%>

    <form method="POST" action="<%=request.getContextPath()%>/upload-file/upload" enctype="multipart/form-data">
        <table>
            <tr>
                <td><input type="file" name="file"
                           onchange="angular.element(this).scope().fileValidate()"
                           accept="image/*"/></td>
            </tr>
            <tr>
                <td>
                    <img id="imgUp" style="width: 100px;height: auto;"
                         src="<%=request.getContextPath()%>/">
                <td>
            </tr>
            <tr>
                <td><input type="submit" value="Submit"/></td>
            </tr>
        </table>
    </form>

        Danh sách Ảnh
        <table class="tg">
            <tr>
                <th>STT</th>
                <th>Tên file</th>
                <th>Đường dẫn</th>
                <th>productId</th>
                <th>userId</th>
                <th>Ngày tạo</th>
                <th>Id</th>
                <th>#</th>
            </tr>
            <tr ng-repeat="item in listData.items track by $index">
                <td>{{(listData.pageNumber - 1) * listData.numberPerPage + $index + 1}}</td>
                <td>{{item.fileName}}</td>
                <td>{{item.url}}</td>
                <td>{{item.productId}}</td>
                <td>{{item.userId}}</td>
                <td>{{item.genDate | date: 'dd/MM/yyyy HH:mm:ss'}}</td>
                <td>{{item.id}}</td>
                <td><img width="100px;" src="<%=request.getContextPath()%>/upload-file/files/{{item.fileName}}"></td>
            </tr>
            <tr>
                <td colspan="10" ng-show="listData.rowCount == 0" style="text-align: center">Không có dữ liệu</td>
            </tr>
        </table>
        <table class="tg">
            <tr>
                <td>Số bản ghi: {{listData.rowCount}}</td>
                <td>
                    <button style="float: left;margin-left: 10px;" ng-if="listData.pageNumber > 1" ng-click="loadPageData(1)">«</button>
                    <div style="float: left; margin-left: 10px;" ng-repeat="item in listData.pageList track by $index">
                        <button style="color:mediumvioletred;" ng-if="item == listData.pageNumber" ng-click="loadPageData($index +1)">{{$index + 1}}</button>
                        <button ng-if="item != listData.pageNumber" ng-click="loadPageData($index +1)">{{$index + 1}}</button>
                    </div>
                    <button style="float: left;margin-left: 10px;" ng-if="listData.pageNumber < listData.pageCount" ng-click="loadPageData(listData.pageCount)">»</button>
                </td>
            </tr>
        </table>
</div>
</body>
</html>
