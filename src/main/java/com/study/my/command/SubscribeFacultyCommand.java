package com.study.my.command;

import com.study.my.model.Faculty;
import com.study.my.model.StudentMark;
import com.study.my.model.User;
import com.study.my.service.FacultyService;
import com.study.my.service.UserService;
import com.study.my.util.Utils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SubscribeFacultyCommand implements Command {
    private UserService userService;
    private FacultyService facultyService;
    private static final Logger LOGGER = Logger.getLogger(SubscribeFacultyCommand.class);

    public SubscribeFacultyCommand(UserService userService, FacultyService facultyService) {
        this.facultyService = facultyService;
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String id = request.getParameter("id");
        if (id == null || "".equals(id)) {
            List<Faculty> faculties = facultyService.getAll();
            String sortparam = request.getParameter("sortparam");
            String locale = request.getParameter("loc");
            faculties.sort(Utils.getComparator(sortparam, locale));

            request.setAttribute("faculties", faculties);
            return "/WEB-INF/jsp/userfaculties.jsp";
        }

        Faculty faculty = facultyService.getById(Integer.parseInt(id));
        request.setAttribute("faculty", faculty);
        User user = (User) request.getSession().getAttribute("user");
        LOGGER.debug("get user from session: " + user);
        StudentMark mark1 = userService.getStudentMark(faculty.getSubjects().get(0).getId(), user.getId());
        mark1.setSubject(faculty.getSubjects().get(0));
        StudentMark mark2 = userService.getStudentMark(faculty.getSubjects().get(1).getId(), user.getId());
        mark2.setSubject(faculty.getSubjects().get(1));
        request.setAttribute("mark1", mark1);
        request.setAttribute("mark2", mark2);
        return "/WEB-INF/jsp/submitfaculty.jsp";
    }
}
