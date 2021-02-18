package com.study.my.filter;

import javax.servlet.http.HttpServletRequest;

import static com.study.my.util.Constants.*;

public class UserDataValidationFilter extends AbstractValidationFilter {

    @Override
    boolean checkFields(HttpServletRequest request) {
        String schoolName = request.getParameter(SCHOOL_NAME_FIELD);
        return
                hasError(request, FIRST_NAME_FIELD, NAME_REGEX)
                        | hasError(request, LAST_NAME_FIELD, NAME_REGEX)
                        | hasError(request, PATRONYMIC_FIELD, NAME_REGEX)
                        | hasError(request, CITY_FIELD, NAME_REGEX)
                        | hasError(request, REGION_FIELD, NAME_REGEX)
                        | schoolName == null
                        | "".equals(schoolName);

    }

    @Override
    String getPage() {
        return "/WEB-INF/jsp/user.jsp";
    }
}
