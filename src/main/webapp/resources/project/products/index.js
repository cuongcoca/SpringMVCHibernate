var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http) {
    $scope.firstName= "John";
    $scope.lastName= "Doe";

    $scope.loadListData = function () {
        $http.get("search", {
            params: {}
        }).then(function (response) {
            if (response != null && response.status == 200) {
                $scope.listData = response.data;
                // console.log($scope.listData);
            }
        })
    }

    $scope.loadListData();

});