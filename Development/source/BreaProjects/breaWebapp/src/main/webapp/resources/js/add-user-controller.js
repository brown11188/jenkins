app.controller('addUserController', function($scope, userService, $translatePartialLoader, $location) {
    $translatePartialLoader.addPart('userprofile');
    $translatePartialLoader.addPart('adduser');
    $translatePartialLoader.addPart('changepassword');
    $scope.usernameExists = false;
    userService.getRole().success(function(data, status, headers, config) {
        $scope.roles = data;
    }).error(function(data, status, headers, config) {
    });
    $scope.addUser = function() {
        $scope.user.enabled = true;
        $scope.usernameExists = false;
        userService.addUser($scope.user).success(function(data, status, headers, config) {
            if (data.code == 1) {
                userService.backToList();
                userService.setCreated(true);
            } else if (data.code == 2) {
                $scope.usernameExists = true;
            }
        }).error(function(data, status, headers, config) {
        });
    };
    $scope.cancel = function() {
        userService.backToList();
    };
});