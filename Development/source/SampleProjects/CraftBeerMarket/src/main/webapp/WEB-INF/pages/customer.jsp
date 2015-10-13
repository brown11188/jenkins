<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add Customer</title>
    <script type="text/javascript">
        function validateFormCustomer() {
            var username = document.addCustomer.username.value;
            var email = document.addCustomer.email.value;
            var atposition = email.indexOf("@");
            var dotposition = email.lastIndexOf(".");

            if (username == null || username == "" || username.length < 6) {
                alert("Username can't be empty and at least six characters.");
                return false;
            } else if (email == null || email == "") {
                alert("Email must not be empty.");
                return false;
            } else if (atposition<1 || dotposition<atposition+2 || dotposition+2>=x.length) {
                alert("Invalid e-mail address.");
                return false;  
            }

            return true;
        }
    </script>
</head>
<body>
    <h2>Customer Management</h2>
    <form:form name="addCustomer" method="POST" action="/CraftBeerMarket/customer/add" onsubmit="return validateFormCustomer()">
        <table>
            <tr>
                <td><form:label path="username">Username</form:label></td>
                <td><form:input path="username" type="text" name="username"/></td>
            </tr>
            <tr>
                <td><form:label path="firstName">First Name</form:label></td>
                <td><form:input path="firstName" type="text" name="firstName"/></td>
            </tr>
            <tr>
                <td><form:label path="lastName">Last Name</form:label></td>
                <td><form:input path="lastName" type="text" name="lastName"/></td>
            </tr>
            <tr>
                <td><form:label path="email">Email</form:label></td>
                <td><form:input path="email" type="text" name="email"/></td>
            </tr>
            <tr>
                <td><form:label path="address">Address</form:label></td>
                <td><form:input path="address" type="text" name="address"/></td>
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