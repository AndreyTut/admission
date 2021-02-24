package com.study.my.command;

import com.study.my.model.Faculty;
import com.study.my.model.User;
import com.study.my.service.FacultyService;
import com.study.my.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.study.my.util.Constants.*;
import static com.study.my.util.Utils.putStudentToRequest;


public class ProfileCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(ProfileCommand.class);
    private UserService userService;
    private FacultyService facultyService;

    public ProfileCommand(UserService userService, FacultyService facultyService) {
        this.userService = userService;
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if ("GET".equals(request.getMethod())) {
            User user = (User) request.getSession().getAttribute("user");
            User student = userService.getFullStudent(user.getId());
            List<Faculty> faculties = facultyService.getUserFaculty(user.getId());
            student.setFaculties(faculties);
            putStudentToRequest(student, request);
        }

        if ("POST".equals(request.getMethod())) {

            int id = Integer.parseInt(request.getParameter("id"));
            String email = request.getParameter(EMAIL_FIELD);
            String password = request.getParameter(PASSWORD_FIELD);
            String city = request.getParameter(CITY_FIELD);
            String region = request.getParameter(REGION_FIELD);
            String patronymic = request.getParameter(PATRONYMIC_FIELD);
            String firstName = request.getParameter(FIRST_NAME_FIELD);
            String lastName = request.getParameter(LAST_NAME_FIELD);
            String schoolName = request.getParameter(SCHOOL_NAME_FIELD);

            User student = User.builder()
                    .id(id)
                    .password(password)
                    .email(email)
                    .firstName(firstName)
                    .lastName(lastName)
                    .patronymic(patronymic)
                    .city(city)
                    .region(region)
                    .schoolName(schoolName)
                    .isEnabled(true)
                    .build();

            LOGGER.debug("Sending student form: " + student);
            userService.update(student);
            User fullStudent = userService.getFullStudent(student.getId());
            List<Faculty> faculties = facultyService.getUserFaculty(student.getId());
            fullStudent.setFaculties(faculties);
            putStudentToRequest(fullStudent, request);
        }


        return "/WEB-INF/jsp/user.jsp";
    }

}
