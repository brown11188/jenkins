<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/include/taglib.jsp" %>

<c:set var="username" value="${pageContext.request.userPrincipal.name}"/>
<c:if test="${username == null}">
    <c:redirect url="/login"/>
</c:if>

<spring:message code="title.page.report" var="pageTitle" />

<spring:url value="/resources/css/bootstrap-3.3.5.min.css" var="bootstrapCss" />
<spring:url value="/resources/css/navbar-fixed-top.css" var="navbarCss"></spring:url>

<spring:url value="/resources/js/jquery-1.11.3.min.js" var="jquery" />
<spring:url value="/resources/js/bootstrap-3.3.5.min.js" var="bootstrapJs" />
<spring:url value="/resources/js/ie10-viewport-bug-workaround.js" var="ie10ViewportBugWorkaround" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
<body>

    <%@ include file="/WEB-INF/pages/include/navigation_bar.jsp"%>

    <sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
        <div class="container">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Sample Reports without Filters</h3>
                </div>
                <div class="panel-body">
                    <p>
                        Income Statement 
                        <a class="btn btn-primary btn-xs" href="report/incomeStatement/PDF" role="button" target="_blank">PDF</a> 
                        <a class="btn btn-primary btn-xs" href="report/incomeStatement/EXCEL" role="button" target="_blank">Excel</a> 
                        <a class="btn btn-primary btn-xs" href="report/incomeStatement/HTML" role="button" target="_blank">HTML</a>
                    </p>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Sample Chart Reports without Filters</h3>
                </div>
                <div class="panel-body">
                    <p>
                        Area by Group 
                        <a class="btn btn-primary btn-xs" href="report/areaByGroup/PDF" role="button" target="_blank">PDF</a> 
                        <a class="btn btn-primary btn-xs" href="report/areaByGroup/EXCEL" role="button" target="_blank">Excel</a> 
                        <a class="btn btn-primary btn-xs" href="report/areaByGroup/HTML" role="button" target="_blank">HTML</a>
                    </p>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Sample Chart Reports with Filters</h3>
                </div>
                <div class="panel-body">
                    <p>
                        <form class="form-inline" action="report/areaByGroup/PDF" method="GET">
                            Area by Group 
                            <select class="form-control custom" id="yearId" name="yearId">
                                <option>Year</option>
                                <option>2003</option>
                                <option>2004</option>
                                <option>2005</option>
                            </select>
                            <select class="form-control custom" id="outputType" name="outputType">
                                <option>Output Type</option>
                                <option>PDF</option>
                                <option>EXCEL</option>
                                <option>HTML</option>
                            </select>
                            <input class="btn btn-primary btn-xs" type="submit" value="Run" onclick="this.form.target='_blank';return true;">
                        </form>
                    </p>
                </div>
            </div>
             <form action="report/areaByGroupGetParams" method="GET">
               <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Sample Reports with Filters</h3>
                </div>
                <div class="panel-body">
                    <p class="text-primary">
                        Area By Group Filter (PDF)
                    </p>
                    <div class="form-group">
                        <label for="sel1">Choose Year:</label> 
                        <%--<select class="form-control custom" id="sel1" name="year_id">
                                <option></option>
                                <c:forEach var="item" items="${year_value}">
                                    <option value="${year_value[0]">${year_value[1]}</option>
                                </c:forEach>
                          </select> --%>
                       <select class="form-control custom" id="sel1" name="year_id">
                            <option value="0">All The Years</option>
                            <option value="2003">2003</option>
                            <option value="2004">2004</option>
                            <option value="2005">2005</option>
                        </select> 
                    </div>
                    <input class="btn btn-primary" type="submit" value="Download" 
                           onclick="this.form.target='_blank';return true;">
                </div>
            </div>
           </form>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Sample Chart Reports with Filters</h3>
                </div>
                <div class="panel-body">
                    <p>
                        Coming soon
                    </p>
                </div>
            </div>
        </div>
    </sec:authorize>
 

</body>
      <!-- Css -->
     <style type="text/css">
       .custom{
            width: 20%;
        }
     </style>
         <!-- Scripts -->
    <script src="${jquery}"></script>
    <script src="${bootstrapJs}"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="${ie10ViewportBugWorkaround}"></script>
</html>