<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<%@ page session="false" %>
<html>
<head>
    <title>Beer Page</title>
    <style type="text/css">
        .tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
        .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
        .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
        .tg .tg-4eph{background-color:#f9f9f9}
    </style>
</head>
<body>
<sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
<h1>
    Add a Beer
</h1>
 
<c:url var="addAction" value="/beer/add" ></c:url>
 
<form:form action="${addAction}" commandName="beer">
<table>
  <c:if test="${!empty beer.name}">
    <tr>
        <td>
            <form:label path="id">
                <spring:message text="ID"/>
            </form:label>
        </td>
        <td>
            <form:input path="id" readonly="true" size="8"  disabled="true" />
            <form:hidden path="id" />
        </td> 
    </tr>
    </c:if>
    <tr>
        <td>
            <form:label path="categoryId">
                <spring:message text="Choose Category"/>
            </form:label>
            
        </td>
        <td>
            <select style="width: 172px" name="categoryId">
            <c:forEach items="${listCategories}" var="category">
                <option value="${category.id}" id="cats">
                    <c:out value="${category.name}"></c:out> 
                </option>
            </c:forEach>
            </select>
        </td>
    </tr>
    <tr>
        <td>
            <form:label path="name">
                <spring:message text="Name (*)"/>
            </form:label>
        </td>
        <td>
            <form:input path="name" cssClass="required" required="required"/>
        </td> 
    </tr>
     <tr>
        <td>
            <form:label path="description">
                <spring:message text="Description"/>
            </form:label>
        </td>
        <td>
            <form:input path="description" />
        </td>
    </tr> 
      <tr>
        <td>
            <form:label path="manufacturer">
                <spring:message text="Manufacturer"/>
            </form:label>
        </td>
        <td>
            <form:input path="manufacturer" />
        </td>
    </tr> 
    <tr>
        <td>
            <form:label path="country">
                <spring:message text="Country"/>
            </form:label>
        </td>
        <td>
            <form:input path="country" />
        </td>
    </tr> 
    <tr>
        <td>
            <form:label path="price">
                <spring:message text="Price"/>
            </form:label>
        </td>
        <td>
            <form:input id="price" type="number" onkeypress='return event.charCode >= 48 && event.charCode <= 57' path="price" />
        </td>
    </tr> 
    <tr>
        <td>
            <form:label path="archived">
                <spring:message text="archived"/>
            </form:label>
        </td>
        <td>
            <form:checkbox path="archived" value="${archived}"/>
        </td>
    </tr> 
    <tr>
        <td colspan="2">
            <c:if test="${!empty beer.name}">
                <input type="submit"
                    value="<spring:message text="Edit Beer"/>" />
            </c:if>
            <c:if test="${empty beer.name}">
                <input type="submit"
                    value="<spring:message text="Add Beer"/>" />
            </c:if>
        </td>
    </tr>
</table>  
</form:form>
<br>

<h3>Beer List</h3>

<c:if test="${!empty listBeers}">
    <table class="tg">
    <tr>
        <th width="80">ID</th>
        <th width="80">Category ID</th>
        <th width="120">Name</th>
        <th width="120">Description</th>
        <th width="120">Manufacturer</th>
        <th width="120">Country</th>
        <th width="120">Price</th>
        <th width="120" colspan="3">Action</th>
    </tr>
    <c:forEach items="${listBeers}" var="beer">
        <tr>
            <td>${beer.id}</td>
            <td>${beer.categoryId}</td>
            <td>${beer.name}</td>
            <td>${beer.description}</td>
            <td>${beer.manufacturer}</td>
            <td>${beer.country}</td>
            <td>${beer.price}</td>
            <c:choose>
                <c:when test="${beer.archived == false}">
                    <td><a href="<c:url value='/editBeer/${beer.categoryId}/${beer.id}' />" >Edit</a></td>
                    <td><a href="<c:url value='/removeBeer/${beer.categoryId}/${beer.id}' />" onclick="return confirm('Are you sure you want to delete it?')">Delete</a></td>
                    <td><a class="arch" href='<c:url value='/archivedBeer/${beer.categoryId}/${beer.id}/${beer.archived }'/>'>Unarchived</a>
                    </td>
                </c:when>
                <c:when test="${beer.archived == true}">
                    <td><a href="<c:url value='/editBeer/${beer.categoryId}/${beer.id}' />" >Edit</a></td>
                    <td><a href="<c:url value='/removeBeer/${beer.categoryId}/${beer.id}' />" onclick="return confirm('Are you sure you want to delete it?')">Delete</a></td>
                    <td><a class="arch" href='<c:url value='/archivedBeer/${beer.categoryId}/${beer.id}/${beer.archived }'/>'>Archived</a>
                    </td>
                </c:when>
                <c:otherwise>
                    <td></td>
                    <td></td>
                </c:otherwise>
            </c:choose>
        </tr>
    </c:forEach>
    </table>
</c:if>
<br>

<a href="<c:url value='/category' />" >Back to Category</a>
</sec:authorize>
<script type="text/javascript">
$( document ).ready(function() {
    $("#price").keydown(function (e) {
        // Allow: backspace, delete, tab, escape, enter and .
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
             // Allow: Ctrl+A
            (e.keyCode == 65 && e.ctrlKey === true) ||
             // Allow: Ctrl+C
            (e.keyCode == 67 && e.ctrlKey === true) ||
             // Allow: Ctrl+X
            (e.keyCode == 88 && e.ctrlKey === true) ||
             // Allow: home, end, left, right
            (e.keyCode >= 35 && e.keyCode <= 39)) {
                 // let it happen, don't do anything
                 return;
        }
        // Ensure that it is a number and stop the keypress
        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
            e.preventDefault();
        }
    });
});
</script>
</body>
</html>