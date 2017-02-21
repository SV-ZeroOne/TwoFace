angular.module("SquareEyesModule")
.run(function (editableOptions) {
    editableOptions.theme = 'bs3';
})
.controller("ordersController4", function ($http, $mdDialog) {
    var $octrl = this;
    $octrl.showMe = false;
    $octrl.newOrder = {};
    $octrl.currentPage = 1;
    $octrl.rowAmount = 10;

    $octrl.ordersSearch = '';
    $octrl.selectedIssue;
    $octrl.issueSet = true;
    $octrl.newOrder.qtyOrdered = 0;
    $octrl.someOrders;
    $octrl.searchFlag = false;
    $octrl.column = '';
    $octrl.reverse = false;

    $octrl.myPromise = $http
        .get('../api/Orders/GetPaged?pageNo=1&pageSize=' + $octrl.rowAmount)
        .then(function (response) {
            $octrl.someOrders = response.data;
            $octrl.noOfPages = $octrl.someOrders.Paging.PageCount;
            console.log("Page Count: " + $octrl.noOfPages);
            $octrl.totalItems = $octrl.someOrders.Paging.TotalRecordCount;
            $octrl.currentPage = $octrl.someOrders.Paging.PageNo;
        })
        .catch(function (errorResponse) {
            $octrl.context = "Something went wrong with getting orders";
        });

    $http
        .get('../api/Issues/GetPaged?pageNo=1&pageSize=10')
        .then(function (response) {
            console.log("Getting issues")
            $octrl.someIssues = response.data;
        })
        .catch(function (errorResponse) {
            $octrl.context = "Something went wrong with getting issues";
        });
    

    $octrl.showNewOrder = function () {
        $octrl.showMe = !$octrl.showMe;
    }

    $octrl.placeOrder = function () {
        $http
          .post('../api/Orders/PlaceOrder?issueID=' + $octrl.selectedIssue.IssueID + '&quantity=' + $octrl.newOrder.qtyOrdered + '&supplierID=' + $octrl.selectedQuotes.SupplierID)
          .then(function (response) {
              $octrl.theNewOrder = response.data;
          }, function (error) {
              
          })
    }

    // delete order
    $octrl.removeOrder = function (index, orderID) {
        console.log("Deleting Order No" + orderID)
        $octrl.someOrders.Data.splice(index, 1);
    };

    //update order
    $octrl.saveOrder = function (data, id) {
        console.log(data);
        console.log("Order ID: " + id);
        data.OrderID = id;
        console.log(data);
        $http.put('../api/Orders', data);
    };

    $octrl.paginationChange = function () {
        console.log("Pagination change event")
        console.log("Page no: " + $octrl.paginationPage)
    }

    $octrl.pageChanged = function () {
        console.log("Page changed function");
        console.log("Current Page: " + $octrl.currentPage + " Row amount " + $octrl.rowAmount);
        if ($octrl.searchFlag) {
            $octrl.searchAll();
        } else {
            $octrl.myPromise = $http.get('../api/Orders/GetPaged?pageNo=' + $octrl.currentPage + '&pageSize=' + $octrl.rowAmount)
            .then(function (response) {
                console.log("Getting new paged results.")
                $octrl.someOrders = response.data;
        });
        }
    };

    $octrl.changeRows = function () {
        $octrl.pageChanged();
    }

    $octrl.searchAll = function () {
        $octrl.myPromise = $http.get('../api/Orders/GetSearchPaged?searchKey=' + $octrl.ordersSearch + '&pageNumber=' + $octrl.currentPage)
        .then(function (response) {
            $octrl.someOrders = response.data;
            $octrl.noOfPages = $octrl.someOrders.Paging.PageCount;
            $octrl.totalItems = $octrl.someOrders.Paging.TotalRecordCount;
            $octrl.currentPage = $octrl.someOrders.Paging.PageNo;
            $octrl.searchFlag = true;
        });
    }

    $octrl.refreshIssues = function (search) {
        switchToPage(search, 1);
    }

    function switchToPage(searchKey, page) {
        console.log("searching")
        $octrl.myPromise = $http.get('../api/Issues/GetSearchPaged?searchKey=' + searchKey + '&pageNumber=' + page)
        .then(function (response) {
            $octrl.someIssues = response.data;
        }, function (error) {
            console.log("error searching")
        });
    }

    $octrl.getQuotes = function (item, model) {
        console.log('Getting quotes')
        $octrl.issueSet = true;
        $http.get("../api/SupplierQuote?issueID=" + $octrl.selectedIssue.IssueID)
        .then(function (response) {

            if (response.data.length > 0) {
                $octrl.quotes = response.data;
                $octrl.issueSet = false;
            }
            else {
                $octrl.quotes = [{ Price: "No quote available" }];
                $octrl.selectedQuotes = $octrl.quotes[0].Price;
                console.log($octrl.selectedQuotes)
            }

        }, function (error) {
            console.log('Some error getting quotes')
        });
    }

    //Place order dialog popup
    $octrl.showAlert = function (ev) {
        $mdDialog.show(
          $mdDialog.alert()
            .parent(angular.element(document.querySelector('#popupContainer')))
            .clickOutsideToClose(true)
            .title('Order Details')
            .textContent('Your order has been placed!')
            .ariaLabel('Order Confirmation Dialog')
            .ok('Ok')
            .targetEvent(ev)
        );
    };

    $octrl.restoreAll = function () {
        $octrl.myPromise = $http
        .get('../api/Orders/GetPaged?pageNo=1&pageSize=' + $octrl.rowAmount)
        .then(function (response) {
            $octrl.searchFlag = false;
            $octrl.someOrders = response.data;
            $octrl.noOfPages = $octrl.someOrders.Paging.PageCount;
            console.log("Page Count: " + $octrl.noOfPages);
            $octrl.totalItems = $octrl.someOrders.Paging.TotalRecordCount;
            $octrl.currentPage = $octrl.someOrders.Paging.PageNo;
        })
        .catch(function (errorResponse) {
            $octrl.context = "Something went wrong with getting issues";
        });
    }

    $octrl.orderTableBy = function orderTableBy(columnName) {
        console.log("trying to filter")
        $octrl.column = columnName;
        $octrl.reverse = !$octrl.reverse;
    };

    $octrl.datePickerConfig = {
        changeYear: true,
        changeMonth: true
    };

});