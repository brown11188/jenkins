app.controller("navBarController", function($scope, $translate) {
    $scope.changeLanguage = function(key, event) {
        $translate.use(key);
    };
});
