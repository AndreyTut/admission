<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html lang="${sessionScope.lang}">
<html>
<head>
    <title><fmt:message key="header.login"/></title>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.4-dist/css/bootstrap.min.css"
          rel="stylesheet" media="screen"/>

    <script src="${pageContext.request.contextPath}/resources/js/jquery-2.1.4.min.js"></script>

    <link href="${pageContext.request.contextPath}/resources/css/custom.css" rel="stylesheet" media="screen"/>
</head>
<body>

<jsp:include page="/WEB-INF/jsp/fragments/commonheader.jsp"/>

<div class="container">
    <div class="row">
        <div class="col-md-5 col-md-offset-4">
            <h2><fmt:message key="header.login"/></h2>
            <div>
                <form class="form-horizontal" action="${pageContext.request.contextPath}/command/login" method="post">

                    <div class="form-group">
                        <label class="control-label"><fmt:message key="inputform.email"/></label>
                        <input type="text" class="form-control" name="email" id="email" list="useremail"/>
                    </div>
                    <div class="form-group">
                        <label class="control-label"><fmt:message key="inputform.password"/></label>
                        <input type="password" class="form-control" name="password" id="password"/>
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>
            </div>
        </div>
    </div>
</div>
<datalist id="useremail">
    <option>admin@gmail.com</option>
    <option>admin@user.net</option>
    <option>user@ukr.net</option>
    <option>user@user.net</option>
</datalist>
</body>
</html>
