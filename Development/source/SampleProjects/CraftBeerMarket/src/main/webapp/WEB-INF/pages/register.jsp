<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Register</title>
    <script type="text/javascript">
        function validation() {
            var username = document.registerForm.username.value;
            var password = document.registerForm.password.value;
            var matchpassword = document.registerForm.matchpassword.value;
            var firstname = document.registerForm.firstname.value;
            var lastname = document.registerForm.lastname.value;
            var email = document.registerForm.email.value;
            var atposition = email.indexOf("@");
            var dotposition = email.lastIndexOf(".");
            var address = document.registerForm.address.value;
            
            if (username == null || username.trim().length < 6) {
                document.getElementById("error_username").innerHTML = "Username is not valid.";
                return false;
            } else if (password.search(" ") != -1 || password.length < 6) {
                document.getElementById("error_password").innerHTML = 
                    "Invalid password (At least six character and no space).";
                return false;
            } else if (matchpassword != password) {
                document.getElementById("error_matchpassword").innerHTML = "Password is not match.";
                return false;
            } else if (firstname == null || firstname.trim() == "") {
                document.getElementById("error_firstname").innerHTML = "Firstname can't be empty.";
                return false;
            } else if (lastname == null || lastname.trim() == "") {
                document.getElementById("error_lastname").innerHTML = "Lastname can't be empty.";
                return false;
            } else if (email == null || email.trim() == "") {
                document.getElementById("error_email").innerHTML = "Email must not empty.";
                return false;
            } else if (atposition<1 || dotposition<atposition+2 || dotposition+2>=x.length) {
                document.getElementById("error_email").innerHTML = "Invalid email.";
                return false;  
            } else if (address == null || address.trim() == "") {
                document.getElementById("error_address").innerHTML = "Address cannot by empty.";
            }
            return true;
        }
    </script>
</head>

<body>
    <h1>Register</h1>
    <form:form name="registerForm" method="POST" onsubmit="return validation()"
             action="/CraftBeerMarket/customer/registration/action">
        <table>
        <!-- Username -->
        <tr>
            <td><label>Username</label>
            </td>
            <td><form:input name="username" path="username" type="text" /></td>
            <form:errors id="error_username" path="username" element="div"/>
        </tr>
        <!-- Password -->
        <tr>
            <td><label>Password</label>
            </td>
            <td><form:input name="password" path="password" type="password" /></td>
            <form:errors id="error_password" path="password" element="div" />
        </tr>
        <!-- Match password -->
        <tr>
            <td><label>Retype password</label>
            </td>
            <td><form:input name="matchpassword" path="matchpassword" type="password" /></td>
            <form:errors id="error_matchpassword" path="matchpassword" element="div" />
        </tr>
        <!-- FirstName -->
        <tr>
            <td><label>Firstname</label>
            </td>
            <td><form:input name="firstname" path="firstname" type="text" /></td>
            <form:errors id="error_firstname" path="firstname" element="div" />
        </tr>
        <!-- LastName -->
        <tr>
            <td><label>Lastname</label>
            </td>
            <td><form:input name="lastname" path="lastname" type="text" /></td>
            <form:errors id="error_lastname" path="lastname" element="div" />
        </tr>
        <!-- Email -->
        <tr>
            <td><label>Email</label>
            </td>
            <td><form:input name="email" path="email" type="text" /></td>
            <form:errors id="error_email" path="email" element="div" />
        </tr>
        <!-- Address -->
        <tr>
            <td><label>Address</label>
            </td>
            <td><form:input name="address" path="address" type="text" /></td>
            <form:errors id="error_address" patt="address" element="div" />
        </tr>
        </table>
        <button type="submit">Submit</button>
    </form:form>
</body>
</html>