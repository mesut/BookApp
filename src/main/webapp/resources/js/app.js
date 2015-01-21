'use strict';

var BookApp = {};

var App = angular.module('BookApp', ['BookApp.filters', 'BookApp.services', 'BookApp.directives']);

App.controller('BookController', ['$scope', '$http', '$rootScope', function ($scope, $http, $rootScope) {
    $scope.book = {};
    $scope.editMode = false
    $scope.addOrUpdateModal = false;
    $scope.generalModal = false;


    $scope.fetchBookList = function () {
        $http.get('books/booklist.json').success(function (bookList) {
            $scope.bookList = bookList;
        });
    };

    $scope.createBook = function (book) {
        if (!isValidBook(book)) {
            return;
        }
        $http.post('books/createBook', book).success(function () {
            $scope.fetchBookList();
            $scope.book.name = '';
            $scope.book.author = '';
            $scope.addOrUpdateModal = false;
            toastr.info("Book created");
        }).error(function () {
            toastr.error("Could not add a new book");
        });
    };

    $scope.updateBook = function (book) {
        if (!isValidBook(book)) {
            return;
        }
        $http.put('books/updateBook', book).success(function () {
            $scope.fetchBookList();
            $scope.book.name = '';
            $scope.book.author = '';
            $scope.editMode = false;
            $scope.addOrUpdateModal = false;
            toastr.info("Book updated");
        }).error(function () {
            toastr.error('Could not update the book');
        });
    };

    $scope.editBook = function (book) {
        $scope.book = book;
        $scope.editMode = true;
        $scope.addOrUpdateModal = true;

    };

    $scope.deleteBook = function (id) {

        $http.delete('books/deleteBook/' + id).success(function () {
            toastr.info('Book deleted');
            $scope.fetchBookList();
        }).error(function () {
            toastr.error('Could not remove book');
        });
        $scope.book.name = '';
        $scope.book.auhor = '';
    };

    $scope.deleteAllBook = function () {
        $http.delete('books/deleteAllBook').success(function () {
            toastr.info("Deleted all books");
            $scope.fetchBookList();
        }).error(function () {
            toastr.error('Could not remove all book ');
        });

    };

    $scope.resetBookForm = function () {
        $scope.book = {};
        $scope.editMode = false;
        $scope.addOrUpdateModal = false;
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

    $scope.openAddOrUpdateModal = function () {
        $scope.addOrUpdateModal = true;
        $scope.editMode = false
        $scope.book = {};
    };
    var isValidBook = function (book) {
        if (isEmpty($scope.book.name)) {
            toastr.error('Please enter book name');
            return false;

        }
        if (isEmpty($scope.book.author)) {
            toastr.error('Please enter author name');
            return false;
        }
        if (!$scope.editMode) {
            if (!$rootScope.captchResult) {
                toastr.error('Please verify captcha');
                return false;
            }
        }
        ;
        return true;
    };
}]);