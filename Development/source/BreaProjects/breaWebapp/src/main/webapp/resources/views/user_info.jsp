<div class="form-group" ng-class="{'has-error': profileForm.firstName.$dirty && profileForm.firstName.$invalid}">
        <label class="control-label col-sm-3" for="firstName">{{'FIRST_NAME' | translate}}</label>
        <div class="col-sm-9">
            <input type="text" class="form-control" name="firstName" placeholder="{{'ENTER_FIRST_NAME' | translate}}" ng-model="user.usersInfo.userFirstName" required>
            <span class="help-block"
                                ng-show="profileForm.firstName.$dirty && profileForm.firstName.$invalid">
                                {{'FIRST_NAME_ERROR_REQUIRED' | translate}}</span>
        </div>
    </div>
    <div class="form-group" ng-class="{'has-error': profileForm.lastName.$dirty && profileForm.lastName.$invalid}">
        <label class="control-label col-sm-3" for="lastName">{{'LAST_NAME' | translate}}</label>
        <div class="col-sm-9">
            <input type="text" class="form-control" name="lastName" placeholder="{{'ENTER_LAST_NAME' | translate}}" ng-model="user.usersInfo.userLastName" required>
            <span class="help-block"
                                ng-show="profileForm.lastName.$dirty && profileForm.lastName.$invalid">
                                {{'LAST_NAME_ERROR_REQUIRED' | translate}}</span>
        </div>
    </div>

    <div class="form-group" ng-class="{'has-error': profileForm.email.$dirty && profileForm.email.$invalid}">
        <label class="control-label col-sm-3" for="email">{{'EMAIL' | translate}}</label>
        <div class="col-sm-9">
            <input type="email" class="form-control" name="email" placeholder="{{'ENTER_EMAIL' | translate}}" ng-model="user.usersInfo.userEmail" required>
            <span ng-show="profileForm.email.$dirty && profileForm.email.$invalid">
                <span class="help-block" ng-show="profileForm.email.$error.required">{{'EMAIL_ERROR_REQUIRED' | translate}}</span>
                <span class="help-block" ng-show="profileForm.email.$error.email">{{'EMAIL_ERROR_INVALID' | translate}}</span>
            </span>
            
        </div>
    </div>
    <div class="form-group" ng-class="{'has-error': profileForm.phone.$dirty && profileForm.phone.$invalid}">
        <label class="control-label col-sm-3" for="name">{{'PHONE' | translate}}</label>
        <div class="col-sm-9">
            <input type="text" class="form-control" name="phone" placeholder="{{'ENTER_PHONE_NUMBER' | translate}}" ng-model="user.usersInfo.userPhone" required ng-pattern="/^[(]{0,1}[0-9]{3}[)\.\- ]{0,1}[0-9]{3}[\.\- ]{0,1}[0-9]{4}$/">
            <div ng-show="profileForm.phone.$dirty && profileForm.phone.$invalid">
                <span class="help-block"
                                ng-show="profileForm.phone.$error.required">
                                {{'PHONE_ERROR_REQUIRED' | translate}}</span>
                <span class="help-block"
                                ng-show="profileForm.phone.$error.pattern">
                                {{'PHONE_ERROR_INVALID' | translate}}</span>
            </div>
        </div>
    </div>
    <div class="form-group" ng-class="{'has-error': profileForm.address.$dirty && profileForm.address.$invalid}">
        <label class="control-label col-sm-3" for="address">{{'ADDRESS' | translate}}</label>
        <div class="col-sm-9">
            <input type="text" class="form-control" name="address" placeholder="{{'ENTER_ADDRESS' | translate}}" ng-model="user.usersInfo.userAddress" required>
            <span class="help-block"
                                ng-show="profileForm.address.$dirty && profileForm.address.$invalid">
                                {{'ADDRESS_ERROR_REQUIRED' | translate}}</span>
        </div>
    </div>
    <div class="form-group" ng-class="{'has-error': profileForm.role.$dirty && profileForm.role.$invalid}">
        <label class="control-label col-sm-3" for="role">{{'ROLE' | translate}}</label>
        <div class="col-sm-9">
            <select class="form-control" name="role" required ng-model="user.roles.id" 
                ng-options="role.id as role.description for role in roles" >
                <option value="">{{'SELECT_ROLE' | translate}}</option>
            </select>
            <span class="help-block"
                                ng-show="profileForm.role.$dirty && profileForm.role.$invalid">
                                {{'ROLE_ERROR_REQUIRED' | translate}}</span>
        </div>
    </div>