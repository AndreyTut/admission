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
                <c:if test="${faculty.id==null}">
                    <a href="?lang=ua">Українська</a>
                </c:if>
                <c:if test="${faculty.id!=null}">
                    <a href="?id=${faculty.id}&lang=ua">Українська</a>
                </c:if>
            </div>

            <div class="col-md-1 navbar-brand">
                <c:if test="${faculty.id==null}">
                    <a href="?lang=en">English</a>
                </c:if>
                <c:if test="${faculty.id!=null}">
                    <a href="?id=${faculty.id}&lang=en">English</a>
                </c:if>
            </div>

        </div>
    </nav>
</div>
<div class="container">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <form method="post" action="${pageContext.request.contextPath}/command/admin/faculty/save">
                <input name="id" type="hidden" value="${faculty.id}">
                <div class="row text-center">
                    <h3>
                        <em>
                            <c:if test="${faculty.id==null}">
                                <span><fmt:message key="faculty.add"/></span>
                            </c:if>
                            <c:if test="${faculty.id!=null}">
                                <span><fmt:message key="faculty.edit"/></span>
                            </c:if>
                        </em>
                    </h3>
                    <br>
                    <br>
                </div>

                <div class="row">
                    <div class="col-md-4 col-md-offset-1">
                        <div class="form-group">
                            <label class="control-label"><fmt:message key="inputform.nameEn"/></label>
                            <input type="text" name="nameEn" id="nameEn" value="${faculty.nameEn}"
                                   class="form-control">
                        </div>

                    </div>
                    <div class="col-md-4 col-md-offset-1">
                        <div class="form-group">
                            <label class="control-label"><fmt:message key="inputform.nameUa"/> </label>
                            <input type="text" name="nameUa" id="nameUa" value="${faculty.nameUa}"
                                   class="form-control">
                        </div>
                    </div>

                </div>

                <div class="row">
                    <div class="col-md-4 col-md-offset-1">
                        <div class="form-group">
                            <label class="control-label"><fmt:message key="inputform.vacancyBudge"/> </label>
                            <input type="number" min=0 name="vacancyBudge" id="vacancyBudge"
                                   value="${faculty.vacancyBudge}"
                                   class="form-control">
                        </div>
                    </div>

                    <div class="col-md-4 col-md-offset-1">
                        <div class="form-group">
                            <label class="control-label"><fmt:message key="inputform.vacancyContr"/></label>
                            <input type="number" min=0 name="vacancyContr" id="vacancyContr"
                                   value="${faculty.vacancyContr}"
                                   class="form-control">
                        </div>
                    </div>
                </div>
                <hr>

                <div class="row text-center">
                    <h4><p><fmt:message key="inputform.subjects"/></p></h4>
                </div>
                <br>
                <div class="row">

                    <c:if test="${sessionScope.lang=='ua'}">
                        <div>
                            <div class="col-md-4 col-md-offset-1">
                                <div class="form-group">
                                    <select name="subj1" class="form-control">
                                        <c:forEach var="subject" items="${subjects}">
                                            <option ${faculty.subjects[0].id==subject.id?'selected="selected"':''}
                                                    value="${subject.id}">
                                                    ${subject.nameUa}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="col-md-4 col-md-offset-1">
                                <div class="form-group">
                                    <select name="subj2" class="form-control">
                                        <c:forEach var="subject" items="${subjects}">
                                            <option ${faculty.subjects[1].id==subject.id?'selected="selected"':''}
                                                    value="${subject.id}">
                                                    ${subject.nameUa}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </c:if>

                    <c:if test="${sessionScope.lang!='ua'}">
                        <div>
                            <div class="col-md-4 col-md-offset-1">
                                <div class="form-group">
                                    <select name="subj1" class="form-control">
                                        <c:forEach var="subject" items="${subjects}">
                                            <option ${faculty.subjects[0].id==subject.id?'selected="selected"':''}
                                                    value="${subject.id}">
                                                    ${subject.nameEn}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="col-md-4 col-md-offset-1">
                                <div class="form-group">
                                    <select name="subj2" class="form-control">
                                        <c:forEach var="subject" items="${subjects}">
                                            <option ${faculty.subjects[1].id==subject.id?'selected="selected"':''}
                                                    value="${subject.id}">
                                                    ${subject.nameEn}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </c:if>


                </div>

                <div class="row info">

                    <div class="col-md-4 col-md-offset-1">
                        <button type="submit" class="btn btn-info"><fmt:message key="inputform.save"/></button>
                    </div>

                </div>

            </form>
        </div>
    </div>
</div>
</body>
</html>
