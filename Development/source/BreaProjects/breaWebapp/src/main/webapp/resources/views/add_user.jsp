<h2>{{'ADD_USER_TITLE' | translate}}</h2>
<form name="profileForm" class="form-horizontal" role="form" novalidate>
    <div class="form-group alert alert-danger" ng-show="usernameExists">
        <p> {{'USER_NAME_NAME_ERROR_EXISTS' | translate}}</p>
    </div>
    <div class="form-group" ng-class="{'has-error': profileForm.username.$dirty && profileForm.username.$invalid}">
        <label class="control-label col-sm-3" for="username">{{'USER_NAME' | translate}}</label>
        <div class="col-sm-9">
            <input type="text" class="form-control" name="username" placeholder="{{'ENTER_USER_NAME' | translate}}" ng-model="user.username" required>
            <span class="help-block"
                                ng-show="profileForm.username.$dirty && profileForm.username.$invalid">
                                {{'USER_NAME_NAME_ERROR_REQUIRED' | translate}}</span>
        </div>
    </div>
    <div ng-include="'resources/views/user_info.jsp'"></div>
     <div class="form-group" ng-class="{'has-error': profileForm.newPassword.$dirty && profileForm.password.$invalid}">
        <label class="control-label col-sm-3" for="newPassword">{{'NEW_PASSWORD' | translate}}</label>
        <div class="col-sm-9">
            <input type="password" class="form-control" name="password" placeholder="{{'ENTER_NEW_PASSWORD' | translate}}" ng-model="user.password" required>
            <span class="help-block"
                                ng-show="profileForm.newPassword.$dirty && profileForm.password.$invalid">
                                {{'NEW_PASSWORD_REQUIRED' | translate}}</span>
        </div>
    </div>

    <div class="form-group" ng-class="{'has-error': profileForm.verifyNewPassword.$dirty && profileForm.verifyNewPassword.$invalid}">
        <label class="control-label col-sm-3" for="verifyNewPassword">{{'VERIFY_NEW_PASSWORD' | translate}}</label>
        <div class="col-sm-9">
            <input type="password" class="form-control" name="verifyNewPassword" placeholder="{{'ENTER_NEW_PASSWORD' | translate}}" ng-model="verifyNewPassword" required password-verify="user.password">
                <span class="help-block" ng-show="profileForm.verifyNewPassword.$dirty && profileForm.verifyNewPassword.$error.required">
                    {{'VERIFY_NEW_PASSWORD_REQUIRED' | translate}}
                </span>
                <span class="help-block" ng-show="profileForm.verifyNewPassword.$dirty && profileForm.verifyNewPassword.$error.noMatch">
                    {{'VERIFY_NEW_PASSWORD_NOT_MATCH' | translate}}
                </span>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-3 col-sm-9">
            <button type="button" class="btn btn-success" ng-disabled="profileForm.$invalid" ng-click="addUser()">{{'CREATE' | translate}}</button>
            <button type="button" class="btn btn-danger" ng-click="cancel()">{{'CANCEL' | translate}}</button>
        </div>
    </div>
</form>