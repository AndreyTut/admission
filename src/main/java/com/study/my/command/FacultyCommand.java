package com.study.my.command;

import com.study.my.model.Faculty;
import com.study.my.service.FacultyService;
import com.study.my.util.Utils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FacultyCommand implements Command {
    private FacultyService facultyService;

    public FacultyCommand(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String sortparam = request.getParameter("sortparam");
        String locale = request.getParameter("loc");

        List<Faculty> faculties = facultyService.getAll();
        faculties.sort(Utils.getComparator(sortparam, locale));
        request.setAttribute("faculties", faculties);
        return "/WEB-INF/jsp/faculties.jsp";
    }
}
