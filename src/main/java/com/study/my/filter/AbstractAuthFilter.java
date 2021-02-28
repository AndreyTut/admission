package com.study.my.filter;

import com.study.my.model.Role;
import com.study.my.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;
import java.util.function.Predicate;

public abstract class AbstractAuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    protected void filter(ServletRequest servletRequest, ServletResponse servletResponse,
                          FilterChain filterChain, Predicate<Set<Role>> predicate) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(servletRequest, servletResponse);
        }
        Set<Role> roles = user.getRoles();
        if (!predicate.test(roles)) {
            request.setAttribute("errormessage", "Forbidden for your role");
            request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(servletRequest, servletResponse);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
