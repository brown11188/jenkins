app.controller('adminController', function($scope, $translatePartialLoader, $translate, $http, $window, $compile) {
    $translatePartialLoader.addPart('admin');
    $scope.changeLanguage = function(key, event) {
        event.preventDefault();
        $translate.use(key);
        var url;
        if (key == 'en') {
            url = '?lang=en_US';
        } else {
            url = '?lang=fr_FR';
        }
        $http.get(url).success(function(data, status, headers, config) {
            reloadMenu(data);
        });
    };
    $scope.changeTheme = function(url, event) {
        event.preventDefault();
        $http.get(url).success(function(data, status, headers, config) {
            $window.location.reload();
        });
    };
    var reloadMenu = function(data) {
        var contents = angular.element("<div>").html(data).find("#menu");
        $("#menu").html($compile(contents)($scope));
    };
});
