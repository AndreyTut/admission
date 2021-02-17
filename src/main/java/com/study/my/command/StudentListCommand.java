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
        String page = request.getParameter("page");
        int pageNum = page == null ? 0 : Integer.parseInt(page);
        List<User> allStudents = userService.getAll();

        int studCount = allStudents.size();
        int pagesCount = studCount % 10 == 0 ? studCount / 10 : studCount / 10 + 1;
        List<User> students = allStudents.subList(pageNum * 10, pageNum == pagesCount - 1 ? studCount : pageNum * 10 + 10);
        request.setAttribute("students", students);
        request.setAttribute("pages", pagesCount);
        request.setAttribute("page", pageNum);
        return "/WEB-INF/jsp/students.jsp";
    }
}
