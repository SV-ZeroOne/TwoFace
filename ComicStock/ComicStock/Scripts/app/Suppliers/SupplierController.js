var app = angular.module("supplierModule", ["xeditable", "ui.bootstrap", "ngMessages", 'ngSanitize', 'ngMaterial']);

app.run(function (editableOptions) {
    editableOptions.theme = 'bs3';
})

app.controller("supplierController", function ($http, $mdDialog) {
    var $ictrl = this;
    $ictrl.showMe = false;
    $ictrl.newSupplier = {};
    $ictrl.currentPage = 1;
    $ictrl.rowAmount = 10;
    $ictrl.someSuppliers;
    $http
        .get('../api/Suppliers/GetPaged?pageNo=1&pageSize=' + $ictrl.rowAmount)
        .then(function (response) {
            $ictrl.someSuppliers = response.data;
            $ictrl.noOfPages = $ictrl.someSuppliers.Paging.PageCount;
            $ictrl.totalItems = $ictrl.someSuppliers.Paging.TotalRecordCount;
            $ictrl.currentPage = $ictrl.someSuppliers.Paging.PageNo;
        })
        .catch(function (errorResponse) {
            $ictrl.context = "Something went wrong with getting suppliers";
        });

    $ictrl.showNewSupplier = function () {
        $ictrl.showMe = !$ictrl.showMe;
    }

    $ictrl.placeSupplier = function () {
        $http
          .post('../api/Suppliers/PlaceSupplier?name=' + $ictrl.newSupplier.name + '&city=' + $ictrl.newSupplier.city)
     }

    //update order
    $ictrl.saveSupplier = function (data, id) {
        console.log(data);
        console.log("Supplier ID: " + id);
        data.SupplierID = id;
        console.log(data);
        $http.put('../api/Suppliers', data);
    };

    $ictrl.searchAll = function () {
        $http.get('../api/Suppliers?search=' + $ictrl.suppliersSearch)
        .then(function (response) {
            console.log("search Funtion called")
            $ictrl.someSuppliers.Data = response.data;
        });
    }

    $ictrl.removeSupplier = function (data) {
        console.log(data);
        data.IsDeleted = true;
        $http.put("../api/Suppliers", data);
    }
    //$ictrl.showAlert = function () {
    //    $mdDialog.show(
    //      $mdDialog.alert()
    //        .parent(angular.element(document.querySelector('#popupContainer1')))
    //        .clickOutsideToClose(true)
    //        .title('New Supplier Confirmation')
    //        .textContent('New Supplier has been added.')
    //        .ariaLabel('Supplier Confirmation Dialog')
    //        .ok('Ok')
    //        .targetEvent(ev)
    //    );
    //};

    $ictrl.paginationChange = function () {
        console.log("Pagination change event")
        console.log("Page no: " + $ictrl.paginationPage)
    }

    $ictrl.pageChanged = function () {
        console.log("Page changed function");
        console.log("Current Page: " + $ictrl.currentPage + " Row amount " + $ictrl.rowAmount);
        $http.get('../api/Suppliers/GetPaged?pageNo=' + $ictrl.currentPage + '&pageSize=' + $ictrl.rowAmount)
        .then(function (response) {
            $ictrl.someSuppliers = response.data;
        });
    };
});