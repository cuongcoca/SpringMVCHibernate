var app = angular.module('myApp', []);
app.controller('myCtrl', ['$scope', '$http', function ($scope, $http) {
    $scope.listData = {};

    $scope.pageNumber = 1;
    $scope.numberPerPage = 5;
    $scope.file = null;

    $scope.search = function () {
        $scope.loadPageData(1);
    }

    $scope.loadPageData = function (pageNumber) {
        $scope.pageNumber = pageNumber;
        $http.get("search", {
            params: {
                "pageNumber": $scope.pageNumber,
                "numberPerPage": $scope.numberPerPage
            }
        }).then(function (response) {
            if (response != null && response.status == 200) {
                $scope.listData = response.data;
                $scope.listData.pageList = getPageList($scope.listData);
            }
        })
    }

    $scope.getSrcBase64Image = function (imageUrl) {
        return "data:image/png;base64, " + imageUrl;
    };

    $scope.uploadFile = function () {

    }

    $scope.fileValidate = function () {
        var files = event.target.files;
        var extFile = files[0].name.split('.').pop().toLowerCase();
        if (extFile == "jpg" || extFile == "jpeg" || extFile == "png") {
            if (files[0].size / 1024 / 1024 > 5) {//check file size max 5mb
                toastr.error("Dung lượng file không được quá 5MB");
                angular.element("input[name='file']").val(null);
                return false;
            } else {
                $scope.file = files[0];

                var reader = new FileReader();
                reader.onload = function () {
                    var output = document.getElementById('imgUp');
                    output.src = reader.result;
                };
                reader.readAsDataURL(event.target.files[0]);
            }

        } else {
            toastr.error("Chỉ cho phép upload file có định dạng JPG, JPEG, PNG");
            angular.element("input[name='file']").val(null);
            return false;
        }
    };

    /*Trợ giúp tính toán số trang hiển thị khi hiện page*/
    function getPageList(pagingResult) {
        pagingResult.pageCount = Math.ceil(pagingResult.rowCount / pagingResult.numberPerPage);

        var pages = [];
        var from = pagingResult.pageNumber - 3;
        var to = pagingResult.pageNumber + 5;
        if (from < 0) {
            to -= from;
            from = 1;
        }

        if (from < 1) {
            from = 1;
        }

        if (to > pagingResult.pageCount) {
            to = pagingResult.pageCount;
        }

        for (var i = from; i <= to; i++) {
            pages.push(i);
        }
        return pages;
    }

    $scope.search();
}]);