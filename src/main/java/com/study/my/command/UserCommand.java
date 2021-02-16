package com.study.my.command;

import com.study.my.model.Role;
import com.study.my.model.User;

import javax.servlet.http.HttpServletRequest;

public class UserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            if (user.getRoles().contains(Role.ROLE_USER)) return "/WEB-INF/jsp/user.jsp";
            if (user.getRoles().contains(Role.ROLE_ADMIN)) return "/WEB-INF/jsp/admin.jsp";
        }
        return "/WEB-INF/jsp/login.jsp";
    }
}
