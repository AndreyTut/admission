<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html lang="${sessionScope.lang}">
<head>
    <title>Faculties</title>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.4-dist/css/bootstrap.min.css"
          rel="stylesheet" media="screen"/>

    <script src="${pageContext.request.contextPath}/resources/js/jquery-2.1.4.min.js"></script>

    <link href="${pageContext.request.contextPath}/resources/css/custom.css" rel="stylesheet" media="screen"/>
</head>
<body>

<jsp:include page="/WEB-INF/jsp/fragments/adminheader.jsp"/>
<body>
<div style="margin: 20px">
    <div class="row">
        <div class="col-md-2 col-md-offset-10">
            <form action="${pageContext.request.contextPath}/command/admin/faculties" method="get">
                <input type="hidden" name="loc" value="${sessionScope.lang}">
                <div class="form-group">
                    <label for="sortparam">
                        <fmt:message key="faculty.sortby"/>
                    </label>
                    <select name="sortparam" id="sortparam">
                        <option value="name">
                            <fmt:message key="faculty.sort.name"/>
                        </option>
                        <option value="budg">
                            <fmt:message key="faculty.sort.budget"/>
                        </option>
                        <option value="contr">
                            <fmt:message key="faculty.sort.contr"/>
                        </option>
                    </select>

                </div>
                <button type="submit" class="btn btn-info">
                    <fmt:message key="faculty.sort"/>
                </button>
            </form>
        </div>
    </div>
    <table class="table table-striped">
        <thead>
        <tr>

            <th>
                <fmt:message key="table.faculty.header.name"/>
            </th>
            <th>
                <fmt:message key="table.faculty.header.vacanciesnum"/>
            </th>
            <th>
                <fmt:message key="table.faculty.header.vacanciescontrnum"/>
            </th>
            <th>
                <fmt:message key="table.faculty.header.edit"/>
            </th>
            <th>
                <fmt:message key="table.faculty.header.delete"/>
            </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${faculties}" var="faculty">
            <tr>
                <c:if test="${sessionScope.lang=='ua'}">
                    <td>${faculty.nameUa}</td>
                </c:if>
                <c:if test="${sessionScope.lang=='en'}">
                    <td>${faculty.nameEn}</td>
                </c:if>
                <c:if test="${sessionScope.lang!='en'&&sessionScope.lang!='ua'}">
                    <td>${faculty.nameEn}</td>
                </c:if>
                <td>${faculty.vacancyBudge}</td>
                <td>${faculty.vacancyContr}</td>
                <td><a href="${pageContext.request.contextPath}/command/admin/faculty/edit?id=${faculty.id}">
                    <img src="${pageContext.request.contextPath}/resources/images/pencil.png"/>
                </a></td>
                <td><a href="${pageContext.request.contextPath}/command/admin/faculty/delete?id=${faculty.id}">
                    <img src="${pageContext.request.contextPath}/resources/images/delete.png"/>
                </a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div>
        <a href="${pageContext.request.contextPath}/command/admin/faculty/add">
            <span><fmt:message key="faculty.add"/></span>
            <img src="${pageContext.request.contextPath}/resources/images/add.png"/>
        </a>
    </div>

</div>
</body>
</html>
