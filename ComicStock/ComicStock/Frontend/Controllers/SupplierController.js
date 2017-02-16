angular.module("SquareEyesModule")
.run(function (editableOptions) {
    editableOptions.theme = 'bs3';
})
.controller("supplierController", function ($http, $mdDialog) {
    var $ictrl = this;
    $ictrl.showMe = false;
    $ictrl.newSupplier = {};
    $ictrl.currentPage = 1;
    $ictrl.rowAmount = 10;
    $ictrl.someSuppliers;
    $ictrl.searchFlag = false;

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

    $ictrl.saveSupplier = function (data, id, refnum) {
        console.log(data);
        console.log("Supplier ID: " + id);
        data.SupplierID = id;
        data.ReferenceNumber = refnum;
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

    $ictrl.searchAll = function () {
        $ictrl.myPromise = $http.get('../api/Suppliers/GetSearchPaged?searchKey=' + $ictrl.suppliersSearch + '&pageNumber=' + $ictrl.currentPage)
        .then(function (response) {
            $ictrl.someSuppliers = response.data;
            $ictrl.noOfPages = $ictrl.someSuppliers.Paging.PageCount;
            $ictrl.totalItems = $ictrl.someSuppliers.Paging.TotalRecordCount;
            $ictrl.currentPage = $ictrl.someSuppliers.Paging.PageNo;
            $ictrl.searchFlag = true;
        });
    };

    $ictrl.removeSupplier = function (data) {
        console.log(data);
        data.IsDeleted = true;
        $http.put("../api/Suppliers", data).then(function (response) {
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

        });
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
        if ($ictrl.searchFlag) {
            $ictrl.searchAll();
            console.log("Paged Search Results")
        } else {
            $http.get('../api/Suppliers/GetPaged?pageNo=' + $ictrl.currentPage + '&pageSize=' + $ictrl.rowAmount)
            .then(function (response) {
                console.log("Getting new paged results.")
                $ictrl.someSuppliers = response.data;
            });
        }
    };

    $ictrl.changeRows = function () {
        $ictrl.pageChanged();
    }

    $ictrl.restoreAll = function () {
        $ictrl.myPromise = $http
        .get('../api/Suppliers/GetPaged?pageNo=1&pageSize=' + $ictrl.rowAmount)
        .then(function (response) {
            $ictrl.searchFlag = false;
            $ictrl.someSuppliers = response.data;
            $ictrl.noOfPages = $ictrl.someSuppliers.Paging.PageCount;
            console.log("Page Count: " + $ictrl.noOfPages);
            $ictrl.totalItems = $ictrl.someSuppliers.Paging.TotalRecordCount;
            $ictrl.currentPage = $ictrl.someSuppliers.Paging.PageNo;
        })
        .catch(function (errorResponse) {
            $sctrl.context = "Something went wrong with getting issues";
        });
    }

    $ictrl.column = '';
    $ictrl.reverse = false;
    $ictrl.orderTableBy = function orderTableBy(columnName) {
        $ictrl.column = columnName;
        $ictrl.reverse = !$ictrl.reverse;
    };

});