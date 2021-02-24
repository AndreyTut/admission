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

<jsp:include page="/WEB-INF/jsp/fragments/userheader.jsp"/>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <div class="row text-center">
                <h3><fmt:message key="student.entermarks"/></h3>
                <hr>
                <form method="post" action="${pageContext.request.contextPath}/command/user/faculty/submit">
                    <input type="hidden" name="facultyid" value="${faculty.id}">
                    <div>
                        <c:if test="${sessionScope.lang=='ua'}">
                            <div>
                                <label class="control-label">
                                        ${mark1.subject.nameUa}
                                </label>
                            </div>
                        </c:if>
                        <c:if test="${sessionScope.lang!='ua'}">
                            <div>
                                <label class="control-label">
                                        ${mark1.subject.nameEn}
                                </label>
                            </div>
                        </c:if>
                        <input type="hidden" name="mark1id" value="${mark1.id}">
                        <input type="hidden" name="subj1id" value="${mark1.subject.id}">

                        <input name="mark1newvalue" value="${mark1.mark}" type="number" min="0" max="200"
                               class="form-control">
                    </div>

                    <div>
                        <c:if test="${sessionScope.lang=='ua'}">
                            <div>
                                <label class="control-label">
                                        ${mark2.subject.nameUa}
                                </label>
                            </div>
                        </c:if>
                        <c:if test="${sessionScope.lang!='ua'}">
                            <div>
                                <label class="control-label">
                                        ${mark2.subject.nameEn}
                                </label>
                            </div>
                        </c:if>
                        <input type="hidden" name="mark2id" value="${mark2.id}">
                        <input type="hidden" name="subj2id" value="${mark2.subject.id}">

                        <input name="mark2newvalue" value="${mark2.mark}" type="number" min="0" max="200"
                               class="form-control">
                    </div>

                    <br>
                    <div class="col-md-4">
                        <button type="submit" class="btn btn-info"><fmt:message key="inputform.save"/></button>
                    </div>

                </form>
            </div>

        </div>

    </div>
</div>
</div>
</body>
</html>
