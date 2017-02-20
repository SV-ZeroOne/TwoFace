angular.module("SquareEyesModule")
.run(function (editableOptions) {
    editableOptions.theme = 'bs3';
})
.controller('CreatorsCtrl', function ($http) {

    var $ctrl = this;
    $ctrl.rowAmount = 10;
    $ctrl.showMe = false;
    $ctrl.currentPage = 1;
    $ctrl.jsonObject = {};
    $ctrl.column = '';
    $ctrl.reverse = false;


    $http
        .get('../api/Creators/GetPaged?pageNo=1&pageSize=' + $ctrl.rowAmount)
        .then(function (response) {
            $ctrl.options = response.data;
            $ctrl.noOfPages = $ctrl.options.Paging.PageCount;
            console.log("Page Count: " + $ctrl.noOfPages);
            $ctrl.totalItems = $ctrl.options.Paging.TotalRecordCount;
            $ctrl.currentPage = $ctrl.options.Paging.PageNo;
        })
        .catch(function (errorResponse) {
            $ctrl.context = "Something went wrong with getting creators";
        });

    $ctrl.addCreator = function () {
        console.log($ctrl.options.name);
        $ctrl.jsonObject = {
            "CountryOfResidence": $ctrl.options.countryOfResidence,
            "CreatorID": 1,
            "EmailAddress": $ctrl.options.email,
            "Name": $ctrl.options.name,
            "TaxReferenceNumber": $ctrl.options.taxReferenceNumber          
        };
        console.log($ctrl.jsonObject);
        $http.post('../api/Creators', $ctrl.jsonObject);
    };

    $ctrl.showNewCreator = function () {
        $ctrl.showMeNow = !$ctrl.showMeNow;
    }
    
    $ctrl.saveCreator = function (data, id) {
        data.CreatorID = id;      
        console.log(data);
        $http.put('../api/Creators', data);
        
    };

    $ctrl.removeCreator = function (data) {
        data.IsDeleted = true;
        console.log("after: " + data.IsDeleted);
        $http.put('../api/Creators', data);
    };

    $ctrl.paginationChange = function () {
        console.log("Pagination change event")
        console.log("Page no: " + $ctrl.paginationPage)
    }

    $ctrl.pageChanged = function () {
        console.log("Page changed function");
        console.log("Current Page: " + $ctrl.currentPage + " Row amount " + $ctrl.rowAmount);
        if ($ctrl.searchFlag) {
            $ctrl.searchAll();
            console.log("Paged Search Results")
        } else {
            $http.get('../api/Creators/GetPaged?pageNo=' + $ctrl.currentPage + '&pageSize=' + $ctrl.rowAmount)
            .then(function (response) {
                console.log("Getting new paged results.")
                $ctrl.options = response.data;
            });
        }
    };

    $ctrl.searchAll = function () {
        $ctrl.myPromise = $http.get('../api/Creators/GetSearchPaged?searchKey=' + $ctrl.creatorSearch + '&pageNumber=' + $ctrl.currentPage)
        .then(function (response) {
            $ctrl.options = response.data;
            $ctrl.noOfPages = $ctrl.options.Paging.PageCount;
            $ctrl.totalItems = $ctrl.options.Paging.TotalRecordCount;
            $ctrl.currentPage = $ctrl.options.Paging.PageNo;
            $ctrl.searchFlag = true;
        });
    };

    $ctrl.changeRows = function () {
        $ctrl.pageChanged();
    }

    $ctrl.restoreAll = function () {
        $ctrl.myPromise = $http
        .get('../api/Creators/GetPaged?pageNo=1&pageSize=' + $ctrl.rowAmount)
        .then(function (response) {
            $ctrl.searchFlag = false;
            $ctrl.options = response.data;
            $ctrl.noOfPages = $ctrl.options.Paging.PageCount;
            console.log("Page Count: " + $ctrl.noOfPages);
            $ctrl.totalItems = $ctrl.options.Paging.TotalRecordCount;
            $ctrl.currentPage = $ctrl.options.Paging.PageNo;
        })
        .catch(function (errorResponse) {
            $ctrl.context = "Something went wrong with getting issues";
        });
    }

    $ctrl.orderTableBy = function orderTableBy(columnName) {
        console.log("trying to filter")
        $ctrl.column = columnName;
        $ctrl.reverse = !$ctrl.reverse;
    };

});
