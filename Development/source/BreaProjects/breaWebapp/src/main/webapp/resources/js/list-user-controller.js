app.controller('listUserController', function($scope, userService, $translatePartialLoader, userService, $filter,
        modalWindowFactory, $location) {
    $translatePartialLoader.addPart('listuser');
    $scope.isUpdated = userService.isUpdated();
    $scope.isCreated = userService.isCreated();
    $scope.isDeleted = userService.isDeleted();
    $scope.currentPage = 1;
    $scope.numPerPage = 10;
    $scope.maxSize = 5;
    $scope.itemsPerPage = [ 10, 20, 50, 100 ];
    $scope.modalTitle = $filter('translate')('LST_DELETE_TITLE');
    $scope.isError = false;
    $scope.users = [];
    // Get list user
    var getListUser = function() {
        userService.getListUser().success(function(data, status, headers, config) {
            $scope.users = data;
        }).error(function(data, status, headers, config) {
            $scope.isError = true;
        });
    };
    getListUser();
    $scope.$watch('currentPage + numPerPage + users.length', function() {
        var begin = (($scope.currentPage - 1) * $scope.numPerPage), end = begin + $scope.numPerPage;
        $scope.filteredListUser = $scope.users.slice(begin, end);
    });

    // Modal delete user.
    $scope.$watch(function() {
        return $filter('translate')('LST_DELETE_TITLE');
    }, function(newval) {
        $scope.modalTitle = newval;
    });
    $scope.showDeleteModal = function(user) {
        $scope.isDeleted = false;
        userService.setDeleted(false);
        var confirmCallback = function() {
            userService.deleteUser(user).success(function(data, status, headers, config) {
                if (data.code == 1) {
                    $scope.isDeleted = true;
                    userService.setDeleted(true);
                    getListUser();
                }
            }).error(function(data, status, headers, config) {
                // Do something here.
            });
        };
        modalWindowFactory.show($scope.modalTitle + user.username, confirmCallback);
    };
    $scope.clearNotification = function() {
        $scope.isUpdated = false;
        $scope.isCreated = false;
        $scope.isDeleted = false;
        userService.setUpdated(false);
        userService.setCreated(false);
        userService.setDeleted(false);
    };
    $scope.clearError = function() {
        $scope.isError = false;
    };
    $scope.sort = {
        column : '',
        descending : false
    };
    $scope.changeSorting = function(column) {

        var sort = $scope.sort;

        if (sort.column == column) {
            sort.descending = !sort.descending;
        } else {
            sort.column = column;
            sort.descending = false;
        }
    };
});