app.factory('userService', function($http, $location, constant) {
    var isUpdated = false;
    var isCreated = false;
    var isDeleted = false;
    var _getUserById = function(id) {
        return $http.get(constant.serviceGetUserByIdUrl + id);
    };
    var _getUserByUserName = function(username) {
        return $http.get(constant.serviceGetUserByUserNameUrl + username);
    };
    var _updateProfile = function(user) {
        return $http.post(constant.serviceUpdateProfileUrl, user);
    };
    var _changePassword = function(user) {
        return $http.post(constant.serviceChangePasswordUrl, user);
    };
    var _getRole = function() {
        return $http.get(constant.serviceGetRoleUrl);
    };
    var _deleteUser = function(user) {
        return $http.get(constant.serviceDeleteUserUrl + user.id);
    };
    var _getListUser = function() {
        return $http.get(constant.serviceGetAllUserUrl);
    };
    var _isUpdated = function() {
        return isUpdated;
    };
    var _setUpdated = function(value) {
        isUpdated = value;
    };
    var _addUser = function(user) {
        return $http.post(constant.serviceAddUserUrl, user);
    };
    var _backToList = function() {
        $location.path(constant.listUserUrl);
    };
    var _isCreated = function() {
        return isCreated;
    };
    var _setCreated = function(value) {
        isCreated = value;
    };
    var _isDeleted = function() {
        return isDeleted;
    };
    var _setDeleted = function(value) {
        isDeleted = value;
    };
    return {
        getUserById : _getUserById,
        updateProfile : _updateProfile,
        changePassword : _changePassword,
        getRole : _getRole,
        deleteUser : _deleteUser,
        isUpdated : _isUpdated,
        setUpdated : _setUpdated,
        getListUser : _getListUser,
        addUser : _addUser,
        backToList : _backToList,
        isCreated : _isCreated,
        setCreated : _setCreated,
        isDeleted : _isDeleted,
        setDeleted : _setDeleted,
        getUserByUserName : _getUserByUserName
    };
});