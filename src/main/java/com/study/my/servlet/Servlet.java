package com.study.my.servlet;

import com.study.my.command.*;
import com.study.my.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class Servlet extends HttpServlet {

    private UserService userService = new UserService();
    private Map<String, Command> commands = new HashMap<>();

    @Override
    public void init() throws ServletException {
        commands.put("login", new LoginCommand(userService));
        commands.put("logout", new LogoutCommand());
        commands.put("user", new UserCommand());
        commands.put("admin", new UserCommand());
        commands.put("admin/students", new StudentListCommand(userService));
        commands.put("registration", new RegistrationCommand(userService));
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        processRequest(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        processRequest(httpServletRequest, httpServletResponse);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        path = path.replaceAll(".*/admission/command/", "");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + path);
        Command command = commands.getOrDefault(path,
                r -> "/index.jsp");
        System.out.println(command.getClass().getName());
        String page = command.execute(request);
        System.out.println("return from command: " + page);
        if (page.contains("redirect:")) {
            response.sendRedirect(page.replace("redirect:", "/admission/command"));
        } else {
            System.out.println("forward to page: " + page);
            request.getRequestDispatcher(page).forward(request, response);
        }

    }

}
