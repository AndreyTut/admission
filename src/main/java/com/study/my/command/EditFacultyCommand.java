package com.study.my.command;

import com.study.my.model.Faculty;
import com.study.my.model.Subject;
import com.study.my.service.FacultyService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class EditFacultyCommand implements Command {
    private FacultyService facultyService;

    public EditFacultyCommand(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String strId = request.getParameter("id");
        if (strId != null) {
            int id = Integer.parseInt(strId);
            Faculty faculty = facultyService.getById(id);
            request.setAttribute("faculty", faculty);
        }
        List<Subject> subjects = facultyService.getAllSubjects();
        request.setAttribute("subjects", subjects);
        return "/WEB-INF/jsp/addeditfaculty.jsp";
    }
}
