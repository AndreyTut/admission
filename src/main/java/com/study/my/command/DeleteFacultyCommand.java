package com.study.my.command;

import com.study.my.service.FacultyService;

import javax.servlet.http.HttpServletRequest;

public class DeleteFacultyCommand implements Command {
    private FacultyService facultyService;

    public DeleteFacultyCommand(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        facultyService.deletefaculty(id);
        return "redirect:/admin/faculties";
    }
}
