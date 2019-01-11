angular.module('cart', [])
    .controller('CartControler', function($scope) {
        $scope.books = [
          {title: 'Absolute Java', qty: 1, price: 114.95},
          {title: 'Pro HTML5', qty: 1, price: 27.95},
          {title: 'Head First HTML5', qty: 1, price: 27.89}
        ];

        $scope.removeBook = function(index) {
          $scope.books.splice(index, 1);
        }

        $scope.newBook = function() {
            newBook = {title: 'New Book', qty: 1, price:10.99}
            $scope.books.push(newBook)
        }

        $scope.save = function() {
            localStorage.setItem("books", JSON.stringify($scope.books));
        }
    })
