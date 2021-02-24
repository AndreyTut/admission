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
<jsp:include page="/WEB-INF/jsp/fragments/userheader.jsp"/>

<div class="container">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <form method="post" action="${pageContext.request.contextPath}/command/user/profile">
                <input type="hidden" value="${student!=null?student.id:sessionScope.get("user").id}" name="id">
                <input type="hidden" value="${student!=null?student.password:sessionScope.get("user").password}"
                       name="password">
                <input type="hidden" value="${student!=null?student.email:sessionScope.get("user").email}" name="email">
                <input type="hidden" value="user" name="formname">

                <div class="row text-center">
                    <div class="col-md-6 col-md-offset-3">
                        <h3>
                            <em>
                                <span><fmt:message
                                        key="student.personalinfo"/> </span>
                            </em>
                        </h3>
                    </div>
                </div>
                <br>
                <hr>
                <div class="row info">
                    <div class="col-md-3 col-md-offset-1">

                        <div class="form-group">
                            <c:if test="${lastNameerror}">
                                <div style="color: red">
                                    <span><fmt:message key="error.field"/> </span>
                                </div>
                            </c:if>
                            <label class="control-label"><fmt:message
                                    key="inputform.lastname"/> </label>
                            <input type="text" name="lastName" id="lastname"
                                   value="${student!=null?student.lastName:pageContext.request.getParameter("lastName")}"
                                   class="form-control"
                                   placeholder="Last name">
                        </div>
                    </div>

                    <div class="col-md-3 col-md-offset-1">
                        <div class="form-group">
                            <c:if test="${firstNameerror}">
                                <div style="color: red">
                                    <span><fmt:message key="error.field"/> </span>
                                </div>
                            </c:if>
                            <label class="control-label"><fmt:message key="inputform.firstname"/> </label>
                            <input name="firstName"
                                   value="${student!=null?student.firstName:pageContext.request.getParameter("firstName")}"
                                   id="firstname" type="text"
                                   class="form-control" placeholder="First name">
                        </div>
                    </div>

                    <div class="col-md-3 col-md-offset-1">
                        <div class="form-group">
                            <c:if test="${patronymicerror}">
                                <div style="color: red">
                                    <span><fmt:message key="error.field"/> </span>
                                </div>
                            </c:if>
                            <label class="control-label"><fmt:message
                                    key="inputform.patronymic"/></label>
                            <input name="patronymic"
                                   value="${student!=null?student.patronymic:pageContext.request.getParameter("patronymic")}"
                                   id="patronymic" type="text"
                                   class="form-control" placeholder="Patronymic">
                        </div>
                    </div>
                </div>
                <br>
                <div class="row info">
                    <div class="col-md-3 col-md-offset-1">
                        <div class="form-group">
                            <c:if test="${cityerror}">
                                <div style="color: red">
                                    <span><fmt:message key="error.field"/> </span>
                                </div>
                            </c:if>
                            <label class="control-label"><fmt:message
                                    key="inputform.city"/> </label>
                            <input name="city"
                                   value="${student!=null?student.city:pageContext.request.getParameter("city")}"
                                   id="city" type="text"
                                   class="form-control">
                        </div>
                    </div>

                    <div class="col-md-3 col-md-offset-1">
                        <div class="form-group">
                            <c:if test="${regionerror}">
                                <div style="color: red">
                                    <span><fmt:message key="error.field"/> </span>
                                </div>
                            </c:if>
                            <label class="control-label"><fmt:message
                                    key="inputform.region"/> </label>
                            <input name="region"
                                   value="${student!=null?student.region:pageContext.request.getParameter("region")}"
                                   id="region" type="text"
                                   class="form-control">
                        </div>
                    </div>

                    <div class="col-md-3 col-md-offset-1">
                        <div class="form-group">
                            <label class="control-label"><fmt:message
                                    key="student.view.school"/> </label>
                            <input name="schoolName"
                                   value="${student!=null?student.schoolName:pageContext.request.getParameter("schoolName")}"
                                   id="schoolName" type="text"
                                   class="form-control">
                        </div>

                    </div>

                </div>
                <br>
                <div class="row info">

                    <div class="col-md-4 col-md-offset-1">
                        <button type="submit" class="btn btn-info"><fmt:message key="student.save.changes"/></button>
                    </div>

                </div>
            </form>
            <hr>

            <form action="${pageContext.request.contextPath}/command/user/diploma" method="post">
                <input name="id" type="hidden" value="${diploma==null?null:diploma.id}">
                <input type="hidden" value="diploma" name="formname">
                <div class="row text-center">
                    <div class="col-md-6 col-md-offset-3">
                        <h3>
                            <em>
                                <span><fmt:message key="student.diploma"/> </span>
                            </em>
                        </h3>
                    </div>
                </div>
                <br>
                <hr>

                <div class="row">
                    <div class="col-md-3 col-md-offset-1">
                        <div class="form-group">
                            <label class="control-label"><fmt:message key="student.diploma.math"/> </label>
                            <input name="math" value="${diploma!=null?diploma.math:''}" id="math" type="number"
                                   min="0" max="12" class="form-control">

                        </div>
                    </div>
                    <div class="col-md-3 col-md-offset-1">
                        <div class="form-group">
                            <label class="control-label"><fmt:message key="student.diploma.physics"/> </label>
                            <input name="physics" value="${diploma!=null?diploma.physics:''}" id="physics"
                                   type="number" min="0" max="12"
                                   class="form-control">
                        </div>
                    </div>

                    <div class="col-md-3 col-md-offset-1">
                        <div class="form-group">
                            <label class="control-label"><fmt:message key="student.diploma.history"/> </label>
                            <input name="history" value="${diploma!=null?diploma.history:''}" id="history"
                                   type="number" min="0" max="12"
                                   class="form-control">
                        </div>
                    </div>

                </div>

                <div class="row">
                    <div class="col-md-3 col-md-offset-1">
                        <div class="form-group">
                            <label class="control-label"><fmt:message key="student.diploma.literature"/> </label>
                            <input name="literature" value="${diploma!=null?diploma.literature:''}"
                                   id="literature" type="number" min="0" max="12"
                                   class="form-control">

                        </div>
                    </div>
                    <div class="col-md-3 col-md-offset-1">
                        <div class="form-group">
                            <label class="control-label"><fmt:message key="student.diploma.chemistry"/> </label>
                            <input name="chemistry" value="${diploma!=null?diploma.chemistry:''}" id="chemistry"
                                   type="number" min="0" max="12"
                                   class="form-control">
                        </div>
                    </div>

                    <div class="col-md-3 col-md-offset-1">
                        <div class="form-group">
                            <label class="control-label"><fmt:message key="student.diploma.biology"/> </label>
                            <input name="biology" value="${diploma!=null?diploma.biology:''}" id="biology"
                                   type="number" min="0" max="12"
                                   class="form-control">

                        </div>
                    </div>

                </div>
                <br>
                <div class="row info">

                    <div class="col-md-4 col-md-offset-1">
                        <button type="submit" class="btn btn-info"><fmt:message key="student.save.changes"/></button>
                    </div>

                </div>
            </form>
            <hr>
            <div class="row text-center">
                <div class="col-md-6 col-md-offset-3">
                    <h3>
                        <em>
                            <span><fmt:message key="diploma.image"/> </span>
                        </em>
                    </h3>
                </div>
            </div>

            <div class="row">
                <div class="col-md-10 col-md-offset-0">
                    <div class="text-center">
                        <a href="${pageContext.request.contextPath}/command/user/setimage">
                            <img class="img-fluid"
                                 src="data:image/jpg;base64,${stringimage}"
                                 alt="No photo"/>
                        </a>
                    </div>
                </div>

                <div class="col-md-2 col-md-offset-0">
                    <c:if test="${student.faculties!=null}">
                        <div>
                            <div class="row text-center">
                                <h5><em><span><fmt:message key="student.faculties"/> </span></em></h5>
                            </div>
                            <ul>
                                <c:forEach var="faculty" items="${student.faculties}">
                                    <li>
                                        <c:if test="${sessionScope.lang=='ua'}">
                                            <span>${faculty.nameUa}</span>
                                        </c:if>
                                        <c:if test="${sessionScope.lang!='ua'}">
                                            <span>${faculty.nameEn}</span>
                                        </c:if>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </c:if>

                    <div>
                        <a href="${pageContext.request.contextPath}/command/user/addfaculty">
                            <span><fmt:message key="faculty.add"/> </span>
                            <img src="${pageContext.request.contextPath}/resources/images/add.png"/>
                        </a>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
