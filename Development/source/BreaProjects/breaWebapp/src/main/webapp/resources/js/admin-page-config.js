app.config(function($routeProvider, constant) {
    $routeProvider.when(constant.adminProfileUrl, {
        templateUrl : 'resources/views/user_profile.jsp',
        controller : 'userProfileController',
        resolve : {
            "showNotification" : function() {
                return false;
            }
        }
    }).when(constant.userProfileUrl, {
        templateUrl : 'resources/views/user_profile.jsp',
        controller : 'userProfileController',
        resolve : {
            "showNotification" : function() {
                return true;
            }
        }
    }).when(constant.adminChangePwdUrl, {
        templateUrl : 'resources/views/change_password.jsp',
        controller : 'passwordController',
    }).when(constant.listUserUrl, {
        templateUrl : 'resources/views/list-users.jsp',
        controller : 'listUserController',
    }).when(constant.addUserUrl, {
        templateUrl : 'resources/views/add_user.jsp',
        controller : 'addUserController'
    }).otherwise({
        redirectTo : constant.listUserUrl
    });

});
app.config(function($httpProvider) {
    $httpProvider.defaults.timeout = 5000;
});
app.run(function($http) {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $http.defaults.headers.common[header] = token;
});
