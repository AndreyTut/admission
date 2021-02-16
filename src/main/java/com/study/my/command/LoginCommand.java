package com.study.my.command;

import com.study.my.model.Role;
import com.study.my.model.User;
import com.study.my.service.UserService;
import com.study.my.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static com.study.my.util.Constants.EMAIL_FIELD;
import static com.study.my.util.Constants.PASSWORD_FIELD;

public class LoginCommand implements Command {

    private UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        if ("GET".equals(request.getMethod())) {
            return "/WEB-INF/jsp/login.jsp";
        }
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String checking = checkUser(user);
        if (checking != null) {
            return checking;
        }

        String email = request.getParameter(EMAIL_FIELD);
        String password = request.getParameter(PASSWORD_FIELD);

        Optional<User> fromDb = userService.getLogged(email, password);
        if (!fromDb.isPresent()) {
            return "/WEB-INF/jsp/login.jsp";
        }
        User logUser = fromDb.get();
        session.setAttribute("user", logUser);

        boolean isAdmin = logUser.getRoles().contains(Role.ROLE_ADMIN);
        boolean isUser = logUser.getRoles().contains(Role.ROLE_USER);
        session.setAttribute("isAdmin", isAdmin);
        session.setAttribute("isUser", isUser);

        return isAdmin
                ? "/WEB-INF/jsp/admin.jsp"
                : isUser
                ? "/WEB-INF/jsp/user.jsp"
                : "/WEB-INF/jsp/error.jsp";
    }

    private String checkUser(User user) {
        if (user != null) {
            return "/WEB-INF/jsp/error.jsp";
        }
        return null;
    }
}
