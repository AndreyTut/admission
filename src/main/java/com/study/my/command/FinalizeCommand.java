package com.study.my.command;

import com.study.my.service.FacultyService;
import com.study.my.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class FinalizeCommand implements Command {
    private UserService userService;
    private FacultyService facultyService;

    public FinalizeCommand(UserService userService, FacultyService facultyService) {
        this.userService = userService;
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String strId = request.getParameter("id");
        int id = Integer.parseInt(strId);
        facultyService.finalizeReport(id);
        return "redirect:/admin/faculties";
    }
}
