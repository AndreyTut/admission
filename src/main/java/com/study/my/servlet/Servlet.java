package com.study.my.servlet;

import com.study.my.command.*;
import com.study.my.service.DiplomaService;
import com.study.my.service.FacultyService;
import com.study.my.service.UserService;
import org.apache.log4j.Logger;

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
    private DiplomaService diplomaService = new DiplomaService();
    private FacultyService facultyService = new FacultyService();
    private Map<String, Command> commands = new HashMap<>();
    private static final Logger LOGGER = Logger.getLogger(Servlet.class);

    @Override
    public void init() throws ServletException {
        commands.put("login", new LoginCommand(userService));
        commands.put("logout", new LogoutCommand());
        commands.put("user", new UserCommand());
        commands.put("user/profile", new ProfileCommand(userService, facultyService));
        commands.put("admin", new UserCommand());
        commands.put("admin/students", new StudentListCommand(userService));
        commands.put("registration", new RegistrationCommand(userService));
        commands.put("user/diploma", new DiplomaCommand(userService, diplomaService, facultyService));
        commands.put("user/addfaculty", new SubscribeFacultyCommand(userService, facultyService));
        commands.put("user/faculty/submit", new SubmitFacultyCommand(userService, facultyService));
        commands.put("user/setimage", new DiplomaImageCommand(userService));
        commands.put("admin/student", new ViewStudentCommand(userService, facultyService));
        commands.put("admin/student/changeactive", new BlockStudentCommand(userService));
        commands.put("admin/faculties", new FacultyCommand(facultyService));
        commands.put("admin/faculty/edit", new EditFacultyCommand(facultyService));
        commands.put("admin/faculty/add", new EditFacultyCommand(facultyService));
        commands.put("admin/faculty/save", new SaveFacultyCommand(facultyService));
        commands.put("admin/faculty/delete", new DeleteFacultyCommand(facultyService));
        commands.put("admin/addtoreport", new AddToReportCommand(userService, facultyService));
        commands.put("admin/faculty/finalize", new FinalizeCommand(userService, facultyService));

    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        LOGGER.debug("Servlet process get request " + httpServletRequest.getRequestURI());
        catchExceptions(httpServletRequest, httpServletResponse);
    }


    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        catchExceptions(httpServletRequest, httpServletResponse);
    }

    private void catchExceptions(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        try {
            processRequest(httpServletRequest, httpServletResponse);
        } catch (Exception e) {
            String message = e.getMessage();
            httpServletRequest.setAttribute("errormessage", message);
            httpServletRequest.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(httpServletRequest, httpServletResponse);
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        path = path.replaceAll(".*/admission/command/", "");

        Command command = commands.getOrDefault(path,
                r -> "/index.jsp");
        String page = command.execute(request);
        if (page.contains("redirect:")) {
            response.sendRedirect(page.replace("redirect:", "/admission/command"));
        } else {
            LOGGER.debug("forward to page: " + page);
            request.getRequestDispatcher(page).forward(request, response);
        }

    }

}
