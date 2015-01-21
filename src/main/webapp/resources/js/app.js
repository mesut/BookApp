'use strict';

var BookApp = {};

var App = angular.module('BookApp', ['BookApp.filters', 'BookApp.services', 'BookApp.directives']);

App.controller('BookController', ['$scope', '$http', '$rootScope', function ($scope, $http, $rootScope) {
    $scope.book = {};
    $scope.editMode = false
    $scope.addOrUpdateModal = false;
    $scope.generalModal = false;

    $scope.openAddOrUpdateModal = function () {
        $scope.addOrUpdateModal = true;
        $scope.editMode = false
        $scope.book = {};
        $scope.resetError();
    };
    $scope.validateBook = function (book) {
        var message = '';
        if (isEmpty($scope.book.name)) {
            message = "Kitap ismi girilmelidir.\n";
        }
        if (isEmpty($scope.book.author)) {
            message += "Yazar ismi girilmelidir.\n";
        }
        if (!$scope.editMode) {
            if (!$rootScope.captchResult) {
                message += "Captcha yalnis girilmistir..\n";
            }
        }
        return message;
    };

    $scope.fetchBookList = function () {
        $http.get('books/booklist.json').success(function (bookList) {
            $scope.bookList = bookList;
        });
    };

    $scope.createBook = function (book) {
        if (!isEmpty($scope.validateBook(book))) {
            $scope.setError($scope.validateBook(book));
            return;
        }
        $http.post('books/createBook', book).success(function () {
            $scope.fetchBookList();
            $scope.book.name = '';
            $scope.book.author = '';
            $scope.addOrUpdateModal = false;
        }).error(function () {
            $scope.setError('Could not add a new book');
        });
    };

    $scope.updateBook = function (book) {
        $scope.resetError();

        $http.put('books/updateBook', book).success(function () {
            $scope.fetchBookList();
            $scope.book.name = '';
            $scope.book.author = '';
            $scope.editMode = false;
            $scope.addOrUpdateModal = false;
        }).error(function () {
            $scope.setError('Could not update the book');
        });
    };

    $scope.editBook = function (book) {
        $scope.resetError();
        $scope.book = book;
        $scope.editMode = true;
        $scope.addOrUpdateModal = true;

    };

    $scope.deleteBook = function (id) {
        $scope.resetError();

        $http.delete('books/deleteBook/' + id).success(function () {
            $scope.fetchBookList();
        }).error(function () {
            $scope.setError('Could not remove book');
        });
        $scope.book.name = '';
        $scope.book.auhor = '';
    };

    $scope.deleteAllBook = function () {
        $scope.resetError();

        $http.delete('books/deleteAllBook').success(function () {
            $scope.fetchBookList();
        }).error(function () {
            $scope.setError('Could not remove all book ');
        });

    };

    $scope.resetBookForm = function () {
        $scope.resetError();
        $scope.book = {};
        $scope.editMode = false;
        $scope.addOrUpdateModal = false;
        $scope.generalModal = false;

    };

    $scope.resetError = function () {
        $scope.error = false;
        $scope.errorMessage = '';
        $scope.generalModal = false;
    };

    $scope.setError = function (message) {
        $scope.error = true;
        $scope.errorMessage = message;
        $scope.generalModal = true;
    };

    $scope.fetchBookList();

    $scope.predicate = 'id';

    var isEmpty = function (val) {
        return (val == 'undefined' || val == null || val.length <= 0) ? true : false;
    }
}]);