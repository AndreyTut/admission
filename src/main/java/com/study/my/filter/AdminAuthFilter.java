package com.study.my.filter;

import com.study.my.model.Role;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class AdminAuthFilter extends AbstractAuthFilter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filter(servletRequest, servletResponse, filterChain, s -> s.contains(Role.ROLE_ADMIN));
    }
}
