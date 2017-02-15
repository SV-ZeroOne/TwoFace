//angular.module('SquareEyesApp',[])
//    .controller('CreatorCtrl', function($http){
//        var $ctrl = this;
//    });

var app = angular.module('SquareEyesApp', ["xeditable", "ui.bootstrap"])

app.run(function (editableOptions) {
    editableOptions.theme = 'bs3';
})

app.controller('CreatorsCtrl', function ($http) {

    var $ctrl = this;

    $ctrl.rowAmount = 10;

    $ctrl.showMe = false;

    $ctrl.currentPage = 1;

    $ctrl.jsonObject = {};

    $http
        .get('../api/Creators/GetPaged?pageNo=1&pageSize=' + $ctrl.rowAmount)
        .then(function (response) {
            $ctrl.options = response.data;
            $ctrl.noOfPages = $ctrl.options.Paging.PageCount;
            console.log("Page Count: " + $ctrl.noOfPages);
            $ctrl.totalItems = $ctrl.options.Paging.TotalRecordCount;
            $ctrl.currentPage = $ctrl.options.Paging.PageNo;
        })
        .catch(function (errorResponse) {
            $ctrl.context = "Something went wrong with getting creators";
        });

    $ctrl.addCreator = function () {
        console.log($ctrl.options.name);
        $ctrl.jsonObject = {
            "CountryOfResidence": $ctrl.options.countryOfResidence,
            "CreatorID": 1,
            "EmailAddress": $ctrl.options.email,
            "Name": $ctrl.options.name,
            "TaxReferenceNumber": $ctrl.options.taxReferenceNumber          
        };
        console.log($ctrl.jsonObject);
        $http.post('../api/Creators', $ctrl.jsonObject);
    };
    
    $ctrl.saveCreator = function (data, id) {
        data.CreatorID = id;      
        console.log(data);
        $http.put('../api/Creators', data);
        
    };

    //$ctrl.removeCreator = function (index, id) {
    //    console.log("Deleting Creator No: " + id);
    //    console.log(index);
    //    $ctrl.options.splice(index, 1);
    //    $http.delete('../api/creators/' + id);
    //};

    $ctrl.removeCreator = function (data) {
        data.IsDeleted = true;
        console.log("after: " + data.IsDeleted);
        $http.put('../api/Creators', data);
    };

    //$ctrl.addCreator = function (data, id) {
    //    console.log(data);
    //    console.log(data);
    //    $http.post('../api/creators', data);
    //}

    //$ctrl.removeIssue = function (index, IssueID) {
    //    console.log("Deleting Issue ID: " + IssueID);
    //    $ctrl.options.splice(index, 1);
    //    $http.delete("../api/issues?issueID=" + IssueID);
    //};

    $ctrl.paginationChange = function () {
        console.log("Pagination change event")
        console.log("Page no: " + $ctrl.paginationPage)
    }

    $ctrl.pageChanged = function () {
        console.log("Page changed function");
        console.log("Current Page: " + $ctrl.currentPage + " Row amount " + $ctrl.rowAmount);
        $http.get('../api/Creators/GetPaged?pageNo=' + $ctrl.currentPage + '&pageSize=' + $ctrl.rowAmount)
        .then(function (response) {
            $ctrl.someOrders = response.data;
        });

        //$http.get("../api/issues").then(function (response) {

        //    $ctrl.options = response.data;

        //}).catch(function (errorResponse) {

        //    $ctrl.context = "Oops... Something went wrong";

        //});

    };

    $ctrl.searchAll = function () {
        $http.get('../api/Creators?search=' + $ctrl.creatorSearch)
        .then(function (response) {
            $ctrl.options.Data = response.data;
        });
    }
});
