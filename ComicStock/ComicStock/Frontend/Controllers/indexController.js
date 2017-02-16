/// <reference path="../Webpages/stock.html" />
/// <reference path="../Webpages/orders.html" />
//var app = angular.module("stockModule", ["xeditable", "ui.bootstrap", 'ui.select', 'ngSanitize', 'ngMaterial', 'ngMessages', 'cgBusy']);
var app = angular.module("SquareEyesModule", ["ui.router", "xeditable", "ui.bootstrap", 'ui.select', 'ngSanitize', 'ngMaterial', 'ngMessages', 'cgBusy']);
app.controller("indexController", function () {

})
app.config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider
            .when('', '/stock')
            .otherwise('/notfound');

    $stateProvider
     .state('orders',{
        url: '/orders',
        templateUrl: 'Frontend/Webpages/orders.html'
     })
    .state('stock',{
        url: '/stock',
        templateUrl: 'Frontend/Webpages/stock.html'
    })
    .state('issues', {
        url: '/issues',
        templateUrl: 'Frontend/Webpages/issues.html'
    })
    .state('Supplier', {
        url: '/Supplier',
        templateUrl: 'Frontend/Webpages/Supplier.html'
    })
    .state('Voucher', {
        url: '/Voucher',
        templateUrl: 'Frontend/Webpages/Voucher.html'
    })
    .state('creators', {
        url: '/creators',
        templateUrl: 'Frontend/Webpages/creators.html'
    })

});