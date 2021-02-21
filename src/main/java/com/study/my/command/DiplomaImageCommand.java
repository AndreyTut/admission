package com.study.my.command;

import com.study.my.model.User;
import com.study.my.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

import static com.study.my.util.Utils.getImageFromRequest;

public class DiplomaImageCommand implements Command {
    private UserService userService;
    private static final Logger LOGGER = Logger.getLogger(DiplomaImageCommand.class);

    public DiplomaImageCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if ("GET".equals(request.getMethod())) {
            return "/WEB-INF/jsp/imageuploadform.jsp";
        }

        if ("POST".equals(request.getMethod())) {
            byte[] image = getImageFromRequest(request);
            int userId = ((User) request.getSession().getAttribute("user")).getId();
            userService.setDiplomaImage(userId, image);
            User fullStudent = userService.getFullStudent(userId);
            String base64Image = Base64.getEncoder().encodeToString(fullStudent.getDiplomImage());
            request.setAttribute("stringimage", base64Image);
            request.setAttribute("student", fullStudent);
            request.setAttribute("diploma", fullStudent.getDiploma());

            LOGGER.debug("processing diploma image loading, method POST");
        }
        return "redirect:/user/profile";
    }
}
