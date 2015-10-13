<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  

<html>

<head>  
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
    <title>Admin page</title>
    
    <script type="text/javascript">
        function validateForm() {
            var username = document.addUser.username.value;
            var password = document.addUser.password.value;
            var matchpass = document.addUser.matchPassword.value;

            if (username == null || username == "" || username.length < 6) {
                alert("Username can't be blank and at least six characters.");
                return false;
            } else if (password.length < 6) {
                alert("Password must be at least 6 characters.");
                return false;
            } else if (password != matchpass) {
                alert("Password must be same.");  
                return false;
            }

            return true;
        }
    </script>
</head>  

<body>
    <h1>Title : ${title}</h1>
    <h1>Message : ${message}</h1>

        <!-- For login user -->
        <c:url value="/j_spring_security_logout" var="logoutUrl" />
        <form action="${logoutUrl}" method="post" id="logoutForm">
            <input type="hidden" name="${_csrf.parameterName}"
                value="${_csrf.token}" />
        </form>
        <script>
            function formSubmit() {
                document.getElementById("logoutForm").submit();
            }
        </script>

        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <h2>
                User : ${pageContext.request.userPrincipal.name} | <a
                    href="javascript:formSubmit()"> Logout</a> | <a
                    href="/CraftBeerMarket/welcome">Home</a> | <a
                    href="/CraftBeerMarket/customer">Customer</a>
            </h2>
        </c:if>

    <h2>Create Admin User</h2>
    <form:form name="addUser" method="POST" action="/CraftBeerMarket/adduser" onsubmit="return validateForm()">
        <table>
            <tr>
                <td><form:label path="username">User Name</form:label></td>
                <td><form:input path="username" name="username"/></td>
            </tr>
            <tr>
                <td><form:label path="password">Password</form:label></td>
                <td><form:input path="password" type="password" name="password"/></td>
            </tr>
            <tr>
                <td><form:label path="matchPassword">Retype Password</form:label></td>
                <td><form:input path="matchPassword" type="password" name="matchPassword"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Submit"/>
                </td>
            </tr>
        </table>  
    </form:form>

</body>
</html>