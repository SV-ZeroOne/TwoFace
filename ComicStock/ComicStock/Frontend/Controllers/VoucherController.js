angular.module("SquareEyesModule")
.run(function (editableOptions) {
    editableOptions.theme = 'bs3';
})
.filter('valididity', function() {
    return function(input) {
        return input ? 'Valid' : 'Invalid';
    }
})
.controller("voucherController", function ($http, $scope, $mdDialog) {
    var $ictrl = this;
    $ictrl.showMe = false;
    $ictrl.newVoucher = {};
    $ictrl.currentPage = 1;
    $ictrl.rowAmount = 10;
    $ictrl.someVouchers;
    $ictrl.searchFlag = false;
    $ictrl.column = '';
    $ictrl.reverse = false;

    $http
        .get('../api/Vouchers/GetPaged?pageNo=1&pageSize='+$ictrl.rowAmount)
        .then(function (response) {
            $ictrl.someVouchers = response.data;
            $ictrl.noOfPages = $ictrl.someVouchers.Paging.PageCount;
            $ictrl.totalItems = $ictrl.someVouchers.Paging.TotalRecordCount;
            $ictrl.currentPage = $ictrl.someVouchers.Paging.PageNo;
        })
        .catch(function (errorResponse) {
            $ictrl.context = "Something went wrong with getting vouchers";
        });

    $ictrl.showNewVoucher = function () {
        $ictrl.showMe = !$ictrl.showMe;
    }

    $ictrl.placeVoucher = function () {
        $http
          .post('../api/Vouchers/PlaceVoucher?amount=' + $ictrl.newVoucher.amount + '&valid=' + $ictrl.newVoucher.valid).then($ictrl.showAdding());
    }

    // remove voucher
    $ictrl.removeVoucher = function (index, VoucherID) {
        $ictrl.someVouchers.Data.splice(index, 1);
        $http.delete('../api/Vouchers/' + VoucherID);
        $ictrl.showAlert();
    };

    //update voucher
    $ictrl.saveVoucher = function (data, id, code) {
        console.log(data);
        console.log("Voucher ID: " + id);
        data.VoucherID = id;
        data.Code = code;
        console.log(data.Valid);
        console.log(data);
        $http.put('../api/Vouchers/', data).then($ictrl.showUpdate);
    };

    $ictrl.showAlert = function () {
        // Appending dialog to document.body to cover sidenav in docs app
        // Modal dialogs should fully cover application
        // to prevent interaction outside of dialog
        $mdDialog.show(
          $mdDialog.alert()
            .parent(angular.element(document.querySelector('#popupContainer')))
            .clickOutsideToClose(true)
            .title('Removoval of voucher')
            .textContent('The selected voucher has been deleted')
            .ariaLabel('Voucher Deletion Dialog')
            .ok('Ok')
            .targetEvent()
        );
    };

    $ictrl.showUpdate = function () {
        $mdDialog.show(
            $mdDialog.alert()
            .parent(angular.element(document.querySelector('#popupContainer')))
            .clickOutsideToClose(true)
            .title('Saving of voucher')
            .textContent('The voucher has been successfully edited')
            .ariaLabel('Voucher Editing Dialog')
            .ok('Ok')
            .targetEvent()
            );
    };

    $ictrl.showAdding = function () {
        $mdDialog.show(
            $mdDialog.alert()
            .parent(angular.element(document.querySelector('#popupContainer')))
            .clickOutsideToClose(true)
            .title('Saving of voucher')
            .textContent('The voucher has been successfully added')
            .ariaLabel('Voucher Addition Dialog')
            .ok('Ok')
            .targetEvent()
            );
    };

    //$ictrl.searchAll = function () {
    //    $http.get('../api/Vouchers?search=' + $ictrl.vouchersSearch)
    //    .then(function (response) {
    //        $ictrl.someVouchers.Data = response.data;
    //    });
    //}

    $ictrl.searchAll = function () {
        $ictrl.myPromise = $http.get('../api/Stock/GetSearchPaged?searchKey=' + $ictrl.vouchersSearch + '&pageNumber=' + $ictrl.currentPage)
        .then(function (response) {
            $ictrl.someVouchers = response.data;
            $ictrl.noOfPages = $ictrl.someVouchers.Paging.PageCount;
            $ictrl.totalItems = $ictrl.someVouchers.Paging.TotalRecordCount;
            $ictrl.currentPage = $ictrl.someVouchers.Paging.PageNo;
            $ictrl.searchFlag = true;
        });
    };

    $scope.Valid = [
    { value: 1, text: 'Valid' },
    { value: 2, text: 'Invalid' },
    ];

    $ictrl.paginationChange = function () {
        console.log("Pagination change event")
        console.log("Page no: " + $ictrl.paginationPage)
    }

    //$ictrl.pageChanged = function () {
    //    console.log("Page changed function");
    //    console.log("Current Page: " + $ictrl.currentPage + " Row amount " + $ictrl.rowAmount);
    //    $http.get('../api/Vouchers/GetPaged?pageNo=' + $ictrl.currentPage + '&pageSize=' + $ictrl.rowAmount)
    //    .then(function (response) {
    //        $ictrl.someVouchers = response.data;
    //    });
    //};

    $ictrl.pageChanged = function () {
        console.log("Page changed function");
        console.log("Current Page: " + $ictrl.currentPage + " Row amount " + $ictrl.rowAmount);
        if ($ictrl.searchFlag) {
            $ictrl.searchAll();
            console.log("Paged Search Results")
        } else {
            $http.get('../api/Vouchers/GetPaged?pageNo=' + $ictrl.currentPage + '&pageSize=' + $ictrl.rowAmount)
            .then(function (response) {
                console.log("Getting new paged results.")
                $ictrl.someVouchers = response.data;
            });
        }
    };

    $ictrl.changeRows = function () {
        $ictrl.pageChanged();
    }

    $ictrl.restoreAll = function () {
        $ictrl.myPromise = $http
        .get('../api/Vouchers/GetPaged?pageNo=1&pageSize=' + $ictrl.rowAmount)
        .then(function (response) {
            $ictrl.searchFlag = false;
            $ictrl.someVouchers = response.data;
            $ictrl.noOfPages = $ictrl.someVouchers.Paging.PageCount;
            console.log("Page Count: " + $ictrl.noOfPages);
            $ictrl.totalItems = $ictrl.someVouchers.Paging.TotalRecordCount;
            $ictrl.currentPage = $ictrl.someVouchers.Paging.PageNo;
        })
        .catch(function (errorResponse) {
            $ictrl.context = "Something went wrong with getting issues";
        });
    }

    $ictrl.orderTableBy = function orderTableBy(columnName) {
        console.log("trying to filter")
        $ictrl.column = columnName;
        $ictrl.reverse = !$ictrl.reverse;
    };

});