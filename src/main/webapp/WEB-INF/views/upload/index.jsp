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
    <meta charset="UTF-8">
    <script src="<%=request.getContextPath()%>/resources/angular/angular.min.js"></script>
    <!-- Liên kết CSS Bootstrap -->
    <link href="<%=request.getContextPath()%>/resources/vendor/bootstrap-4.5.0/css/bootstrap.min.css" type="text/css"
          rel="stylesheet"/>
    <!-- Liên kết Jquery -->
    <script src="<%=request.getContextPath()%>/resources/vendor/jquery/jquery.min.js"></script>
    <!-- Liên kết PopperJS -->
    <script src="<%=request.getContextPath()%>/resources/vendor/popperjs/popper.min.js"></script>
    <!-- Liên kết Bootstrap -->
    <script src="<%=request.getContextPath()%>/resources/vendor/bootstrap-4.5.0/js/bootstrap.js"></script>

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

    <div class="container">
        <div class="row" style="padding-top: 30px;">
            <div class="col-lg-6">
            <form method="POST" action="<%=request.getContextPath()%>/upload-file/upload" enctype="multipart/form-data">
                <div class="form-group">
                    <input class="form-control" style="border: none" type="file" name="file"
                           onchange="angular.element(this).scope().fileValidate()"
                           accept="image/*"/>
                </div>
                <div class="form-group">
                    <img id="imgUp" style="width: 100px;height: auto;"
                         src="<%=request.getContextPath()%>/">
                </div>
                <div class="form-group">
                    <input class="btn btn-primary mb-3" type="submit" value="Upload"/>
                </div>
            </form>
            </div   >
        </div>
        <div class="row">
            <div class="col-lg-12">
                <label>Danh sách Ảnh</label>
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead class="bg-gray">
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
                        </thead>
                        <tr ng-repeat="item in listData.items track by $index">
                            <td>{{(listData.pageNumber - 1) * listData.numberPerPage + $index + 1}}</td>
                            <td>{{item.fileName}}</td>
                            <td>{{item.url}}</td>
                            <td>{{item.productId}}</td>
                            <td>{{item.userId}}</td>
                            <td>{{item.genDate | date: 'dd/MM/yyyy HH:mm:ss'}}</td>
                            <td>{{item.id}}</td>
                            <td><img width="100px;"
                                     src="<%=request.getContextPath()%>/upload-file/files/{{item.fileName}}">
                            </td>
                        </tr>
                        <tr>
                            <td colspan="10" ng-show="listData.rowCount == 0" style="text-align: center">Không có dữ
                                liệu
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-6">
                <label>Số bản ghi: {{listData.rowCount}}</label>
            </div>
            <div class="col-lg-6">
                <nav aria-label="Page navigation example">
                    <ul class="pagination float-right">
                        <li class="page-item">
                            <a class="page-link" href="#" aria-label="Previous"
                               ng-if="listData.pageNumber > 1"
                               ng-click="loadPageData(1)">
                                <span aria-hidden="true">&laquo;</span>
                                <span class="sr-only">Previous</span>
                            </a>
                        </li>
                        <li ng-repeat="item in listData.pageList track by $index" class="page-item">
                            <a style="color: mediumvioletred;" ng-if="item == listData.pageNumber"
                               ng-click="loadPageData($index +1)"
                               class="page-link" href="#">{{item}}</a>
                            <a ng-if="item != listData.pageNumber"
                               ng-click="loadPageData($index +1)"
                               class="page-link" href="#">{{item}}</a>
                        </li>
                        <li class="page-item">
                            <a class="page-link" href="#" aria-label="Next"
                               ng-if="listData.pageNumber < listData.pageCount"
                               ng-click="loadPageData(listData.pageCount)">
                                <span aria-hidden="true">&raquo;</span>
                                <span class="sr-only">Next</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>

    </div>

</div>
</body>
</html>
