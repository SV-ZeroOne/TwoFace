var app = angular.module("stockModule", ["xeditable", "ui.bootstrap", 'ui.select', 'ngSanitize', 'ngMaterial', 'ngMessages', 'cgBusy']);

app.run(function (editableOptions) {
    editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2', 'default'
});

app.controller("stockController", function ($http, $mdDialog) {
    var $sctrl = this;
    $sctrl.currentPage = 1;
    $sctrl.rowAmount = 10;
    $sctrl.showMe = false;
    $sctrl.selectedIssue;
    $sctrl.stockSearch = '';
    $sctrl.someStock;
    $sctrl.searchFlag = false;

    $sctrl.myPromise = $http
        .get('../api/Stock/GetPaged?pageNo=1&pageSize=' + $sctrl.rowAmount)
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

    $http
       .get('../api/Issues/GetPaged?pageNo=1&pageSize=10')
       .then(function (response) {
           console.log("Getting issues")
           $sctrl.someIssues = response.data;
       })
       .catch(function (errorResponse) {
           $sctrl.context = "Something went wrong with getting issues";
       });

    $sctrl.searchAll = function () {
        $sctrl.myPromise = $http.get('../api/Stock/GetSearchPaged?searchKey=' + $sctrl.stockSearch + '&pageNumber=' + $sctrl.currentPage)
        .then(function (response) {
            $sctrl.someStock = response.data;
            $sctrl.noOfPages = $sctrl.someStock.Paging.PageCount;
            $sctrl.totalItems = $sctrl.someStock.Paging.TotalRecordCount;
            $sctrl.currentPage = $sctrl.someStock.Paging.PageNo;
            $sctrl.searchFlag = true;
            });   
        };

    $sctrl.saveStock = function (data, id) {
        //$scope.user not updated yet
        //angular.extend(data, { id: id });
        //return $http.post('/saveUser', data);
        console.log(data);
        console.log("Stock ID: " + id);
        data.StockReferenceID = id;
        console.log(data);
        $http.put('../api/Stock', data);
    };

    $sctrl.paginationChange = function () {
        console.log("Pagination change event")
        console.log("Page no: " + $sctrl.paginationPage)
    }

    $sctrl.pageChanged = function () {
        console.log("Page changed function");
        console.log("Current Page: " + $sctrl.currentPage + " Row amount " + $sctrl.rowAmount);
        if ($sctrl.searchFlag) {
            $sctrl.searchAll();
            console.log("Paged Search Results")
        } else {
            $http.get('../api/Stock/GetPaged?pageNo=' + $sctrl.currentPage + '&pageSize=' + $sctrl.rowAmount)
            .then(function (response) {
                console.log("Getting new paged results.")
                $sctrl.someStock = response.data;
        });
     }
    };

    $sctrl.showNewStock = function () {
        $sctrl.showMe = !$sctrl.showMe;
    }

    $sctrl.refreshIssues = function (search) {
        switchToPage(search, 1);
    }

    function switchToPage(searchKey, page) {
        //if ($octrl.searchCriteria == null) $octrl.searchCriteria = "";
        console.log("searching")
        $sctrl.myPromise = $http.get('../api/Issues/GetSearchPaged?searchKey=' + searchKey + ' &pageNumber=' + page)
        .then(function (response) {
            $sctrl.someIssues = response.data;
        }, function (error) {
            console.log("error searching")
        });
    }

    $sctrl.showAlert = function (ev) {
        $mdDialog.show(
          $mdDialog.alert()
            .parent(angular.element(document.querySelector('#popupContainer1')))
            .clickOutsideToClose(true)
            .title('New Stock Confirmation')
            .textContent('New stock has been added.')
            .ariaLabel('Stock Confirmation Dialog')
            .ok('Ok')
            .targetEvent(ev)
        );
    };

    $sctrl.makeStock = function () {
        var stockDTO = {
            "IssueID": $sctrl.selectedIssue.IssueID,
            "Condition": $sctrl.selectedCondition,
            "AvailableQuantity": $sctrl.Quantity,
            "Price": $sctrl.Price
        }
        console.log("Making stock")
        console.log(stockDTO);
        $http
            .post('../api/Stock', stockDTO);
    }

    $sctrl.restoreAll = function(){
        $sctrl.myPromise = $http
        .get('../api/Stock/GetPaged?pageNo=1&pageSize=' + $sctrl.rowAmount)
        .then(function (response) {
            $sctrl.searchFlag = false;
            $sctrl.someStock = response.data;
            $sctrl.noOfPages = $sctrl.someStock.Paging.PageCount;
            console.log("Page Count: " + $sctrl.noOfPages);
            $sctrl.totalItems = $sctrl.someStock.Paging.TotalRecordCount;
            $sctrl.currentPage = $sctrl.someStock.Paging.PageNo;
        })
        .catch(function (errorResponse) {
            $sctrl.context = "Something went wrong with getting issues";
        });
    }

});