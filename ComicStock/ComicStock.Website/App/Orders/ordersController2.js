var app = angular.module("ordersModule", ["$http", "ngTable", "ngResource"]);
(function(){
    app.controller("ordersController2", ordersController);
    ordersController.$inject = ["$http", "NgTableParams", "$resource"];

    var $octrl = this;
    $octrl.showMe = false;
    $octrl.newOrder = {};

    $http
       .get('http://localhost:62655/api/Orders/GetPaged?pageNo=1&pageSize=5')
       .then(function (response) {
           $octrl.someOrders = response.data;
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

    function ordersController(NgTableParams, $resource){
        //$octrl.tableParams = new NgTableParams({}, { dataset: $octrl.someOrders });
        debugger;
        var Api = $resource("/data")
        $octrl.tableParams = new NgTableParams({}, {
            getData: function (params) {
                return Api.get(params.url()).$promise.then(function(data) {
                    params.total(data.inlineCount); // recal. page nav controls
                    return data.results;
                });
            }
        });
    }
})();
    

   

    
