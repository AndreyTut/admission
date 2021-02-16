package com.study.my.command;

import com.study.my.model.User;
import com.study.my.service.UserService;
import com.study.my.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.study.my.util.Constants.*;

public class RegistrationCommand implements Command {

    private UserService userService;

    public RegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if ("GET".equals(request.getMethod())) {
            System.out.println("when get---------------------------");
            return "/WEB-INF/jsp/registration.jsp";
        }

        String email = request.getParameter(EMAIL_FIELD);
        String password = request.getParameter(PASSWORD_FIELD);
        String firstName = request.getParameter(FIRST_NAME_FIELD);
        String lastName = request.getParameter(LAST_NAME_FIELD);
        String patronymic = request.getParameter(PATRONYMIC_FIELD);
        String city = request.getParameter(CITY_FIELD);
        String region = request.getParameter(REGION_FIELD);
        String schoolName = request.getParameter(SCHOOL_NAME_FIELD);

        byte[] image = null;
        try {
            Part filePart = request.getPart(FILE_NAME);
            if (filePart != null) {
                InputStream fileContemt = filePart.getInputStream();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                while (fileContemt.available() > 0) {
                    byteArrayOutputStream.write(fileContemt.read());
                }
                byteArrayOutputStream.flush();
                image = byteArrayOutputStream.toByteArray();
            }
        } catch (IOException | ServletException e) {
            e.printStackTrace();

            //TODO process
            throw new RuntimeException(e);
        }

        User user = User.builder()
                .email(email)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .patronymic(patronymic)
                .city(city)
                .region(region)
                .schoolName(schoolName)
                .diplomImage(image)
                .build();

        userService.create(user);
        return "index.jsp";
    }
}
