<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Storage</title>

    <link href="http://cdn.jsdelivr.net/webjars/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet" media="screen"
          th:href="@{/css/bootstrap-3.3.4-dist/css/bootstrap.min.css}"/>

    <script src="http://cdn.jsdelivr.net/webjars/jquery/2.1.4/jquery.min.js"
            th:src="@{/js/jquery-2.1.4.min.js}"></script>

</head>
<body>
<!--/*@thymesVar id="item" type="com.example.sklad.model.Item"*/-->
<div class="container-fluid" style="margin-top: 20px">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="pannel-group">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h1 class="panel-title"><fmt:message key="file.upload"/></h1>
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-5">
                                <form action="${pageContext.request.contextPath}/command/user/setimage" method="post"
                                      enctype="multipart/form-data">
                                    <label class="control-label"><fmt:message
                                            key="file.select"/> </label>
                                    <input id="imagefile" name="file" type="file" class="file">
                                    <button type="submit" class="btn btn-primary">Submit</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>