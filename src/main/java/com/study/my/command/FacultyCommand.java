package com.study.my.command;

import com.study.my.model.Faculty;
import com.study.my.service.FacultyService;
import com.study.my.util.Utils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FacultyCommand implements Command {
    private FacultyService facultyService;
    private static final Logger LOGGER = Logger.getLogger(FacultyCommand.class);

    public FacultyCommand(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String sortparam = request.getParameter("sortparam");
        String locale = request.getParameter("loc");
        LOGGER.debug("locale: " + locale);
        LOGGER.debug("sortparam: " + sortparam);
        List<Faculty> faculties = facultyService.getAll();
        faculties.sort(Utils.getComparator(sortparam, locale));
        request.setAttribute("faculties", faculties);
        return "/WEB-INF/jsp/faculties.jsp";
    }
}
