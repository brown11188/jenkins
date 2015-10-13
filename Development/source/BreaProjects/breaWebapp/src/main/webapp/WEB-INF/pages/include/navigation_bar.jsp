<spring:message code="site.brand" var="siteBrand" />
<spring:message code="link.navbar.loggedInAs" var="loggedInAs" />
<spring:message code="link.navbar.login" var="login" />
<spring:message code="link.navbar.logout" var="logout" />
<spring:message code="link.navbar.theme.light" var="light" />
<spring:message code="link.navbar.theme.dark" var="dark" />
<spring:message code="link.navbar.lang.english" var="english" />
<spring:message code="link.navbar.lang.french" var="french" />
<spring:message code="link.navbar.helloWorld" var="helloWorld" />
<spring:message code="link.navbar.helloBootstrap" var="helloBootstrap" />
<spring:message code="link.navbar.helloSpringTheme" var="helloSpringTheme" />
<spring:message code="link.navbar.helloPentaho" var="helloPentaho" />

<spring:theme code="navbar.theme" var="navbarTheme" />

<c:url value="/j_spring_security_logout" var="logoutUrl" />

    <nav class="navbar ${navbarTheme} navbar-fixed-top">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="home">${siteBrand}</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <!-- <ul class="nav navbar-nav">
                    <li><a href="#">Link <span class="sr-only">(current)</span></a></li>
                    <li><a href="#">Link</a></li>
                    <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                        aria-haspopup="true" aria-expanded="false"
                    >Dropdown <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Action</a></li>
                            <li><a href="#">Another action</a></li>
                            <li><a href="#">Something else here</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#">Separated link</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#">One more separated link</a></li>
                        </ul></li>
                </ul>
                <form class="navbar-form navbar-left" role="search">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search">
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form> -->
                <ul class="nav navbar-nav navbar-right">
                    <li>
                    <c:choose>
                        <c:when test="${pageContext.request.userPrincipal.name != null}">
                            <p class="navbar-text">
                                ${loggedInAs} <a href="" class="navbar-link">${pageContext.request.userPrincipal.name}</a> | 
                                <a href="javascript:formSubmit()" class="navbar-link">${logout}</a>
                            </p>
                        </c:when>
                        <c:otherwise>
                            <p class="navbar-text"><a href="login" class="navbar-link">${login}</a></p>
                        </c:otherwise>
                    </c:choose>
                    </li>
                    <li class="<c:if test="${navbarTheme=='navbar-default'}" >active</c:if>"><a href="?theme=light" ng-click="changeTheme('?theme=light',$event)">${light}</a></li>
                    <li class="<c:if test="${navbarTheme=='navbar-inverse'}" >active</c:if>"><a href="?theme=dark" ng-click="changeTheme('?theme=dark',$event)">${dark}</a></li>
                    <li class="<c:if test="${pageContext.response.locale=='en_US'}" >active</c:if>" ><a id="language" href="?lang=en_US" ng-click="changeLanguage('en',$event)">${english}</a></li>
                    <li class="<c:if test="${pageContext.response.locale=='fr_FR'}" >active</c:if>" ><a id="language" href="?lang=fr_FR" ng-click="changeLanguage('fr',$event)">${french}</a></li>
                    <li class="dropdown"><a href="" class="dropdown-toggle" data-toggle="dropdown" role="button"
                        aria-haspopup="true" aria-expanded="false">${helloWorld} <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <!-- <li><a href="#">Action</a></li>
                            <li><a href="#">Another action</a></li>
                            <li><a href="#">Something else here</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#">Separated link</a></li>
                            <li role="separator" class="divider"></li> -->
                            <li><a href="hellotheme">${helloSpringTheme}</a></li>
                        </ul></li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>

    <sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
        <form action="${logoutUrl}" method="post" id="logoutForm">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>
        <script>
            function formSubmit() {
                document.getElementById("logoutForm").submit();
            }
        </script>
    </sec:authorize>