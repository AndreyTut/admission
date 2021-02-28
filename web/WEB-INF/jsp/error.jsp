<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <head>
        <title>Error</title>
        <link href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.4-dist/css/bootstrap.min.css"
              rel="stylesheet" media="screen"/>

        <script src="${pageContext.request.contextPath}/resources/js/jquery-2.1.4.min.js"></script>

        <link href="${pageContext.request.contextPath}/resources/css/custom.css" rel="stylesheet" media="screen"/>
    </head>
</head>
<body>
<h1>Error</h1>
<jsp:text>${errormessage}</jsp:text>
</body>
</html>
