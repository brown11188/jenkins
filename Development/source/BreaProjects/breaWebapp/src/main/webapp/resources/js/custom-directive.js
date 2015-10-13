app.directive("passwordVerify", function() {
    return {
        require : 'ngModel',
        scope : {

            reference : '=passwordVerify'

        },
        link : function(scope, elm, attrs, ctrl) {
            ctrl.$parsers.unshift(function(viewValue, $scope) {
                var noMatch = viewValue === scope.reference;
                ctrl.$setValidity('noMatch', noMatch);
                return noMatch ? viewValue : undefined;
            });

            scope.$watch("reference", function(value) {
                ctrl.$setValidity('noMatch', value === ctrl.$viewValue);
            });
        }
    };
});
app.filter('active', function($filter) {
    return function(input) {
        if (input == true) {
            return "Active";
        } else {
            return "Deactive";
        }
    };
});
app.factory('modalWindowFactory', function($modal) {
    var modalController = _modalController;
    function _modalController($scope, $modalInstance, title) {
        $scope.title = "";
        if (title) {
            $scope.title = title;
        }
        $scope.comfirm = function() {
            $modalInstance.close();
        };
        $scope.cancel = function() {
            $modalInstance.dismiss();
        };
    }
    var _show = function(title, confirmCallback) {
        // Show window
        var modalInstance = $modal.open({
            templateUrl : 'resources/views/modal_delete_user.jsp',
            controller : modalController,
            size : 'sm',
            resolve : {
                title : function() {
                    return title;
                }
            }
        });
        // Register confirm and cancel callbacks
        modalInstance.result.then(function() {
            if (confirmCallback != undefined) {
                confirmCallback();
            }
        });
    };
    return {
        show : _show
    };
});