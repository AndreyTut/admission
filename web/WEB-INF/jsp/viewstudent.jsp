<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html lang="${sessionScope.lang}">
<head>
    <title>Student</title>
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
                <a href="${pageContext.request.contextPath}/command/admin/students"><fmt:message
                        key="header.studentes"/>
            </div>
            <div class="col-md-1 col-md-offset-1 navbar-brand">
                <a href="${pageContext.request.contextPath}/command/admin/faculties"><fmt:message
                        key="header.Faculties"/>
            </div>

            <div class="col-md-1 col-md-offset-1 navbar-brand">
                <a href="${pageContext.request.contextPath}/command/logout"><fmt:message key="header.logout"/></a>
            </div>

            <div class="col-md-1 col-md-offset-4 navbar-brand">
                <a href="?id=${student.id}&lang=ua">Українська</a>
            </div>

            <div class="col-md-1 navbar-brand">
                <a href="?id=${student.id}&lang=en">English</a>
            </div>

        </div>
    </nav>
</div>


<div class="container-fluid" style="margin-top: 20px">

    <c:if test="${student.status==1}">
        <div class="col-md-10 col-md-offset-1 alert alert-success">
            <span><fmt:message key="student.accepted.budget"/></span>
            <c:if test="${sessionScope.lang=='ua'}">
                ${student.faculty.nameUa}
            </c:if>
            <c:if test="${sessionScope.lang!='ua'}">
                ${student.faculty.nameEn}
            </c:if>
        </div>
    </c:if>
    <c:if test="${student.status==2}">
        <div class="col-md-10 col-md-offset-1 alert alert-success">
            <span><fmt:message key="student.accepted.contract"/></span>
            <c:if test="${sessionScope.lang=='ua'}">
                ${student.faculty.nameUa}
            </c:if>
            <c:if test="${sessionScope.lang!='ua'}">
                ${student.faculty.nameEn}
            </c:if>
        </div>
    </c:if>
    <c:if test="${student.status==3}">
        <div class="col-md-10 col-md-offset-1 alert alert-danger">
            <span><fmt:message key="student.failed"/></span>
            <c:if test="${sessionScope.lang=='ua'}">
                ${student.faculty.nameUa}
            </c:if>
            <c:if test="${sessionScope.lang!='ua'}">
                ${student.faculty.nameEn}
            </c:if>
        </div>
    </c:if>

    <div class="row">
        <div class="col-sm-1 col-md-offset-10">
            <form action="${pageContext.request.contextPath}/command/admin/student/changeactive?id=${student.id}"
                  method="post">
                <button type="submit" class="btn btn-info">
                    <c:if test="${student.enabled}">
                        <fmt:message key="student.block"/>
                    </c:if>
                    <c:if test="${!student.enabled}">
                        <fmt:message key="student.deblock"/>
                    </c:if>
                </button>
                <input type="hidden" name="enabled" value="${!student.isEnabled()}">
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-6 col-md-offset-3">
            <table class="table-striped ">
                <thead>
                <tr>
                    <th>
                        <h3><fmt:message key="student.view.title"/></h3>
                    </th>
                    <th>
                    </th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><fmt:message key="student.view.name"/></td>
                    <td>${student.lastName} ${student.firstName} ${student.patronymic}
                    </td>
                </tr>
                <tr>
                    <td><fmt:message key="student.view.from"/></td>
                    <td>${student.city}, ${student.region}
                        <fmt:message key="table.cell.reg"/>
                    </td>
                </tr>
                <tr>
                    <td><fmt:message key="student.view.school"/></td>
                    <td>${student.schoolName}
                    </td>
                </tr>
                <tr>
                    <td><fmt:message key="student.view.email"/></td>
                    <td>${student.email}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <c:if test="${student.diploma!=null}">
        <div class="row">
            <div class="col-sm-6 col-lg-offset-3">
                <table class="table-striped">
                    <thead>
                    <th>
                        <h4><fmt:message key="student.view.diploma"/></h4>
                    </th>
                    <th>
                    </th>
                    </thead>
                    <tbody>
                    <tr>
                        <td><fmt:message key="student.diploma.math"/></td>
                        <td>${student.diploma.math}</td>
                    </tr>
                    <tr>
                        <td><fmt:message key="student.diploma.physics"/></td>
                        <td>${student.diploma.physics}</td>
                    </tr>
                    <tr>
                        <td><fmt:message key="student.diploma.biology"/></td>
                        <td>${student.diploma.biology}</td>
                    </tr>
                    <tr>
                        <td><fmt:message key="student.diploma.history"/></td>
                        <td>${student.diploma.history}</td>
                    </tr>
                    <tr>
                        <td><fmt:message key="student.diploma.chemistry"/></td>
                        <td>${student.diploma.chemistry}</td>
                    </tr>
                    <tr>
                        <td><fmt:message key="student.diploma.literature"/></td>
                        <td>${student.diploma.literature}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </c:if>
    <c:if test="${student.diploma==null}">
        <span><fmt:message key="student.diploma.isnull"/> </span>
    </c:if>
    <div class="row text-center">
        <div class="col-sm-8 col-lg-offset-2">
            <h4><fmt:message key="student.diploma.scan"/></h4>
            <img src="data:image/jpg;base64,${stringimage}" class="img-fluid"
                 alt="Student didn't load diploma scan"/>

        </div>
        <div class="col-md-2">
            <c:if test="${student.faculty==null}">
                <h4><fmt:message key="student.faculties"/></h4>
                <ul>
                    <c:forEach items="${student.faculties}" var="faculty">
                        <li>
                        <span>
                        <c:if test="${sessionScope.lang=='ua'}">
                            ${faculty.nameUa}
                        </c:if>
                        <c:if test="${sessionScope.lang!='ua'}">
                            ${faculty.nameEn}
                        </c:if>
                            <a href="${pageContext.request.contextPath}/command/admin/addtoreport?stid=${student.id}&fid=${faculty.id}"><fmt:message
                                    key="student.addtoreport"/> </a>
                        </span>
                        </li>
                    </c:forEach>
                </ul>
            </c:if>

            <c:if test="${student.faculty!=null}">
                <span><fmt:message key="student.inreport"/>
                                        <c:if test="${sessionScope.lang=='ua'}">
                                            ${student.faculty.nameUa}
                                        </c:if>
                        <c:if test="${sessionScope.lang!='ua'}">
                            ${student.faculty.nameEn}
                        </c:if>

                </span>
            </c:if>


        </div>
    </div>
</div>
</body>
</html>
