var app = angular.module("stockModule", ["xeditable", "ui.bootstrap", 'ui.select', 'ngSanitize', 'ngMaterial', 'ngMessages']);

app.run(function (editableOptions) {
    editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2', 'default'
});

app.controller("stockController", function ($http, $mdDialog) {
    var $sctrl = this;
    $sctrl.currentPage = 1;
    $sctrl.rowAmount = 10;

    $http
        .get('http://localhost:62655/api/Stock/GetPaged?pageNo=1&pageSize=' + $sctrl.rowAmount)
        .then(function (response) {
            $sctrl.someStock = response.data;
            $sctrl.noOfPages = $sctrl.someStock.Paging.PageCount;
            console.log("Page Count: " + $sctrl.noOfPages);
            $sctrl.totalItems = $sctrl.someStock.Paging.TotalRecordCount;
            $sctrl.currentPage = $sctrl.someStock.Paging.PageNo;
        })
        .catch(function (errorResponse) {
            $sctrl.context = "Something went wrong with getting issues";
        });

    $sctrl.searchAll = function () {
        $http.get('http://localhost:62655/api/Stock?search=' + $sctrl.stockSearch)
        .then(function (response) {
            $sctrl.someStock.Data = response.data;
        });
    }

    $sctrl.saveStock = function (data, id) {
        //$scope.user not updated yet
        //angular.extend(data, { id: id });
        //return $http.post('/saveUser', data);
        console.log(data);
        console.log("Stock ID: " + id);
        data.StockReferenceID = id;
        console.log(data);
        $http.put('http://localhost:62655/api/Stock', data);
    };

    $sctrl.paginationChange = function () {
        console.log("Pagination change event")
        console.log("Page no: " + $sctrl.paginationPage)
    }

    $sctrl.pageChanged = function () {
        console.log("Page changed function");
        console.log("Current Page: " + $sctrl.currentPage + " Row amount " + $sctrl.rowAmount);
        $http.get('http://localhost:62655/api/Stock/GetPaged?pageNo=' + $sctrl.currentPage + '&pageSize=' + $sctrl.rowAmount)
        .then(function (response) {
            $sctrl.someStock = response.data;
        });
        //$log.log('Page changed to: ' + $octrl.s);
    };

});