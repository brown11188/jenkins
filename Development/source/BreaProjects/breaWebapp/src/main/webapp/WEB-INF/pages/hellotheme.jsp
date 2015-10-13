<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/pages/include/taglib.jsp" %>

<spring:theme code="css" var="css" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello Spring Theme</title>
<link href="${css}" rel="stylesheet" />
</head>
<body>

    <div style="border:1px solid red; padding:4px">
        <div style="float:left; width:20%;">
            <a href="home">Home</a>
        </div>
        <div align="right" style="float:left; width:80%;">
            Language: 
            <a href="?lang=en_US">English</a> | <a href="?lang=fr_FR">French</a> | 
            Theme: 
            <a href="?theme=default">Default</a> | <a href="?theme=black">Black</a> | <a href="?theme=blue">Blue</a>
        </div>
        <br style="clear:left;" />
    </div>
    <br /><br /><br />
    <div align="center">
        <spring:message code="title.page.login" /><br />
        <spring:message code="label.form.login.username" /><br />
        <spring:message code="label.form.login.password" /><br />
        <spring:message code="label.form.login.login" /><br />
        <spring:message code="label.form.login.rememberMe" /><br />
        <spring:message code="label.form.login.forgotPassword" />
    </div>

</body>
</html>
