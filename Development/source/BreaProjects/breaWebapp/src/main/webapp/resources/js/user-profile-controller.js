app.controller('userProfileController', function($scope, $routeParams, userService, $translatePartialLoader, $location,
        showNotification) {
    $translatePartialLoader.addPart('userprofile');
    $scope.showNotification = showNotification;
    userService.setUpdated(false);
    userService.getRole().success(function(data, status, headers, config) {
        $scope.roles = data;
    }).error(function(data, status, headers, config) {
    });
    if (showNotification) {
        userService.getUserById($routeParams.id).success(function(data, status, headers, config) {
            $scope.user = data;
        }).error(function(data, status, headers, config) {
        });
    } else {
        userService.getUserByUserName($routeParams.username).success(function(data, status, headers, config) {
            $scope.user = data;
        }).error(function(data, status, headers, config) {
        });
    }
    $scope.updateProfile = function() {
        $scope.userNotExists = false;
        userService.updateProfile($scope.user).success(function(data, status, headers, config) {
            if (data.code == 1) {
                if (showNotification) {
                    userService.setUpdated(true);
                }
                userService.backToList();
            } else if (data.code) {
                $scope.userNotExists = true;
            }
        }).error(function(data, status, headers, config) {
        });
    };

    $scope.cancel = function() {
        userService.backToList();
    };
});