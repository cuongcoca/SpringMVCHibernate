var app = angular.module('myApp', []);
app.controller('myCtrl', function ($scope, $http) {
    $scope.contacts = {};

    $scope.loadListData = function () {
        $http.get("search", {
            params: {}
        }).then(function (response) {
            if (response != null && response.status == 200) {
            $scope.listData = response.data;
            }
        })
    }

    $scope.loadListData();

    $scope.addContacts = function () {
        console.log("ADD functions");
        $http.post("add", JSON.stringify($scope.contacts)).then(function (response) {
            if (response != null && response.status == 200) {
                if (response.data == 1) {
                    console.log("add success!");
                } else {
                    console.log("add failed!");
                }
                $scope.loadListData();
            }
        })
    }
});