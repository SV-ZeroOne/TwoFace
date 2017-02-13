var app = angular.module('SquareEyesApp', ["xeditable"])

app.run(function (editableOptions) {
    editableOptions.theme = 'bs3';
})

app.controller('IssuesCtrl', function ($http) {

        var $ctrl = this;

        $ctrl.count = 0;

        $ctrl.test = function () {
            alert('testing button');
        }

        //$ctrl.data = [];

        

        $http.get("http://localhost:62655/api/issues").then(function (response) {
            console.log("I'm in get method");
            $ctrl.options = response.data;
          
        }).catch(function (errorResponse) {

            $ctrl.context = "Oops... Something went wrong";

        });

        $scope.editUser = function (data, id) {
            edit_data.UserID = id;
            $http.post("/User/Edit", edit_data).success(function () {
                angular.extend(data, { id: id });
            });
        };

        $scope.deleteUser = function (index, id) {
            $http.post("/User/Delete", id).success(function () {
                $scope.users.splice(index, 1);
            });
        };

        //$ctrl.checkTitle = function (data, id) {
        //    $ctrl.data = data;
        //    return $ctrl.data;
        //};

        //// update issue
        //$ctrl.saveIssue = function (data, id) {
           
        //    //$ctrl.issue not updated yet
        //    angular.extend(data, { id: id });
        //    return $http.put('http://localhost:62655/api/issues/'+id, data);
        //};

        //// remove issue
        //$scope.removeUser = function (index) {
        //    $ctrl.users.splice(index, 1);
        //};

        //// add issue
        //$scope.addUser = function () {
        //    $ctrl.inserted = {
        //        id: $scope.users.length + 1,
        //        name: '',
        //        status: null,
        //        group: null
        //    };
        //    $ctrl.users.push($ctrl.inserted);
        //};
    });
//.run(function (editableOptions) {
//    editableOptions.theme = 'bs3';
//});