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
        <table border="1">
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
                <th>{{$index + 1}}</th>
                <th>{{item.fileName}}</th>
                <th>{{item.url}}</th>
                <th>{{item.productId}}</th>
                <th>{{item.userId}}</th>
                <th>{{item.genDate | date: 'dd/MM/yyyy HH:mm:ss'}}</th>
                <th>{{item.id}}</th>
                <th><img width="100px;" src="{{getSrcBase64Image(item.base64Img)}}"></th>
            </tr>
        </table>
        <table>
            <tr>
                <th>Số bản ghi: {{listData.rowCount}}</th>
                <th>
                    <div style="float: left; padding-left: 10px;" ng-repeat="item in listData.pageList track by $index">
                        <button ng-click="loadPageData($index +1)">{{$index + 1}}</button>
                    </div>
                </th>
            </tr>
        </table>
</div>
</body>
</html>
