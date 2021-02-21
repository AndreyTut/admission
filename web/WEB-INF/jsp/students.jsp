<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html lang="${sessionScope.lang}">
<head>
    <title>Students</title>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap-3.3.4-dist/css/bootstrap.min.css"
          rel="stylesheet" media="screen"/>

    <script src="${pageContext.request.contextPath}/resources/js/jquery-2.1.4.min.js"></script>

    <link href="${pageContext.request.contextPath}/resources/css/custom.css" rel="stylesheet" media="screen"/>
</head>
<body>

<jsp:include page="/WEB-INF/jsp/fragments/adminheader.jsp"/>
<body>
<div style="margin: 20px">
    <table class="table table-striped">
        <thead>
        <tr>
            <th><fmt:message key="table.header.name"/></th>
            <th><fmt:message key="table.header.email"/></th>
            <th><fmt:message key="table.header.cityregion"/></th>
            <th><fmt:message key="table.header.view"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="student" items="${students}">
            <tr class="${student.isEnabled()?'':'danger'}">
                <td><c:out value="${student.getFullName()}"/></td>
                <td><c:out value="${student.email}"/></td>
                <td><c:out value="${student.city}, ${student.region} "/><fmt:message key="table.cell.reg"/></td>
                <td><a href="${pageContext.request.contextPath}/command/admin/student?id=${student.id}">
                    <fmt:message key="table.header.view"/>
                </a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <nav aria-label="Page navigation example">
        <ul class="pagination">
            <li class="page-item">
                <c:if test="${page>0}">
                    <a class="page-link"
                       href="${pageContext.request.contextPath}/command/admin/students?page=${page-1}">Previous</a>
                </c:if>
                <c:if test="${page==0}">
                    <span class="page-link">Previous</span>
                </c:if>
            </li>
            <li class="page-item">
                <c:if test="${page<pages-1}">
                    <a class="page-link"
                       href="${pageContext.request.contextPath}/command/admin/students?page=${page+1}">Next</a>
                </c:if>
            </li>
            <li class="page-item">
                <c:if test="${page==pages-1}">
                    <span class="page-link">Next</span>
                </c:if>
            </li>
        </ul>

    </nav>
</div>
</body>
</html>
