'use strict';

/**
 * BookController
 * @constructor
 */
var BookController = function ($scope, $http) {
    $scope.book = {};
    $scope.editMode = false;

    $scope.fetchBookList = function () {
        $http.get('books/booklist.json').success(function (bookList) {
            $scope.bookList = bookList;
        });
    };

    $scope.createBook = function (book) {
        $scope.resetError();

        $http.post('books/createBook', book).success(function () {
            $scope.fetchBookList();
            $scope.book.name = '';
            $scope.book.author = '';
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
        }).error(function () {
            $scope.setError('Could not update the book');
        });
    };

    $scope.editBook = function (book) {
        $scope.resetError();
        $scope.book = book;
        $scope.editMode = true;
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
        alert('sdfsdf');
        $scope.resetError();
        $scope.book = {};
        $scope.editMode = false;
    };

    $scope.resetError = function () {
        $scope.error = false;
        $scope.errorMessage = '';
    };

    $scope.setError = function (message) {
        $scope.error = true;
        $scope.errorMessage = message;
    };

    $scope.fetchBookList();

    $scope.predicate = 'id';

};