'use strict';

var BookApp = {};

var App = angular.module('BookApp', ['BookApp.filters', 'BookApp.services', 'BookApp.directives']);

// Declare app level module which depends on filters, and services
App.config(['$routeProvider', function ($routeProvider) {

    $routeProvider.when('/books', {
        templateUrl: 'books/layout',
        controller: BookController
    });

    $routeProvider.otherwise({redirectTo: '/books'});
}]);
