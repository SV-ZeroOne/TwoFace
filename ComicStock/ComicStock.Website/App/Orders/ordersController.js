var app = angular.module("ordersModule", ["ngTable"]);
app.controller("ordersController", ['$http','NgTableParams', '$resource', function ($http, NgTableParams, $resource) {
    var $octrl = this;
    $octrl.showMe = false;
    $octrl.newOrder = {};

    $http
        .get('http://localhost:62655/api/Orders/GetPaged?pageNo=1&pageSize=50')
        .then(function(response) {
            $octrl.someOrders = response.data;
        })
        .catch(function (errorResponse) {
            $octrl.context = "Something went wrong with getting issues";
        });

      $octrl.showNewOrder = function() {
        $octrl.showMe = !$octrl.showMe;
      }

      $octrl.placeOrder = function(){
          $http
            .post('http://localhost:62655/api/Orders/PlaceOrder?issueID=' + $octrl.newOrder.issueID + '&quantity=' + $octrl.newOrder.qtyOrdered + '&supplierID=' + $octrl.newOrder.supplierID);
          
      }

      $octrl.data = [{ name: "Moroni", age: 50 }, { name: "Steve", age: 27 }];
      //$octrl.tableParams = new NgTableParams({}, { dataset: $octrl.someOrders });

      var Api = $resource("http://localhost:62655/api/Orders/GetPaged?pageNo=1&pageSize=50");
      this.tableParams = new NgTableParams({}, {
          getData: function (params) {
              // ajax request to api
              return Api.get(params.url()).$promise.then(function (data) {
                  params.total(data.inlineCount); // recal. page nav controls
                  return data.results;
              });
          }});

}]);