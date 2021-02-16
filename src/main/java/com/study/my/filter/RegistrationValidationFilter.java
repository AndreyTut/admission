package com.study.my.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.study.my.util.Constants.*;

public class RegistrationValidationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if ("POST".equals(request.getMethod())) {
            boolean hasErrors;

            hasErrors = hasError(request, EMAIL_FIELD, EMAIL_REGEX)
                    | hasError(request, FIRST_NAME_FIELD, NAME_REGEX)
                    | hasError(request, LAST_NAME_FIELD, NAME_REGEX)
                    | hasError(request, PATRONYMIC_FIELD, NAME_REGEX)
                    | hasError(request, CITY_FIELD, NAME_REGEX)
                    | hasError(request, REGION_FIELD, NAME_REGEX);


            if (hasErrors) {
                request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(servletRequest, servletResponse);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    private boolean hasError(HttpServletRequest request, String fieldName, String regex) {
        String field = request.getParameter(fieldName);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(field);
        if (!matcher.matches()) {
            request.setAttribute(fieldName + "error", true);
            return true;
        }
        return false;
    }
}
