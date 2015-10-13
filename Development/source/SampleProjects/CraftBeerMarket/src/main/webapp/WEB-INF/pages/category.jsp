<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTaglib.tld"%>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<%@ page session="false" %>

<head>
<html>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
    <title>Category Page</title>
</head>
<body>
 <sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
<h1 class="header">
    Add a Category
</h1>
 
<c:url var="addAction" value="/category/add" ></c:url>
<form:form id="categoryForm" class="form-horizontal" action="${addAction}" commandName="category">
<c:if test="${!empty successMessage}">
 <div class="form-group">
    <label class="col-xs-3 control-label"></label>
    <div class="col-xs-6">
      <div class="alert alert-success">
          <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
          <strong>Success!</strong> <c:out value="${successMessage}"></c:out>
      </div>
    </div>
  </div>
</c:if>
<c:if test="${!empty errorMessage }">
<div class="form-group">
    <label class="col-xs-3 control-label"></label>
    <div class="col-xs-6">
      <div class="alert alert-danger">
          <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
          <strong>Warning!</strong> <c:out value="${errorMessage}"></c:out>
      </div>
    </div>
  </div>
</c:if>
<c:if test="${!empty updateSuccess }">
<div class="form-group">
    <label class="col-xs-3 control-label"></label>
    <div class="col-xs-6">
      <div class="alert alert-success">
          <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
          <strong>Success!</strong> <c:out value="${updateSuccess}"></c:out>
      </div>
    </div>
  </div>
</c:if>
<c:if test="${!empty category.name}">
    <div class="form-group">
        <label class="col-xs-3 control-label">ID</label>
        <div class="col-xs-6">
            <form:input path="id" disabled="true" readonly="true" value="${category.id}"/>
            <form:hidden path="id" />
            <%-- <input type="text" class="form-control" name="id"  value="${category.id}" disabled="disabled" readonly="readonly"/> --%>
        </div>
    </div>
 </c:if>

    <div class="form-group">
        <label class="col-xs-3 control-label">Name (*)</label>
        <div class="col-xs-6">
            <input value="${category.name}" type="text" class="form-control" name="name" required="required" placeholder="category name"/>
        </div>
        
    </div>
    <div class="form-group">
        <label class="col-xs-3 control-label">Description</label>
        <div class="col-xs-6">
            <input type="text" class="form-control" name="description" />
        </div>
    </div>

    <!-- #messages is where the messages are placed inside -->
    <div class="form-group">
        <div class="col-xs-9 col-xs-offset-3">
            <div id="messages"></div>
        </div>
    </div>

    <div class="form-group">
        <div class="col-xs-9 col-xs-offset-3">
            <c:if test="${category.id !=0}">
                <input type="submit"
                      class="btn btn-primary" value="<spring:message text="Edit Category"/>" />
            </c:if>
            <c:if test="${category.id == 0}">
                <input type="submit"
                   class="btn btn-primary" value="<spring:message text="Add Category"/>" />
            </c:if>
        </div>
    </div>
</form:form>
<div class="container">
<form name='addBeer' action="<c:url value='/beer' />">
    <input type="submit" class="btn btn-info" value="More Beer">
</form>
</div>

<c:if test="${!empty listCategories}">
<div class="container">
  <div class="well">
   <strong>List of Categories</strong>
  </div>
  <table class="table table-stripped">
   <tr>
       <th>Category ID</th>
       <th>Category Name</th>
       <th>Category Description</th>
       <th colspan="2" class="action">Action</th>
   </tr>
   <c:forEach items="${listCategories}" var="category">
    <tr>
       <td>${category.id}</td>
       <td>${category.name}</td>
       <td>${category.description}</td>
       <td><a href="<c:url value='/edit/${category.id}' />" >Edit</a></td>
       <td><a href="<c:url value='/remove/${category.id}' />" onclick="return confirm('Are you sure you want to delete it?')" >Delete</a></td>
    </tr>
   </c:forEach>
  </table>
  <tag:paginate max="15" offset="${offset}" count="${count}"
   uri="/CraftBeerMarket/category" next="&raquo;" previous="&laquo;" />
 </div>
 </c:if>
</sec:authorize>
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
<style type="text/css">
    .header {text-align: center; color: blue;}
</style>
</html>