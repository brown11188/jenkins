<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<title><spring:message code="title" /></title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href='<spring:theme code="styleSheet" />' />
<link rel="stylesheet" href='<spring:theme code="bootstrapcss" />'>
<!--[if lt IE 9]>
  <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
</head>
<body>
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                    <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
                </button>
            </div>
            <div>
                <div class="collapse navbar-collapse navbar-right" id="myNavbar">
                    <ul class="nav navbar-nav">
                        <li><a href="#home"><spring:message code="menu.home" /></a></li>
                        <li><a href="#about"><spring:message code="menu.aboutus" /></a></li>
                        <li><a href="#project"><spring:message code="menu.project" /></a></li>
                        <li><a href="#contact"><spring:message code="menu.contact" /></a></li>
                        <li><a data-toggle="modal" data-target="#myModal"><spring:message code="menu.login" /></a></li>
                    </ul>
                </div>
            </div>
        </div>
    </nav>
    <section class="home_center_content" id="home">
        <div class="row">
            <div class="col-md-12">
                <div id="myCarousel" class="carousel slide" data-ride="carousel" data-pause="hover">
                    <!-- Indicators -->
                    <ol class="carousel-indicators">
                        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                        <li data-target="#myCarousel" data-slide-to="1"></li>
                        <li data-target="#myCarousel" data-slide-to="2"></li>
                    </ol>
                    <!--Wrapper for slides-->
                    <div class="carousel-inner" role="listbox">
                        <div class="item active">
                            <img src='<spring:theme code="imageHeader1"/>' alt="" class="imgSlide" id="image1" />
                            <div class="carousel-caption">
                                <h3>
                                    <spring:message code="message.slide.1"></spring:message>
                                </h3>
                            </div>
                        </div>
                        <div class="item">
                            <img src='<spring:theme code="imageHeader2"/>' alt="" class="imgSlide" id="image2" />
                            <div class="carousel-caption">
                                <h3>
                                    <spring:message code="message.slide.2" />
                                </h3>
                            </div>
                        </div>
                        <div class="item">
                            <img src='<spring:theme code="imageHeader3"/>' alt="" class="imgSlide" id="image3" />
                            <div class="carousel-caption">
                                <h3>
                                    <spring:message code="message.slide.3" />
                                </h3>

                            </div>
                        </div>
                    </div>
                    <!--Left and right controls-->
                    <!-- Controls -->
                    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                        <span
                            class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                        <span class="sr-only">
                                <spring:message code="previous" />
                        </span>
                    </a>
                    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                        <span
                            class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                        <span class="sr-only"><spring:message
                                    code="next" /></span>
                    </a>
                </div>
            </div>

        </div>
    </section>

    <section class="main_content">
        <div class="content" id="about">
            <div class="row">
                <div class="col-md-6">
                    <h1>
                        <spring:message code="aboutus.title" />
                    </h1>
                    <p>
                        <spring:message code="aboutus.content" />
                    </p>
                </div>
                <div class="col-md-6">
                    <h1>
                        <spring:message code="emailnewletter.title" />
                    </h1>
                    <p>
                        <spring:message code="emailnewletter.content" />
                    </p>
                    <form class="form-inline" role="form">
                        <div class="form-group">
                            <input type="email" class="form-control" placeholder='<spring:message code="email" />' />
                            <button type="button" class="btn btn-primary" onclick="this.value=''">
                                <spring:message code="btnSubcribe" />
                            </button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6 project" id="project">
                    <h1>
                        <spring:message code="featuredprojects" />
                    </h1>
                    <div class="project_box">
                        <img src='<spring:theme code="imageProject" />' alt="" border="0"
                            class="img-thumbnail img-responsive" />
                    </div>
                </div>
                <div class="col-md-6 project">
                    <h1>
                        <spring:message code="newandupdate.title" />
                    </h1>
                    <div class="news_box">
                        <img src='<spring:theme code="imageNewThumb1" />' class="news_thumb img-responsive" alt="" />
                        <p class="news_content">
                            <spring:message code="newandupdate.content" />
                        </p>
                    </div>
                    <div class="news_box">
                        <img src='<spring:theme code="imageNewThumb2" />' class="news_thumb img-responsive" alt="" />
                        <p class="news_content">
                            <spring:message code="newandupdate.content" />
                        </p>
                    </div>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
    </section>
    <section class="main_content" id="contact">
        <div class="content">
            <div class="row">
                <div class="col-md-4">
                    <h2>
                        <spring:message code="getintouch.title" />
                    </h2>
                    <span class="email"> <spring:message code="getintouch.email" />
                    </span>
                    <div class="footer_info">
                        <span class="orange"> <spring:message code="getintouch.address.title" />
                        </span>
                        <p class="info">
                            <spring:message code="getintouch.address.content" />
                        </p>
                    </div>
                    <div class="footer_info">
                        <span class="orange"> <spring:message code="getintouch.phone.title" />
                        </span>
                        <p class="info">
                            <spring:message code="getintouch.phone.content" />
                        </p>
                    </div>
                    <div class="footer_copyrights">
                        <img src='<spring:theme code="footerLogo" />' alt="" /><br />
                        <spring:message code="copyright" />
                        <br />
                    </div>
                </div>
                <div class="col-md-5">
                    <h2>
                        <spring:message code="favorites.title" />
                    </h2>
                    <div class="favorites_box">
                        <span class="fav_nr"> <spring:message code="favorite.box.1" />
                        </span>
                        <p class="favorites">
                            <spring:message code="favorites.content" />
                        </p>
                    </div>
                    <div class="favorites_box">
                        <span class="fav_nr"> <spring:message code="favorite.box.2" /></span>
                        <p class="favorites">
                            <spring:message code="favorites.content" />
                        </p>
                    </div>
                    <div class="favorites_box">
                        <span class="fav_nr"> <spring:message code="favorite.box.3" /></span>
                        <p class="favorites">
                            <spring:message code="favorites.content" />
                        </p>
                    </div>
                </div>
                <div class="col-md-3">
                    <h2>
                        <spring:message code="links.title" />
                    </h2>
                    <div class="list-group">
                        <a href="#" class="list-group-item"> <spring:message code="links.home" />
                        </a> <a href="#" class="list-group-item"> <spring:message code="links.services" />
                        </a> <a href="#" class="list-group-item"> <spring:message code="links.clients" />
                        </a> <a href="#" class="list-group-item"> <spring:message code="links.work" />
                        </a> <a href="#" class="list-group-item"> <spring:message code="termconditions" />
                        </a> <a href="#" class="list-group-item"> <spring:message code="rss" />
                        </a> <a href="#" class="list-group-item"> <spring:message code="contact" />
                        </a>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
    </section>
    <div class="modal fade" id="myModal" role="dialog">
        <div class="modal-dialog modal-sm">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h2 class="modal-title text-center">
                        <spring:message code="login" />
                    </h2>
                </div>
                <div class="modal-body">
                    <form name="loginForm" novalidate method="post" ng-app="myApp" ng-controller="loginController">
                        <div class="form-group"
                            ng-class="{'has-error': loginForm.username.$dirty && loginForm.username.$invalid}">
                            <input class="form-control" name="username" type="text"
                                placeholder='<spring:message code="username" />' required ng-model="username" />
                            <span
                                class="help-block"
                                ng-show="loginForm.username.$dirty && loginForm.username.$error.required"> <spring:message
                                    code="username.required" />
                            </span>
                        </div>
                        <div class="form-group"
                            ng-class="{'has-error':loginForm.password.$dirty && loginForm.password.$invalid}">
                            <input class="form-control" name="password" type="password" ng-minlength="8"
                                ng-maxlength="20" placeholder='<spring:message code="password" />' required
                                ng-model="password" />
                            <div ng-show="loginForm.password.$dirty && loginForm.password.$invalid">
                                <span class="help-block" ng-show="loginForm.password.$error.required">
                                    <spring:message
                                        code="password.required" />
                                </span>
                                <span class="help-block" ng-show="loginForm.password.$error.minlength">
                                    <spring:message
                                            code="password.minlength" />
                                </span>
                                <span class="help-block" ng-show="loginForm.password.$error.maxlength">
                                    <spring:message
                                            code="password.maxlength" />
                                </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <button type="button" data-dismiss="modal" class="btn btn-primary btn-block" value="Login"
                                title="Login" ng-disabled="!loginForm.$valid">
                                <spring:message code="signin" />
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <script src='<spring:theme code="jquery" />'></script>
    <script src='<spring:theme code="bootstrapjs" />'></script>
    <script src='<spring:theme code="creativejs" />'></script>
    <script src='<spring:theme code="angularjs" />'></script>
    <script src='<spring:theme code="myAppjs" />'></script>
    <script src='<spring:theme code="loginControllerjs" />'></script>
</html>
