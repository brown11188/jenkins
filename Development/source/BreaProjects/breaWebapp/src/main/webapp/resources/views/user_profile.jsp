<h2>{{'MY_PROFILE' | translate}}</h2>
<form name="profileForm" class="form-horizontal" role="form" novalidate>
    <div class="form-group alert alert-danger" ng-show="userNotExists">
        <p>{{'USER_NOT_EXISTS' | translate}}</p>
    </div>
    <div ng-include="'resources/views/user_info.jsp'"></div>
    <div class="form-group">
        <div class="col-sm-offset-3 col-sm-9">
            <div class="checkbox">
                <label><input type="checkbox" name="enable" ng-model="user.enabled">{{'ACTIVE' | translate}}</label>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-3 col-sm-9">
            <button type="button" class="btn btn-success" ng-disabled="profileForm.$invalid" ng-click="updateProfile()">{{'SAVE' | translate}}</button>
            <button type="button" class="btn btn-danger" ng-click="cancel()">{{'CANCEL' | translate}}</button>
        </div>
    </div>
</form>