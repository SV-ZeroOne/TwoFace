var app = angular.module("voucherModule", ["xeditable", "ui.bootstrap", "ngMessages", "ngMaterial"]);

app.run(function (editableOptions) {
    editableOptions.theme = 'bs3';
})

app.filter('valididity', function() {
    return function(input) {
        return input ? 'Valid' : 'Invalid';
    }
})

app.controller("voucherController", function ($http, $scope) {
    var $ictrl = this;
    $ictrl.showMe = false;
    $ictrl.newVoucher = {};
    $ictrl.currentPage = 1;
    $ictrl.rowAmount = 10;
    $ictrl.someVouchers;
    $http
        .get('http://localhost:62655/api/Vouchers/GetPaged?pageNo=1&pageSize='+$ictrl.rowAmount)
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
          .post('http://localhost:62655/api/Vouchers/PlaceVoucher?amount=' + $ictrl.newVoucher.amount + '&valid=' + $ictrl.newVoucher.valid);
    }

    // remove voucher
    $ictrl.removeVoucher = function (index, VoucherID) {
        $ictrl.someVouchers.Data.splice(index, 1);
        $http.delete('http://localhost:62655/api/Vouchers/' + VoucherID);
    };

    //update voucher
    $ictrl.saveVoucher = function (data, id) {
        console.log(data);
        console.log("Voucher ID: " + id);
        data.VoucherID = id;
        console.log(data);
        $http.put('http://localhost:62655/api/Vouchers', data);
    };

    $ictrl.searchAll = function () {
        $http.get('http://localhost:62655/api/Vouchers?search=' + $ictrl.vouchersSearch)
        .then(function (response) {
            $ictrl.someVouchers.Data = response.data;
        });
    }

    $scope.Valid = [
    { value: 1, text: 'Valid' },
    { value: 2, text: 'Invalid' },
    ];

    $ictrl.paginationChange = function () {
        console.log("Pagination change event")
        console.log("Page no: " + $ictrl.paginationPage)
    }

    $ictrl.pageChanged = function () {
        console.log("Page changed function");
        console.log("Current Page: " + $ictrl.currentPage + " Row amount " + $ictrl.rowAmount);
        $http.get('http://localhost:62655/api/Vouchers/GetPaged?pageNo=' + $ictrl.currentPage + '&pageSize=' + $ictrl.rowAmount)
        .then(function (response) {
            $ictrl.someVouchers = response.data;
        });
    };
});