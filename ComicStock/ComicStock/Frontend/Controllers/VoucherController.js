angular.module("SquareEyesModule")
.run(function (editableOptions) {
    editableOptions.theme = 'bs3';
})
.filter('valididity', function() {
    return function(input) {
        return input ? 'Invalid' : 'Valid';
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
    $ictrl.newVoucher.valid = true;
    $ictrl.newVoucher.amount = 10;

    $ictrl.myPromise = $http
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

    $http
        .get('../api/Vouchers/GetStats')
        .then(function (response) {
            $ictrl.voucherStats = response.data;
        })

    $ictrl.getStats = function () {
        $http
        .get('../api/Vouchers/GetStats')
        .then(function (response) {
            $ictrl.voucherStats = response.data;
        })
    }

    $ictrl.showNewVoucher = function () {
        $ictrl.showMe = !$ictrl.showMe;
    }

    $ictrl.placeVoucher = function () {
        $ictrl.myPromise = $http
          .post('../api/Vouchers/PlaceVoucher?amount=' + $ictrl.newVoucher.amount + '&qty=' + $ictrl.newVoucher.qty + '&valid=' + $ictrl.newVoucher.valid);
        $ictrl.showAdding();
        setTimeout(function () { $ictrl.restoreAll(); }, 2000);
        setTimeout(function () { $ictrl.getStats(); }, 2000);
    }

    // remove voucher
    $ictrl.removeVoucher = function (index, VoucherID) {
        $ictrl.someVouchers.Data.splice(index, 1);
        $http.delete('../api/Vouchers/' + VoucherID);
        setTimeout(function () { $ictrl.getStats(); }, 2000);
        $ictrl.showAlert();
    };

    $ictrl.checkAmount = function (data, form) {
        if (data <= 0 || (data % 10) != 0) {
            var msg = "You have entered an invalid amount";
            form.$setError('name', msg);
            return msg;
        }
        else {
            form.$setError('name', '');
        }
    };

    //update voucher
    $ictrl.saveVoucher = function (data, id, code, date) {
        console.log(data);
        console.log("Voucher ID: " + id);
        data.VoucherID = id;
        data.Code = code;
        data.DateIssued = date;
        console.log(data.Valid);
        console.log(data);
        $http.put('../api/Vouchers/', data).then($ictrl.showUpdate);
        setTimeout(function () { $ictrl.restoreAll(); }, 2000);
        setTimeout(function () { $ictrl.getStats(); }, 2000);
    };

    $ictrl.showAlert = function () {
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

    $ictrl.searchAll = function () {
        $ictrl.myPromise = $http.get('../api/Vouchers/GetSearchPaged?searchKey=' + $ictrl.vouchersSearch + '&pageNumber=' + $ictrl.currentPage)
        .then(function (response) {
            $ictrl.someVouchers = response.data;
            $ictrl.noOfPages = $ictrl.someVouchers.Paging.PageCount;
            $ictrl.totalItems = $ictrl.someVouchers.Paging.TotalRecordCount;
            $ictrl.currentPage = $ictrl.someVouchers.Paging.PageNo;
            $ictrl.searchFlag = true;
            if (response.data.Data.length == 0) {
                console.log("No search results")
                console.log("Data lenght" + response.data.Data.length)
                $ictrl.showNoSearchResults();
            }
        });
    };

    $scope.Valid = [
    { value: "true", text: 'Valid' },
    { value: "false", text: 'Invalid' },
    ];

    $ictrl.CheckIfValid = function (valid) {
        if (valid == true) {
            return "Valid";
        }
        else if (valid == false) {
            return "Invalid";
        }
    }

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
            $ictrl.myPromise = $http.get('../api/Vouchers/GetPaged?pageNo=' + $ictrl.currentPage + '&pageSize=' + $ictrl.rowAmount)
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

    
    $ictrl.showNoSearchResults = function (ev) {
        $mdDialog.show(
          $mdDialog.alert()
            .parent(angular.element(document.querySelector('#popupContainer')))
            .clickOutsideToClose(true)
            .title('No results found.')
            .textContent('No results found for you search criteria.')
            .ariaLabel('No Search Results Dialog')
            .ok('Ok')
            .targetEvent(ev)
        );
    };
});