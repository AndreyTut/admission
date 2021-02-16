package com.study.my.filter;

import com.study.my.model.Role;

import javax.servlet.*;
import java.io.IOException;

public class UserAuthFilter extends AbstractAuthFilter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filter(servletRequest, servletResponse, filterChain, s -> s.contains(Role.ROLE_USER));
    }
}
