<%@ include file="/WEB-INF/pages/include/taglib.jsp"%>
<spring:message code="lable.form.accessDenied.message" var="message" />
<spring:message code="lable.form.accessDenied.username" var="usernameLable" />
<spring:message code="lable.form.accessDenied.title" var="title" />

<html>
<body>
    <h1>${title}</h1>

    <c:choose>
        <c:when test="${empty username}">
            <h2>${message}</h2>
        </c:when>
        <c:otherwise>
            <h2>
                ${usernameLable} : ${username} <br /> ${message}
            </h2>
        </c:otherwise>
    </c:choose>

</body>
</html>
