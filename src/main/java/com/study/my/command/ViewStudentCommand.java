package com.study.my.command;

import com.study.my.model.User;
import com.study.my.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

public class ViewStudentCommand implements Command {
    private UserService userService;
    private static final Logger LOGGER = Logger.getLogger(ViewStudentCommand.class);

    public ViewStudentCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String id = request.getParameter("id");
        User student = userService.getFullStudent(Integer.parseInt(id));
        byte[] diplomImage = student.getDiplomImage();
        String base64Image = null;
        if (diplomImage != null) {
            base64Image = Base64.getEncoder().encodeToString(diplomImage);
        }
        request.setAttribute("stringimage", base64Image);
        request.setAttribute("student", student);
        return "/WEB-INF/jsp/viewstudent.jsp";
    }
}
