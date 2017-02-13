var app = angular.module("ordersModule", ["xeditable", "ui.bootstrap"]);

app.run(function (editableOptions) {
    editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2', 'default'
});

app.controller("ordersController4", function ($http) {
    var $octrl = this;
    $octrl.showMe = false;
    $octrl.newOrder = {};
    $octrl.currentPage = 1;

    $http
        .get('http://localhost:62655/api/Orders/GetPaged?pageNo=1&pageSize=50')
        .then(function (response) {
            $octrl.someOrders = response.data;
            $octrl.noOfPages = $octrl.someOrders.Paging.PageCount;
            console.log("Page Count: " + $octrl.noOfPages);
            $octrl.totalItems = $octrl.someOrders.Paging.TotalRecordCount;
            $octrl.currentPage = $octrl.someOrders.Paging.PageNo;
        })
        .catch(function (errorResponse) {
            $octrl.context = "Something went wrong with getting issues";
        });

    $octrl.showNewOrder = function () {
        $octrl.showMe = !$octrl.showMe;
    }

    $octrl.placeOrder = function () {
        $http
          .post('http://localhost:62655/api/Orders/PlaceOrder?issueID=' + $octrl.newOrder.issueID + '&quantity=' + $octrl.newOrder.qtyOrdered + '&supplierID=' + $octrl.newOrder.supplierID);

    }

    // delete order
    $octrl.removeOrder = function (index , orderID) {
        console.log("Deleting Order No" + orderID)
        $octrl.someOrders.Data.splice(index, 1);
        //Cascade Error, however delete should not be possible for orders because of business logic.
        //If you want to make it work delete supplier payment based on order id first.
        //$http.delete('http://localhost:62655/api/Orders?orderID=' + orderID);
    };

    //update order
    $octrl.saveOrder = function (data, id) {
        //$scope.user not updated yet
        //angular.extend(data, { id: id });
        //return $http.post('/saveUser', data);
        console.log(data);
        console.log("Order ID: " + id);
        data.OrderID = id;
        console.log(data);
        $http.put('http://localhost:62655/api/Orders', data);
    };

    $octrl.paginationChange = function () {
        console.log("Pagination change event")
        console.log("Page no: " + $octrl.paginationPage)
    }

    $octrl.pageChanged = function () {
        console.log("Page changed function");
        $log.log('Page changed to: ' + $octrl.currentPage);
    };
    
});