var app = angular.module('myApp', []);
app.controller('myCtrl', ['$scope', '$http', function ($scope, $http) {
    $scope.file = null;

    $scope.loadListData = function () {
        $http.get("search", {
            params: {}
        }).then(function (response) {
            if (response != null && response.status == 200) {
                $scope.listData = response.data;
            }
        })
    }

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

    $scope.loadListData();
}]);