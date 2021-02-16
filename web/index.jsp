<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html lang="${sessionScope.lang}">
<head>
    <title>Main</title>
</head>
<body>

<c:if test="${sessionScope.user==null}">
    <jsp:include page="/WEB-INF/jsp/fragments/commonheader.jsp"/>
</c:if>

<c:if test="${sessionScope.isAdmin}">
    <jsp:include page="/WEB-INF/jsp/fragments/adminheader.jsp"/>
</c:if>

<c:if test="${sessionScope.isUser}">
    <jsp:include page="/WEB-INF/jsp/fragments/userheader.jsp"/>
</c:if>

<div>
    <a href="?lang=en">English</a>
    <a href="?lang=ua">Українська</a>
</div>
<p><a href="${pageContext.request.contextPath}/command/user">User</a></p>
<p><a href="${pageContext.request.contextPath}/command/admin">Admin</a></p>
<p><a href="${pageContext.request.contextPath}/command/login"><fmt:message key="header.login"/></a></p>
<p><a href="${pageContext.request.contextPath}/command/logout"><fmt:message key="header.logout"/></a></p>
<p><a href="${pageContext.request.contextPath}/command/registration"><fmt:message key="header.registration"/></a></p>
</body>
</html>
