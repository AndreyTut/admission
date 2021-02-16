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
    <title><fmt:message key="header.registration"/></title>
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
            <h2><fmt:message key="header.registration"/></h2>
            <div>
                <form class="form-horizontal" action="${pageContext.request.contextPath}/command/registration"
                      method="post"
                      enctype="multipart/form-data">

                    <div class="form-group">
                        <c:if test="${emailerror}">
                            <div style="color: red">
                                <span><fmt:message key="error.field"/> </span>
                            </div>
                        </c:if>
                        <label class="control-label" for="email"><fmt:message key="inputform.email"/> </label>
                        <input type="text" class="form-control" value="${pageContext.request.getParameter("email")}"
                               name="email" id="email" list="emails"/>
                    </div>

                    <div class="form-group">
                        <label class="control-label" for="password"><fmt:message key="inputform.password"/>
                        </label>
                        <input type="password" class="form-control" name="password" id="password"/>
                    </div>


                    <div class="form-group">
                        <c:if test="${firstNameerror}">
                            <div style="color: red">
                                <span><fmt:message key="error.field"/> </span>
                            </div>
                        </c:if>
                        <label class="control-label" for="firstName"><fmt:message key="inputform.firstname"/></label>
                        <input type="text" value="${pageContext.request.getParameter("firstName")}" class="form-control"
                               name="firstName" id="firstName"
                               list="firstnames"/>
                    </div>

                    <div class="form-group">
                        <c:if test="${patronymicerror}">
                            <div style="color: red">
                                <span><fmt:message key="error.field"/> </span>
                            </div>
                        </c:if>
                        <label class="control-label" for="patronymic"><fmt:message key="inputform.patronymic"/></label>
                        <input type="text" value="${pageContext.request.getParameter("patronymic")}"
                               class="form-control" name="patronymic" id="patronymic"
                               list="patronymics"/>
                    </div>

                    <div class="form-group">
                        <c:if test="${lastNameerror}">
                            <div style="color: red">
                                <span><fmt:message key="error.field"/> </span>
                            </div>
                        </c:if>
                        <label class="control-label" for="lastName"><fmt:message key="inputform.lastname"/></label>
                        <input type="text" value="${pageContext.request.getParameter("lastName")}" class="form-control"
                               name="lastName" id="lastName"
                               list="lastnames"/>
                    </div>


                    <div class="form-group">
                        <c:if test="${cityerror}">
                            <div style="color: red">
                                <span><fmt:message key="error.field"/> </span>
                            </div>
                        </c:if>
                        <label class="control-label" for="city"><fmt:message key="inputform.city"/></label>
                        <input type="text" value="${pageContext.request.getParameter("city")}" list="cities"
                               class="form-control" name="city" id="city"/>
                    </div>

                    <div class="form-group">
                        <c:if test="${regionerror}">
                            <div style="color: red">
                                <span><fmt:message key="error.field"/> </span>
                            </div>
                        </c:if>
                        <label class="control-label" for="region"><fmt:message key="inputform.region"/></label>
                        <input type="text" value="${pageContext.request.getParameter("region")}" list="regs" class="form-control" name="region" id="region"/>
                    </div>

                    <div class="form-group">
                        <label class="control-label" for="schoolName"><fmt:message
                                key="inputform.schoolname"/></label>
                        <input type="text" value="${pageContext.request.getParameter("schoolName")}" class="form-control" name="schoolName" id="schoolName"
                               list="schools"/>
                    </div>
                    <div class="form-group">
                        <label class="control-label"><fmt:message key="inputform.diplomascan"/></label>
                        <input type="file" value="${pageContext.request.getParameter("file")}" name="file" accept="image/x-png,image/jpeg,image/jpg">
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-default">Submit</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<datalist id="emails">
    <option>user@user.net</option>
    <option>user1@user.net</option>
    <option>user2@user.net</option>
    <option>user3@user.net</option>
</datalist>

<datalist id="firstnames">
    <option>Іван</option>
    <option>Григорій</option>
    <option>Олександр</option>
    <option>Олексій</option>
</datalist>

<datalist id="lastnames">
    <option>Деркач</option>
    <option>Завгородній</option>
    <option>Хмара</option>
    <option>Горліс</option>
</datalist>

<datalist id="patronymics">
    <option>Миколайович</option>
    <option>Васильовоч</option>
    <option>Семенович</option>
    <option>Вікторович</option>
</datalist>

<datalist id="cities">
    <option>Київ</option>
    <option>Вінниця</option>
    <option>Черкаси</option>
    <option>Львів</option>
</datalist>

<datalist id="regs">
    <option>Київська</option>
    <option>Вінницька</option>
    <option>Черкаська</option>
    <option>Львівська</option>
</datalist>

<datalist id="schools">
    <option>Школа №12</option>
    <option>Гімназія №1</option>
    <option>Технікум радіоелектроніки</option>
    <option>Колледж №14</option>
</datalist>
</body>
</html>