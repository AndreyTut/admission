package com.study.my.command;

import com.study.my.service.FacultyService;
import com.study.my.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class AddToReportCommand implements Command {
    private UserService userService;
    private FacultyService facultyService;

    public AddToReportCommand(UserService userService, FacultyService facultyService) {
        this.userService = userService;
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Integer studentId = Integer.parseInt(request.getParameter("stid"));
        Integer facultyId = Integer.parseInt(request.getParameter("fid"));
        userService.subscribeOnfaculty(studentId, facultyId);
        return "redirect:/admin/student?id="+studentId;
    }
}
