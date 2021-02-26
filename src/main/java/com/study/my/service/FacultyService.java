package com.study.my.service;

import com.study.my.dao.AbstractDaoFactory;
import com.study.my.dao.FacultyDao;
import com.study.my.dao.SubjectDao;
import com.study.my.dao.UserDao;
import com.study.my.model.Faculty;
import com.study.my.model.Subject;
import com.study.my.model.User;
import org.apache.log4j.Logger;

import java.util.List;

public class FacultyService {
    private FacultyDao facultyDao;
    private SubjectDao subjectDao;
    private UserDao userDao;
    private static final Logger LOGGER = Logger.getLogger(FacultyService.class);

    public FacultyService() {
        AbstractDaoFactory daoFactory = AbstractDaoFactory.getInstance();
        facultyDao = daoFactory.createFacultyDao();
        subjectDao = daoFactory.createSubjectDao();
        userDao = daoFactory.createUserDao();
    }


    public List<Faculty> getAll() {
        return facultyDao.findAll();
    }

    public Faculty getById(int id) {
        return facultyDao.findById(id);
    }

    public List<Subject> getAllSubjects() {
        return subjectDao.findAll();
    }

    public boolean saveWithSubjIds(Faculty faculty, List<Integer> subjIds) {
        int facultyId;
        boolean isFacultyOpSuccess;
        if (faculty.getId() == null) {
            Faculty saved = facultyDao.create(faculty);
            facultyId = saved.getId();
            isFacultyOpSuccess = saved != null;
        } else {
            isFacultyOpSuccess = facultyDao.update(faculty);
            facultyId = faculty.getId();
        }
        boolean isSubjSaved = subjectDao.saveFacultySubjs(facultyId, subjIds);
        return isFacultyOpSuccess && isSubjSaved;
    }

    public boolean deletefaculty(int id) {
        facultyDao.delete(id);
        return true;
    }

    public List<Faculty> getUserFaculty(Integer id) {
        return facultyDao.getForUser(id);
    }

    public Faculty getUserSubscr(int userId) {
        return facultyDao.getOneForUser(userId);
    }

    public boolean finalizeReport(int id) {
        Faculty faculty = facultyDao.findById(id);

        int sub1Id = faculty.getSubjects().get(0).getId();
        LOGGER.debug("subject 1: " + faculty.getSubjects().get(0));
        LOGGER.debug("subject 2: " + faculty.getSubjects().get(1));
        int sub2Id = faculty.getSubjects().get(1).getId();
        List<User> students = userDao.findByFaculty(id, sub1Id, sub2Id);
        students.sort((s1, s2) -> (s2.getMarks().get(0).getMark() + s2.getMarks().get(1).getMark())
                                - (s1.getMarks().get(0).getMark() + s1.getMarks().get(1).getMark()));
        for (int i = 0; i < faculty.getVacancyBudge(); i++) {
            if (i >= students.size()) {
                break;
            }
            students.get(i).setStatus(1);
        }
        for (int i = faculty.getVacancyBudge(); i < faculty.getVacancyContr() + faculty.getVacancyBudge(); i++) {
            if (i >= students.size()) {
                break;
            }
            students.get(i).setStatus(2);
        }
        for (int i = faculty.getVacancyContr() + faculty.getVacancyBudge(); i < students.size(); i++) {
            students.get(i).setStatus(3);
        }
        facultyDao.finalizeFaculty(id);

        for (User student : students) {
            userDao.setStatus(student.getId(), student.getStatus());
            LOGGER.debug(student.getEmail() + " marks " + student.getMarks().get(0).getMark() + " " + student.getMarks().get(1).getMark());
        }
        return true;
    }
}
