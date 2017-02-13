//agGrid.initialiseAgGridWithAngular1(angular);

var module = angular.module("ordersModule", []);

module.controller("ordersController3", function ($http) {
    var $octrl = this;
    $octrl.showMe = false;
    $octrl.newOrder = {};

    $http
       .get('http://localhost:62655/api/Orders/GetPaged?pageNo=1&pageSize=5')
       .then(function (response) {
           $octrl.someOrders = response.data;
           $octrl.orders = $octrl.someOrders[0];
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

    // gets the template to ng-include for a table row / item
    $octrl.getTemplate = function (o) {
        console.log('Getting template');
        if (o.OrderID === $octrl.someOrders.Data.OrderID) return 'edit';
        else return 'display';
    };

    $octrl.editContact = function (o) {
        $octrl.someOrders.Data = angular.copy(o);
    };

    $octrl.saveContact = function (idx) {
        console.log("Saving order");
        $octrl.someOrders.orders[idx] = angular.copy($octrl.someOrders.Data);
        $octrl.reset();
    };

    $octrl.reset = function () {
        $octrl.someOrders.Datas = {};
    };

//    document.addEventListener('DOMContentLoaded', function() {

//        var gridDiv = document.querySelector('#myGrid');

//        var gridOptions = {
//            columnDefs: [
//                    { headerName: "Order ID", field: "OrderID" },
//                    { headerName: "Order Date", field: "OrderDate" },
//                    { headerName: "Issue ID", field: "IssueID" },
//                    { headerName: "Qty Ordered", field: "QtyOrdered" },
//                    { headerName: "Total", field: "Total" },
//        { headerName: "Supplier ID", field: "SupplierID" },
//        { headerName: "Shipment Ref", field: "ShipmentRef" },
//        { headerName: "Shipment Date", field: "ShipmentDate" },
//        { headerName: "Delivery Status", field: "DeliveryStatus" }
//            ]
//        };

//        new agGrid.Grid(gridDiv, gridOptions);

//        jsonLoad( function(data) {
//            gridOptions.api.setRowData(data);
//        });
//    });



//function jsonLoad(callback) {
//    var xhr = new XMLHttpRequest();
//    xhr.open('GET', 'http://localhost:62655/api/Orders/GetPaged?pageNo=1&pageSize=5'); // by default async
//    xhr.responseType = 'json'; // in which format you expect the response to be

//    xhr.onload = function() {
//        if(this.status == 200) {// onload called even on 404 etc so check the status
//            callback(this.response);
//        }
//    };

//    xhr.onerror = function() {
//        console.log('loading data error');
//    };

//    xhr.send();
//}


    //var columnDefs = [
    //    { headerName: "Order ID", field: "OrderID" },
    //    { headerName: "Order Date", field: "OrderDate" },
    //    { headerName: "Issue ID", field: "IssueID" },
    //    { headerName: "Qty Ordered", field: "QtyOrdered" },
    //    { headerName: "Total", field: "Total" },
    //    { headerName: "Supplier ID", field: "SupplierID" },
    //    { headerName: "Shipment Ref", field: "ShipmentRef" },
    //    { headerName: "Shipment Date", field: "ShipmentDate" },
    //    { headerName: "Delivery Status", field: "DeliveryStatus" }
    //];

    //var rowData = [
    //    { make: "Toyota", model: "Celica", price: 35000 },
    //    { make: "Ford", model: "Mondeo", price: 32000 },
    //    { make: "Porsche", model: "Boxter", price: 72000 }
    //];

    

    //$octrl.gridOptions = {
    //    columnDefs: columnDefs,
    //    rowData: $octrl.orders
    //};

});