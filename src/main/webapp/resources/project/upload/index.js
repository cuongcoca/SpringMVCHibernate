var app = angular.module('myApp', []);
app.controller('myCtrl', ['$scope', '$http', function ($scope, $http) {
    $scope.file = null;

    $scope.uploadFile = function () {

        // var form = new FormData();
        // var file = $scope.file;
        // form.append("file", file);
        // // var jsonStr = JSON.stringify($scope.detailObj);
        // // form.append("newsDetailStr", jsonStr);
        // var settings = {
        //     "async": true,
        //     "crossDomain": true,
        //     "url": preUrl + "/upload-file/upload",
        //     "method": "POST",
        //     "headers": {},
        //     "processData": false,
        //     "contentType": false,
        //     "mimeType": "multipart/form-data",
        //     "data": form
        // };
        //
        // $.ajax(settings).done(function (response) {
        //     switch (Number(response)) {
        //         case 1:
        //             // toastr.success("Lưu thành công!");
        //             break;
        //         case -1:
        //             // toastr.error("Lưu thất bại!");
        //             $('#btnUpload').removeAttr('disabled');
        //             break;
        //         default:
        //             // toastr.error("Lưu thất bại!");
        //             $('#btnUpload').removeAttr('disabled');
        //             break;
        //     }
        // });
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
}]);