package com.study.my.filter;

import javax.servlet.http.HttpServletRequest;

import static com.study.my.util.Constants.*;

public class RegistrationValidationFilter extends AbstractValidationFilter {

    @Override
    boolean checkFields(HttpServletRequest request) {
        return hasError(request, EMAIL_FIELD, EMAIL_REGEX)
                | hasError(request, FIRST_NAME_FIELD, NAME_REGEX)
                | hasError(request, LAST_NAME_FIELD, NAME_REGEX)
                | hasError(request, PATRONYMIC_FIELD, NAME_REGEX)
                | hasError(request, CITY_FIELD, NAME_REGEX)
                | hasError(request, REGION_FIELD, NAME_REGEX);
    }

    @Override
    String getPage() {
        return "/WEB-INF/jsp/registration.jsp";
    }
}
