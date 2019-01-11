angular.module("Filter", [])
    .controller("FilterController", function($scope) {
        $scope.input = "Hello World";
        $scope.delim = ",";
    })
    .filter("tokenize", function() {
        return function(value, delim) {
            if (angular.isString(value)) {
                if (delim) {
                    return value.split("").join(delim);
                }
                return value.split("").join(",");
            } else {
                return value;
            }
        }
    });
