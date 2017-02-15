angular.module("SquareEyesModule")
.run(function (editableOptions) {
    editableOptions.theme = 'bs3';
})
.filter('valididity', function() {
    return function(input) {
        return input ? 'Valid' : 'Invalid';
    }
})
.controller("voucherController", function ($http, $scope) {
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
          .post('../api/Vouchers/PlaceVoucher?amount=' + $ictrl.newVoucher.amount + '&valid=' + $ictrl.newVoucher.valid);
    }

    // remove voucher
    $ictrl.removeVoucher = function (index, VoucherID) {
        $ictrl.someVouchers.Data.splice(index, 1);
        $http.delete('../api/Vouchers/' + VoucherID);
    };

    //update voucher
    $ictrl.saveVoucher = function (data, id) {
        console.log(data);
        console.log("Voucher ID: " + id);
        data.VoucherID = id;
        console.log(data);
        $http.put('../api/Vouchers', data);
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