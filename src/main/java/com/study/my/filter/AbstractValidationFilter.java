package com.study.my.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractValidationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if ("POST".equals(request.getMethod())) {
            boolean hasErrors;

            hasErrors = checkFields(request);


            if (hasErrors) {
                request.getRequestDispatcher(getPage()).forward(servletRequest, servletResponse);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    boolean hasError(HttpServletRequest request, String fieldName, String regex) {
        String field = request.getParameter(fieldName);
        if (field == null) {
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(field);
        if (!matcher.matches()) {
            request.setAttribute(fieldName + "error", true);
            return true;
        }
        return false;
    }

    abstract boolean checkFields(HttpServletRequest request);

    abstract String getPage();

}
