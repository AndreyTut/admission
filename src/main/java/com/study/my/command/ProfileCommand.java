package com.study.my.command;

import com.study.my.model.User;
import com.study.my.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class ProfileCommand implements Command {
    private UserService userService;

    public ProfileCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        User student = userService.getFullStudent(user.getId());
        return null;
    }
}
