package com.study.my.service;

import com.study.my.dao.AbstractDaoFactory;
import com.study.my.dao.FacultyDao;
import com.study.my.dao.SubjectDao;
import com.study.my.model.Faculty;
import com.study.my.model.Subject;
import org.apache.log4j.Logger;

import java.util.List;

public class FacultyService {
    private FacultyDao facultyDao;
    private SubjectDao subjectDao;
    private static final Logger LOGGER = Logger.getLogger(FacultyService.class);

    public FacultyService() {
        AbstractDaoFactory daoFactory = AbstractDaoFactory.getInstance();
        facultyDao = daoFactory.createFacultyDao();
        subjectDao = daoFactory.createSubjectDao();
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
}
