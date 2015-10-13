<h2>{{'LST_LIST_USER' | translate}}</h2>
<div class="alert alert-success" ng-if="isUpdated || isDeleted || isResetPassword || isCreated">
    <a class="close" data-dismiss="alert" aria-label="close" ng-click="clearNotification()">&times;</a>
    <p ng-if="isUpdated">{{'LST_UPDATE_SUCCESS' | translate}}</p>
    <p ng-if="isDeleted">{{'LST_DELETE_SUCCESS' | translate}}</p>
    <p ng-if="isResetPassword">{{'LST_RESET_PASSWORD_SUCCESS' | translate}}</p>
    <p ng-if="isCreated">{{'LST_CREATE_SUCCESS' | translate}}</p>
</div>
<div class="alert alert-danger" ng-if="isError">
    <a class="close" data-dismiss="alert" aria-label="close" ng-click="clearError()">&times;</a>
    <p>{{'LST_ERROR_GET_LIST_USER' | translate}}</p>
</div>
<div class="row search-box">
    <div class="col-md-6 pull-left">
        <form class="form-inline">
            <div class="form-group">
                <label class="control-label">{{'LST_SHOW' | translate}}</label>
                <select class="form-control" ng-model="numPerPage" ng-options="item for item in itemsPerPage">
                </select>
                <label class="control-label">{{'LST_ENTRIES' | translate}}</label>
            </div>
        </form>
    </div>
    <div class="col-md-6">
        <div class="pull-right">
            <form class="form-inline ">
                <div class="form-group">
                    <label>{{'LST_SEARCH' | translate}}</label> <input type="text" ng-model="search" class="form-control" placeholder="{{'LST_TYPE_TO_SEARCH' | translate}}">
                </div>
            </form>
        </div>
    </div>
</div>
<div class="table-responsive">
    <table class="table table-striped table-bordered table-hover table-condensed">
        <thead>
            <tr class="table-header">
                <th></th>
                <th ng-click="changeSorting('username')">{{'LST_USER_NAME' | translate}}
                    <span ng-show="sort.column == 'username'">
                        <span ng-show="!sort.descending" class="glyphicon glyphicon-sort-by-attributes"></span>
                        <span ng-show="sort.descending" class="glyphicon glyphicon-sort-by-attributes-alt"></span>
                    </span>
                </th>
                <th ng-click="changeSorting('usersInfo.userFirstName')">{{'LST_FRIST_NAME' | translate}}
                    <span ng-show="sort.column == 'usersInfo.userFirstName'">
                        <span ng-show="!sort.descending" class="glyphicon glyphicon-sort-by-attributes"></span>
                        <span ng-show="sort.descending" class="glyphicon glyphicon-sort-by-attributes-alt"></span>
                    </span>
                </th>
                <th ng-click="changeSorting('usersInfo.userLastName')">{{'LST_LAST_NAME' | translate}}
                    <span ng-show="sort.column == 'usersInfo.userLastName'">
                        <span ng-show="!sort.descending" class="glyphicon glyphicon-sort-by-attributes"></span>
                        <span ng-show="sort.descending" class="glyphicon glyphicon-sort-by-attributes-alt"></span>
                    </span>
                </th>
                <th ng-click="changeSorting('usersInfo.userEmail')">{{'LST_EMAIL' | translate}}
                    <span ng-show="sort.column == 'usersInfo.userEmail'">
                        <span ng-show="!sort.descending" class="glyphicon glyphicon-sort-by-attributes"></span>
                        <span ng-show="sort.descending" class="glyphicon glyphicon-sort-by-attributes-alt"></span>
                    </span>
                </th>
                <th ng-click="changeSorting('usersInfo.userPhone')">{{'LST_PHONE' | translate}}
                    <span ng-show="sort.column == 'usersInfo.userPhone'">
                        <span ng-show="!sort.descending" class="glyphicon glyphicon-sort-by-attributes"></span>
                        <span ng-show="sort.descending" class="glyphicon glyphicon-sort-by-attributes-alt"></span>
                    </span>
                </th>
                <th ng-click="changeSorting('usersInfo.userAddress')">{{'LST_ADDRESS' | translate}}
                    <span ng-show="sort.column == 'usersInfo.userAddress'">
                        <span ng-show="!sort.descending" class="glyphicon glyphicon-sort-by-attributes"></span>
                        <span ng-show="sort.descending" class="glyphicon glyphicon-sort-by-attributes-alt"></span>
                    </span>
                </th>
                <th ng-click="changeSorting('userInfo.roleName')">{{'LST_ROLE' | translate}}
                    <span ng-show="sort.column == 'userInfo.roleName'">
                        <span ng-show="!sort.descending" class="glyphicon glyphicon-sort-by-attributes"></span>
                        <span ng-show="sort.descending" class="glyphicon glyphicon-sort-by-attributes-alt"></span>
                    </span>
                </th>
                <th ng-click="changeSorting('enabled')">{{'LST_STATUS' | translate}}
                    <span ng-show="sort.column == 'enabled'">
                        <span ng-show="sort.descending" class="glyphicon glyphicon-sort-by-attributes"></span>
                        <span ng-show="!sort.descending" class="glyphicon glyphicon-sort-by-attributes-alt"></span>
                    </span>
                </th>
                <th>{{'LST_ACTIONS' | translate}}</th>
            </tr>
        </thead>
        <tbody>
            <tr class="animate-repeat" ng-repeat="user in filteredUser =  (filteredListUser | orderBy:sort.column:sort.descending | filter:search) track by $index ">
                <td>{{$index + 1}}</td>
                <td>{{user.username}}</td>
                <td>{{user.usersInfo.userFirstName}}</td>
                <td>{{user.usersInfo.userLastName}}</td>
                <td>{{user.usersInfo.userEmail}}</td>
                <td>{{user.usersInfo.userPhone}}</td>
                <td>{{user.usersInfo.userAddress}}</td>
                <td>{{user.roles.description}}</td>
                <td class="vert-align text-center">
                    <span ng-class="{'label label-success' : user.enabled, 'label label-danger' : !user.enabled}">{{user.enabled
                            | active}}
                    </span>
                </td>
                <td>
                    <a id="lstEditUser" href="#/user/{{user.id}}" title="{{'LST_EDIT' | translate}}">
                        <img alt="" src="resources/images/edit-icon.png">
                    </a>|
                    <a id="lstDeleteUser" href="" title="{{'LST_DELETE' | translate}}" ng-click="showDeleteModal(user)">
                        <img alt="" src="resources/images/delete-icon.png">
                    </a>
                </td>
            </tr>
        </tbody>
    </table>
    <div class="text-center" ng-show="filteredUser.length">
        <pagination ng-model="currentPage" total-items="users.length" class="pagination-sm" max-size="maxSize"
            boundary-links="true" items-per-page="numPerPage" rotate="false" num-pages="numPages"
            previous-text="&lsaquo;" next-text="&rsaquo;"
            first-text="&laquo;" last-text="&raquo;"> </pagination>
    </div>
    <div ng-if="!filteredUser.length && search.length">
        <p class="no-result">{{'LST_NO_RESULTS_FOUND' | translate}}</p>
    </div>
</div>