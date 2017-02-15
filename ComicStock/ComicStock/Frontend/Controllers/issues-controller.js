angular.module('SquareEyesModule')
.run(function (editableOptions) {
    editableOptions.theme = 'bs3';
})
.controller('IssuesCtrl', function ($http) {

    var $ctrl = this;
    
    $ctrl.rowAmount = 10;

    $ctrl.showMe = false;
        
    $ctrl.currentPage = 1;

    $ctrl.jsonObject = {};
    $ctrl.searchFlag = false;

    $http
        .get('../api/Issues/GetPaged?pageNo=1&pageSize=' + $ctrl.rowAmount)
        .then(function (response) {
            $ctrl.options = response.data;
            $ctrl.noOfPages = $ctrl.options.Paging.PageCount;
            console.log("Page Count: " + $ctrl.noOfPages);
            
            $ctrl.totalItems = $ctrl.options.Paging.TotalRecordCount;
            $ctrl.currentPage = $ctrl.options.Paging.PageNo;
        })
        .catch(function (errorResponse) {
            $ctrl.context = "Something went wrong with getting issues";
        });

    $ctrl.saveIssue = function (data, id) {
        console.log(data);

        console.log("Issue ID: " + id);
        data.IssueID = id;
        console.log(data);
        $http.put('../api/Issues', data);
    };

    $ctrl.changeRows = function () {
        $ctrl.pageChanged();
    }

    $ctrl.addIssue = function(){
        console.log($ctrl.options.title);
        $ctrl.jsonObject = {
            "Title": $ctrl.options.title,
            "PublicationDate": $ctrl.options.publicationDate,
            "Publisher": $ctrl.options.publisher,
            "SeriesNumber": $ctrl.options.series,
            "Description": null

        };

        $http.post('../api/Issues', $ctrl.jsonObject);
    };

    //$ctrl.removeIssue = function (index) {
    //    console.log(index);
    //    index.IsDeleted = true;
    //    console.log(index);
    //    $ctrl.jsonObjectForRemoval = {
    //        "Title": index.title,
    //        "PublicationDate": index.publicationDate,
    //        "Publisher": index.publisher,
    //        "SeriesNumber": index.series,
    //        "Description": index.Description,
    //        "IsDeleted" : "true"
    //    };
    //    console.log($ctrl.jsonObjectForRemoval);
    //    //$http.put('../api/Issues/' + index.IssueID);
    //    //$http.put('../api/Issues/' + index);

    //};

    // ~ working
    //$ctrl.removeIssue = function () {
    //    console.log($ctrl.options.title);
    //    console.log($ctrl.options.series);
    //    $ctrl.jsonObjectForRemoval = {
    //        "Title": $ctrl.options.title,
    //        "PublicationDate": $ctrl.options.publicationDate,
    //        "Publisher": $ctrl.options.publisher,
    //        "SeriesNumber": $ctrl.options.series,
    //        "Description": null,
    //        "IsDeleted" : "true"
    //    };
    //    console.log($ctrl.jsonObjectForRemoval);
    //    //$http.put('../api/Issues/' + index.IssueID);
    //    //$http.put('../api/Issues/' + index);

    //};

    //$ctrl.removeIssue = function () {
    //        console.log($ctrl.options.title);
    //        console.log($ctrl.options.series);
    //        $ctrl.jsonObjectForRemoval = {
    //            "Title": $ctrl.options.title,
    //            "PublicationDate": $ctrl.options.publicationDate,
    //            "Publisher": $ctrl.options.publisher,
    //            "SeriesNumber": $ctrl.options.series,
    //            "Description": null,
    //            "IsDeleted" : "true"
    //        };
    //        console.log($ctrl.jsonObjectForRemoval);
           
    //        //$http.put('../api/Issues/' + index.IssueID);
    //        $http.put('../api/Issues', $ctrl.jsonObjectForRemoval);

    //};

    $ctrl.removeIssue = function (data) {  
        data.IsDeleted = true;
        console.log("after: " + data.IsDeleted);
        $http.put('../api/Issues', data);
    };

    //$ctrl.saveIssue = function (data, id) {
    //    console.log(data);

    //    console.log("Issue ID: " + id);
    //    data.IssueID = id;
    //    console.log(data);
    //    $http.put('../api/Issues', data);
    //};

    $ctrl.paginationChange = function () {
        console.log("Pagination change event")
        console.log("Page no: " + $ctrl.paginationPage)
    }

    //$ctrl.pageChanged = function () {
    //    console.log("Page changed function");
    //    console.log("Current Page: " + $ctrl.currentPage + " Row amount " + $ctrl.rowAmount);
    //    $http.get('../api/Issues/GetPaged?pageNo=' + $ctrl.currentPage + '&pageSize=' + $ctrl.rowAmount)
    //    .then(function (response) {
    //        $ctrl.options = response.data;
    //    })
    //};

    $ctrl.pageChanged = function () {
        console.log("Page changed function");
        console.log("Current Page: " + $ctrl.currentPage + " Row amount " + $ctrl.rowAmount);
        if ($ctrl.searchFlag) {
            $ctrl.searchAll();
            console.log("Paged Search Results")
        } else {
            $http.get('../api/Issues/GetPaged?pageNo=' + $ctrl.currentPage + '&pageSize=' + $ctrl.rowAmount)
            .then(function (response) {
                console.log("Getting new paged results.")
                $ctrl.options = response.data;
            });
        }
    };

    //$ctrl.searchAll = function () {
    //    $http.get('../api/Issues?search=' + $ctrl.issueSearch)
    //    .then(function (response) {
    //        $ctrl.options.Data = response.data;
    //    });
    //}

    $ctrl.searchAll = function () {
        $http.get('../api/Issues/GetSearchPaged?searchKey=' + $ctrl.issueSearch + '&pageNumber=' + $ctrl.currentPage)
        .then(function (response) {
            $ctrl.options = response.data;
            $ctrl.noOfPages = $ctrl.options.Paging.PageCount;
            $ctrl.totalItems = $ctrl.options.Paging.TotalRecordCount;
            $ctrl.currentPage = $ctrl.options.Paging.PageNo;
            $ctrl.searchFlag = true;
        });
    };

    $ctrl.restoreAll = function () {
        $ctrl.myPromise = $http
        .get('../api/Issues/GetPaged?pageNo=1&pageSize=' + $ctrl.rowAmount)
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

});
