app.controller('passwordController', function($scope, userService, $translatePartialLoader, $location) {
    $translatePartialLoader.addPart('changepassword');
    $scope.passwordIncorrect = false;
    $scope.changePassword = function() {
        userService.changePassword($scope.user).success(function(data, status, headers, config) {
            if (data.code == 1) {
                userService.backToList();
            } else if (data.code == 2) {
                $scope.passwordIncorrect = true;
            }
        }).error(function(data, status, headers, config) {
        });
    };
    $scope.cancel = function() {
        userService.backToList();
    };
});