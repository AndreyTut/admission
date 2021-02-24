package com.study.my.command;

import com.study.my.model.Faculty;
import com.study.my.service.FacultyService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class SaveFacultyCommand implements Command {
    private final Logger LOGGER = Logger.getLogger(SaveFacultyCommand.class);
    private FacultyService facultyService;

    public SaveFacultyCommand(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String id = request.getParameter("id");
        String nameEn = request.getParameter("nameEn");
        String nameUa = request.getParameter("nameUa");
        String budget = request.getParameter("vacancyBudge");
        String contract = request.getParameter("vacancyContr");
        String subj1Id = request.getParameter("subj1");
        String subj2Id = request.getParameter("subj2");

        Integer facultyId = (id == null) || ("".equals(id)) ? null : Integer.parseInt(id);
        Faculty faculty = Faculty.builder()
                .id(facultyId)
                .nameUa(nameUa)
                .nameEn(nameEn)
                .vacancyContr(Integer.valueOf(contract))
                .vacancyBudge(Integer.valueOf(budget))
                .build();

        List<Integer> subjIds = new ArrayList<>();
        subjIds.add(Integer.valueOf(subj1Id));
        subjIds.add(Integer.valueOf(subj2Id));

        facultyService.saveWithSubjIds(faculty, subjIds);

        return "redirect:/admin/faculties";
    }
}
