package com.study.my.command;

import com.study.my.command.Command;
import com.study.my.command.ViewStudentCommand;
import com.study.my.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class BlockStudentCommand implements Command {
    private UserService userService;
    private static final Logger LOGGER = Logger.getLogger(BlockStudentCommand.class);

    public BlockStudentCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        boolean enabled = Boolean.valueOf(request.getParameter("enabled"));
        int id = Integer.valueOf(request.getParameter("id"));
        LOGGER.debug("block user method, id = " + id + ", enabled = " + enabled);
        userService.setEnabled(id, enabled);
        return "redirect:/admin/student?id=" + id;
    }
}
