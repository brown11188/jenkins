<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@page session="true"%>
<spring:url value="/resources/css/bootstrap-3.3.5.min.css" var="bootstrapCss" />
<spring:url value="/resources/js/bootstrap-3.3.5.min.js" var="bootstrapJs" />
<spring:url value="/resources/js/jquery-1.11.3.min.js" var="jquery" />
<spring:url value="/resources/js/admin-page.js" var="adminPageJs" />
<spring:url value="/resources/css/admin-page.css" var="adminPageCss" />
<spring:url value="/resources/js/angular-1.4.4.min.js" var="angularJs" />
<spring:url value="/resources/js/brea-app.js" var="breaAppJs" />
<spring:url value="/resources/js/admin-page-config.js" var="adminPageConfig" />
<spring:url value="#/profile/${pageContext.request.userPrincipal.name}" var="adminProfileUrl" />
<spring:url value="#/password" var="adminChangePasswordUrl" />
<spring:url value="#/users" var="listUserUrl" />
<spring:url value="/resources/js/angular-route-1.4.4.js" var="angularRouteJs" />
<spring:url value="/resources/js/user-profile-controller.js" var="userProfileController" />
<spring:url value="/resources/js/user-service.js" var="userProfileService" />
<spring:url value="/resources/js/password-controller.js" var="passwordController" />
<spring:url value="/resources/js/custom-directive.js" var="customDirective" />
<spring:url value="/resources/js/list-user-controller.js" var="userController" />
<spring:url value="/resources/js/ui-bootstrap-tpls-0.13.3.min.js" var="uiBootrapTpls" />
<spring:url value="/resources/js/angular-translate-2.7.2.min.js" var="angularTranslate" />
<spring:url value="/resources/js/angular-translate-loader-partial.min.js" var="angularTranslateLoaderPartial" />
<spring:url value="/resources/js/add-user-controller.js" var="addUserController" />
<spring:url value="/resources/js/constant.js" var="constant" />
<spring:url value="/resources/css/navbar-fixed-top.css" var="navbarCss"></spring:url>
<spring:message code="link.navbar.helloWorld" var="helloWorld" />
<spring:message code="link.navbar.helloSpringTheme" var="helloSpringTheme" />
<spring:url value="/resources/js/angular-cookies-1.4.4.js" var="angularCookies" />
<spring:url value="/resources/js/angular-translate-storage-cookie-2.7.2.js" var="angularTranslateCookies" />
<spring:url value="/resources/js/ie10-viewport-bug-workaround.js" var="ie10ViewportBugWorkaround" />
<spring:url value="/resources/js/angular-animate-1.4.4.min.js" var="angularAnimate" />

<spring:theme code="css" var="css" />
<!DOCTYPE html>
<html ng-app="breaApp" ng-controller="adminController">
<head>
<title>{{'ADMIN_TITLE' | translate }}</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="_csrf" content="${_csrf.token}" />
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${adminPageCss}" rel="stylesheet" />
<link href="${css}" rel="stylesheet" />
</head>
<body>
    <div id="menu">
        <%@ include file="/WEB-INF/pages/include/navigation_bar.jsp"%>
    </div>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-3">
                <ul class="nav nav-pills nav-stacked ul-border">
                    <li class="active"><a href=""><span class="glyphicon glyphicon-home"></span> {{'MENU_HOME' |
                            translate }}</a></li>
                    <li><a data-toggle="dropdown" href="${listUserUrl}"><span class="glyphicon glyphicon-user"></span> {{'MENU_USER'
                            | translate}}</a></li>
                    <li><a href="#/adduser"><span class="glyphicon glyphicon-plus"></span>
                            {{'ADD_USER' | translate}}</a></li>
                    <li class="active"><a href=""><span class="glyphicon glyphicon-cog"></span> {{'YOUR_ACCOUNT' |
                            translate }}</a></li>
                    <li><a id="adminProfile" href="${adminProfileUrl}"><span class="glyphicon glyphicon-user"></span>
                            {{'USER_PROFILE' | translate}}</a></li>
                   <li><a id="adminPassword" href="${adminChangePasswordUrl}"><span class="glyphicon glyphicon-lock"></span>
                            {{'MENU_CHANGE_PASSWORD' | translate}}</a></li>
                </ul>
            </div>
                <div class="col-md-9 ul-border">
                    <div class="content" ng-view></div>
                </div>
            </div>
    </div>
    <!-- Script -->
    <script src="${jquery}"></script>
    <script src="${bootstrapJs}"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="${ie10ViewportBugWorkaround}"></script>
    <script src="${angularJs}"></script>
    <script src="${angularRouteJs}"></script>
    <script src="${uiBootrapTpls}"></script>
    <script src="${angularTranslate}"></script>
    <script src="${angularTranslateLoaderPartial}"></script>
    <script src="${breaAppJs}"></script>
    <script src="${adminPageConfig}"></script>
    <script src="${adminPageJs}"></script>
    <script src="${userProfileService}"></script>
    <script src="${userProfileController}"></script>
    <script src="${passwordController}"></script>
    <script src="${customDirective}"></script>
    <script src="${userController}"></script>
    <script src="${addUserController}"></script>
    <script src="${constant}"></script>
    <script src="${angularCookies}"></script>
    <script src="${angularTranslateCookies}"></script>
    <script src="${angularAnimate}" ></script>
</body>
</html>