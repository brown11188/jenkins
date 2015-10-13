<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/pages/include/taglib.jsp"%>

<c:if test="${pageContext.request.userPrincipal.name != null}">
    <c:redirect url="/home"/>
</c:if>

<spring:message code="param.form.login.username" var="param_username" />
<spring:message code="param.form.login.password" var="param_password" />
<spring:message code="title.page.login" var="pageTitle" />
<spring:message code="title.form.login" var="formTitle" />
<spring:message code="label.form.login.username" var="username" />
<spring:message code="label.form.login.password" var="password" />
<spring:message code="label.form.login.login" var="login" />
<spring:message code="label.form.login.rememberMe" var="rememberMe" />
<spring:message code="label.form.login.forgotPassword" var="forgotPassword" />
<spring:message code="error.username.required" var="errorUserName" />
<spring:message code="error.password.required" var="errorPassword" />

<spring:url value="/resources/css/bootstrap-3.3.5.min.css" var="bootstrapCss" />
<spring:url value="/resources/css/navbar-fixed-top.css" var="navbarCss"></spring:url>
<spring:url value="/resources/css/login-page.css" var="loginCss"></spring:url>

<spring:url value="/resources/js/jquery-1.11.3.min.js" var="jquery" />
<spring:url value="/resources/js/bootstrap-3.3.5.min.js" var="bootstrapJs" />
<spring:url value="/resources/js/login-page.js" var="loginJs" />
<spring:url value="/resources/js/ie10-viewport-bug-workaround.js" var="ie10ViewportBugWorkaround" />
<spring:url value="/resources/js/angular-1.4.4.min.js" var="angularJs" />
<spring:url value="/resources/js/angular-route-1.4.4.js" var="angularRouteJs" />
<spring:url value="/resources/js/brea-app.js" var="breaAppJs" />
<spring:url value="/resources/js/nav-bar-controller.js" var="navBarController" />
<spring:url value="/resources/js/ui-bootstrap-tpls-0.13.3.min.js" var="uiBootrapTpls" />
<spring:url value="/resources/js/angular-translate-2.7.2.min.js" var="angularTranslate" />
<spring:url value="/resources/js/angular-translate-loader-partial.min.js" var="angularTranslateLoaderPartial" />
<spring:url value="/resources/js/angular-cookies-1.4.4.js" var="angularCookies" />
<spring:url value="/resources/js/angular-translate-storage-cookie-2.7.2.js" var="angularTranslateCookies" />
<spring:url value="/resources/js/angular-animate-1.4.4.min.js" var="angularAnimate" />

<!DOCTYPE html>
<html lang="en" ng-app="breaApp" ng-controller="navBarController">
<head>
<title>${pageTitle}</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${navbarCss}" rel="stylesheet" />
<link href="${loginCss}" rel="stylesheet" />

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body >

    <%@ include file="/WEB-INF/pages/include/navigation_bar.jsp"%>
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-sm-6 col-xs-8 col-md-offset-4 col-xs-offset-2 col-sm-offset-3">
                <div class="wrap ">
                    <form class="login" name="loginForm" novalidate 
                        action="<c:url value='/j_spring_security_check' />" method='POST'>
                        <div class="form-group">
                            <p class="form-title">${formTitle}</p>
                        </div>
                        <div class="form-group">
                            <c:if test="${not empty error}">
                                <div class="error-title">${error}</div>
                            </c:if>
                            <c:if test="${not empty msg}">
                                <div class="error-msg">${msg}</div>
                            </c:if>
                        </div>
                        <div class="form-group"
                            ng-class="{'has-error': loginForm.username.$dirty && loginForm.username.$invalid}">
                            <input class="form-control" name="${param_username}" type="text" placeholder="${username}"
                                required ng-model="username" /> <span class="help-block"
                                ng-show="loginForm.username.$dirty && loginForm.username.$invalid">
                                ${errorUserName} </span>
                        </div>

                        <div class="form-group"
                            ng-class="{'has-error':loginForm.password.$dirty && loginForm.password.$invalid}">
                            <input class="form-control" name="${param_password}" type="password"
                                placeholder="${password}" required ng-model="password" />
                            <div ng-show="loginForm.password.$dirty && loginForm.password.$invalid">
                                <span class="help-block" ng-show="loginForm.password.$error.required">
                                    ${errorPassword} </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="remember-forgot">
                                <div class="row">
                                    <div class="col-md-6 col-sm-6 ">
                                        <div class="checkbox">
                                            <label> <input type="checkbox" name="remember-me" /> ${rememberMe}
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-md-6 col-sm-6 forgot-pass-content">
                                        <a href="#" class="forgot-pass">${forgotPassword}</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <input name="submit" type="submit" value="${login}" class="btn btn-success btn-block"
                            ng-disabled="!loginForm.$valid" /> <input type="hidden" name="${_csrf.parameterName}"
                            value="${_csrf.token}" />
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- Scripts -->
    <script src="${jquery}"></script>
    <script src="${bootstrapJs}"></script>
    <script src="${loginJs}"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="${ie10ViewportBugWorkaround}"></script>
    <script src="${angularJs}"></script>
    <script src="${angularRouteJs}"></script>
    <script src="${breaAppJs}"></script>
    <script src="${navBarController}"></script>
    <script src="${uiBootrapTpls}"></script>
    <script src="${angularTranslate}"></script>
    <script src="${angularTranslateLoaderPartial }"></script>
    <script src="${angularCookies}"></script>
    <script src="${angularTranslateCookies}"></script>
    <script src="${angularAnimate}"></script>
</body>
</html>
