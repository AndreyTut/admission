<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html lang="${sessionScope.lang}">
<head>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.4-dist/css/bootstrap.min.css"
          rel="stylesheet" media="screen"/>

    <script src="${pageContext.request.contextPath}/resources/js/jquery-2.1.4.min.js"></script>

    <link href="${pageContext.request.contextPath}/resources/css/custom.css" rel="stylesheet" media="screen"/>

</head>
<body>
<div class="container-fluid">
    <nav class="navbar navbar-light" style="background-color: #e3f2fd;">
    <div class="row">
            <div class="col-md-1 col-md-offset-1 navbar-brand">
                <a href="${pageContext.request.contextPath}/command/login"><fmt:message
                        key="header.login"/>
            </div>
            <div class="col-md-1 col-md-offset-1 navbar-brand">
                <a href="${pageContext.request.contextPath}/command/registration"><fmt:message
                        key="header.registration"/>
            </div>

            <div class="col-md-1 col-md-offset-5 navbar-brand">
                <a href="?lang=ua">Українська</a>
            </div>

            <div class="col-md-1 navbar-brand">
                <a href="?lang=en">English</a>
            </div>
    </div>
    </nav>
</div>
</body>