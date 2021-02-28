<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
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
<div class="container-fluid">
    <img src="${pageContext.request.contextPath}/resources/images/priemnaia.png" height="533" width="100%"/>
</div>
</body>
</html>
