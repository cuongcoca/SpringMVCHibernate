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
                           accept="image/*" /></td>
            </tr>
            <tr>
                <td><input type="submit" value="Submit"/></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
