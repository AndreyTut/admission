package com.study.my.command;

import com.study.my.model.Diploma;
import com.study.my.model.User;
import com.study.my.service.DiplomaService;
import com.study.my.service.FacultyService;
import com.study.my.service.UserService;
import com.study.my.util.Constants;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import java.util.Base64;

import static com.study.my.util.Constants.*;
import static com.study.my.util.Constants.SUBJ_BIOLOGY;
import static com.study.my.util.Utils.putStudentToRequest;

public class DiplomaCommand implements Command {
    private UserService userService;
    private DiplomaService diplomaService;
    private FacultyService facultyService;
    private static final Logger LOGGER = Logger.getLogger(DiplomaCommand.class);

    public DiplomaCommand(UserService userService, DiplomaService diplomaService, FacultyService facultyService) {
        this.userService = userService;
        this.diplomaService = diplomaService;
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        if ("GET".equals(request.getMethod())) {
            User fullStudent = userService.getFullStudent(((User) (request.getSession().getAttribute("user"))).getId());
            putStudentToRequest(fullStudent, request);
            return "/WEB-INF/jsp/user.jsp";
        }
        int math = Integer.parseInt(request.getParameter(SUBJ_MATH));
        int physics = Integer.parseInt(request.getParameter(SUBJ_PHYSICS));
        int history = Integer.parseInt(request.getParameter(SUBJ_HISTORY));
        int literature = Integer.parseInt(request.getParameter(SUBJ_LITERATURE));
        int chemistry = Integer.parseInt(request.getParameter(SUBJ_CHEMISTRY));
        int biology = Integer.parseInt(request.getParameter(SUBJ_BIOLOGY));
        String parameterId = request.getParameter(ID_FIELD);

        Integer id = (parameterId == null || "".equals(parameterId)) ? null : Integer.parseInt(parameterId);
        Diploma diploma = Diploma.builder()
                .id(id)
                .literature(literature)
                .history(history)
                .physics(physics)
                .math(math)
                .chemistry(chemistry)
                .biology(biology)
                .userId(((User) request.getSession().getAttribute("user")).getId())
                .build();
        diplomaService.save(diploma);
        LOGGER.debug("Sending diploma form: " + diploma);

        User fullStudent = userService.getFullStudent(diploma.getUserId());
        fullStudent.setFaculties(facultyService.getUserFaculty(fullStudent.getId()));
        putStudentToRequest(fullStudent, request);
        return "/WEB-INF/jsp/user.jsp";
    }
}
