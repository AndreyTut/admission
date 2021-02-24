package com.study.my.command;

import com.study.my.model.User;
import com.study.my.service.FacultyService;
import com.study.my.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class SubmitFacultyCommand implements Command {
    private UserService userService;
    private FacultyService facultyService;

    public SubmitFacultyCommand(UserService userService, FacultyService facultyService) {
        this.userService = userService;
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String facultyid = request.getParameter("facultyid");
        User user = (User) request.getSession().getAttribute("user");

        userService.addFaculty(Integer.parseInt(facultyid), user.getId());
        String mark1idStr = request.getParameter("mark1id");
        String mark2idStr = request.getParameter("mark2id");
        int mark1 = Integer.parseInt(request.getParameter("mark1newvalue"));
        int mark2 = Integer.parseInt(request.getParameter("mark2newvalue"));
        Integer mark1id = mark1idStr == null || "".equals(mark1idStr) ? null : Integer.parseInt(mark1idStr);
        Integer mark2id = mark2idStr == null || "".equals(mark2idStr) ? null : Integer.parseInt(mark2idStr);
        int subj1id = Integer.parseInt(request.getParameter("subj1id"));
        int subj2id = Integer.parseInt(request.getParameter("subj2id"));
        userService.saveMark(mark1id, user.getId(), subj1id, mark1);
        userService.saveMark(mark2id, user.getId(), subj2id, mark2);
        return "redirect:/user/profile";
    }
}
