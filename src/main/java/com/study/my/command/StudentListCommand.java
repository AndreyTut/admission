package com.study.my.command;

import com.study.my.model.User;
import com.study.my.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class StudentListCommand implements Command {

    private UserService userService;

    public StudentListCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<User> students = userService.getAll();
        return "/WEB-INF/jsp/students.jsp";
    }
}
