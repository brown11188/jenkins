<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/include/taglib.jsp" %>

<c:set var="username" value="${pageContext.request.userPrincipal.name}"/>
<c:if test="${username == null}">
    <c:redirect url="/login"/>
</c:if>

<spring:message code="title.page.home" var="pageTitle" />

<spring:url value="/resources/css/bootstrap-3.3.5.min.css" var="bootstrapCss" />
<spring:url value="/resources/css/navbar-fixed-top.css" var="navbarCss"></spring:url>

<spring:url value="/resources/js/jquery-1.11.3.min.js" var="jquery" />
<spring:url value="/resources/js/bootstrap-3.3.5.min.js" var="bootstrapJs" />
<spring:url value="/resources/js/ie10-viewport-bug-workaround.js" var="ie10ViewportBugWorkaround" />
<spring:url value="/resources/js/angular-1.4.4.min.js" var="angularJs" />
<spring:url value="/resources/js/angular-route-1.4.4.js" var="angularRouteJs" />
<spring:url value="/resources/js/brea-app.js" var="breaAppJs" />
<spring:url value="/resources/js/nav-bar-controller.js" var="controllerJs" />
<spring:url value="/resources/js/ui-bootstrap-tpls-0.13.3.min.js" var="uiBootrapTpls" />
<spring:url value="/resources/js/angular-translate-2.7.2.min.js" var="angularTranslate" />
<spring:url value="/resources/js/angular-translate-loader-partial.min.js" var="angularTranslateLoaderPartial" />
<spring:url value="/resources/js/angular-cookies-1.4.4.js" var="angularCookies" />
<spring:url value="/resources/js/angular-translate-storage-cookie-2.7.2.js" var="angularTranslateCookies" />
<spring:url value="/resources/js/angular-animate-1.4.4.min.js" var="angularAnimate" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="breaApp" ng-controller="navBarController">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${pageTitle}</title>
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${navbarCss}" rel="stylesheet" />

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body >

    <%@ include file="/WEB-INF/pages/include/navigation_bar.jsp"%>

    <sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
        <div class="container">
            <div class="jumbotron">
                <h1>Hello, ${username}!</h1>
                <p>Welcome to BREA, a wonderful system built on Spring MVC, Bootstrap & AngularJs.</p>
                <p><a class="btn btn-primary btn-lg" href="#" role="button">Your Profile</a></p>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">What's next?</h3>
                </div>
                <div class="panel-body">
                    <p>
                        Hate someone and want to get rid of them? You can kick their ass in 
                        <a class="btn btn-primary btn-xs" href="admin" role="button">Admin</a>
                    </p>
                    <p>
                        Want to know what's going on? You can find everything in 
                        <a class="btn btn-primary btn-xs" href="display" role="button">Report</a>
                    </p>
                </div>
            </div>
        </div>
    </sec:authorize>

    <!-- Scripts -->
    <script src="${jquery}"></script>
    <script src="${bootstrapJs}"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="${ie10ViewportBugWorkaround}"></script>
    <script src="${angularJs}"></script>
    <script src="${angularRouteJs}"></script>
    <script src="${breaAppJs}"></script>
    <script src="${controllerJs}"></script>
    <script src="${uiBootrapTpls}"></script>
    <script src="${angularTranslate}"></script>
    <script src="${angularTranslateLoaderPartial }"></script>
    <script src="${angularCookies}"></script>
    <script src="${angularTranslateCookies}"></script>
    <script src="${angularAnimate}" ></script>
</body>
</html>