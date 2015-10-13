<h2>{{'TITLE_CHANGE_PASSWORD' | translate}}</h2>
<form name="passwordForm" class="form-horizontal" role="form" novalidate>
    <div class="form-group alert alert-danger" ng-if="passwordIncorrect">
        <p>{{'PASSWORD_INCORRECT' | translate}}</p>
    </div>
    <div class="form-group" ng-class="{'has-error': passwordForm.password.$dirty && passwordForm.password.$invalid}">
        <label class="control-label col-sm-3" for="firstName">{{'CURRENT_PASSWORD' | translate}}</label>
        <div class="col-sm-9">
            <input type="password" class="form-control" name="password" placeholder="{{'ENTER_CURRENT_PASSWORD' | translate}}" ng-model="user.currentPass" required>
            <span class="help-block"
                                ng-show="passwordForm.password.$dirty && passwordForm.password.$invalid">
                                {{'CURRENT_PASSWORD_REQUIRED' | translate}}</span>
        </div>
    </div>
    <div class="form-group" ng-class="{'has-error': passwordForm.newPassword.$dirty && passwordForm.newPassword.$invalid}">
        <label class="control-label col-sm-3" for="newPassword">{{'NEW_PASSWORD' | translate}}</label>
        <div class="col-sm-9">
            <input type="password" class="form-control" name="newPassword" placeholder="{{'ENTER_NEW_PASSWORD' | translate}}" ng-model="user.newPass" required>
            <span class="help-block"
                                ng-show="passwordForm.newPassword.$dirty && passwordForm.newPassword.$invalid">
                                {{'NEW_PASSWORD_REQUIRED' | translate}}</span>
        </div>
    </div>

    <div class="form-group" ng-class="{'has-error': passwordForm.verifyNewPassword.$dirty && passwordForm.verifyNewPassword.$invalid}">
        <label class="control-label col-sm-3" for="verifyNewPassword">{{'VERIFY_NEW_PASSWORD' | translate}}</label>
        <div class="col-sm-9">
            <input type="password" class="form-control" name="verifyNewPassword" placeholder="{{'ENTER_NEW_PASSWORD' | translate}}" ng-model="verifyNewPassword" required password-verify="user.newPass">
                <span class="help-block" ng-show="passwordForm.verifyNewPassword.$dirty && passwordForm.verifyNewPassword.$error.required">
                    {{'VERIFY_NEW_PASSWORD_REQUIRED' | translate}}
                </span>
                <span class="help-block" ng-show="passwordForm.verifyNewPassword.$dirty && passwordForm.verifyNewPassword.$error.noMatch">
                    {{'VERIFY_NEW_PASSWORD_NOT_MATCH' | translate}}
                </span>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-3 col-sm-9">
            <button type="button" class="btn btn-success" ng-disabled="passwordForm.$invalid" ng-click="changePassword()" >{{'SAVE' | translate}}</button>
            <button class="btn btn-danger" ng-click="cancel()">{{'CANCEL' | translate}}</button>
        </div>
    </div>
</form>