<%@ include file="/WEB-INF/pages/include/taglib.jsp" %>

<%-- <c:redirect url="/login"/> --%>

<c:choose>
    <c:when test="${pageContext.request.userPrincipal.name != null}">
        <c:redirect url="/home"/>
    </c:when>
    <c:otherwise>
        <c:redirect url="/login"/>
    </c:otherwise>
</c:choose>