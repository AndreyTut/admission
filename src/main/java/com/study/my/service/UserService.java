package com.study.my.service;

import com.study.my.dao.AbstractDaoFactory;
import com.study.my.dao.DaoFactory;
import com.study.my.dao.UserDao;
import com.study.my.model.Role;
import com.study.my.model.StudentMark;
import com.study.my.model.User;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserService {

    private UserDao userDao;
    private static final Logger LOGGER = Logger.getLogger(UserService.class);

    public UserService() {
        AbstractDaoFactory daoFactory = DaoFactory.getInstance();
        this.userDao = daoFactory.createUserDao();
    }


    public Optional<User> getLogged(String email, String password) {
        return userDao.findByEmailAndPassword(email, password);
    }

    public boolean create(User user) {
        return userDao.create(user) != null;
    }

    public List<User> getAll() {
        LOGGER.debug("getting all users");
        List<User> list = userDao.findAll();
        return list.stream()
                .filter(user -> user.getRoles().contains(Role.ROLE_USER))
                .collect(Collectors.toList());
    }

    public User getFullStudent(Integer id) {
        return userDao.findWithDiploma(id);
    }

    public boolean update(User student) {
        return userDao.update(student);
    }

    public User getById(int id) {
        return userDao.findById(id);
    }

    public boolean setDiplomaImage(int userId, byte[] image) {
        boolean updated = userDao.updateDiplomaImage(userId, image);
        LOGGER.debug("updated diploma image (image size = " + image.length + " for user with id " + userId + " result: " + updated);
        return updated;
    }

    public byte[] getDiplomaImage(int studentId) {
        return userDao.getDiplImage(studentId);
    }

    public User getByEmail(String email) {
        Optional<User> userOptional = userDao.findByEmail(email);
        //TODO Change with custom exception
        return userOptional.orElseThrow(RuntimeException::new);
    }

    public boolean setEnabled(int id, boolean enabled) {
        return userDao.setEnabled(id, enabled);
    }

    public StudentMark getStudentMark(Integer subjId, Integer userId) {
//        User student = userDao.findById(userId);
        StudentMark mark = userDao.getMark(subjId, userId);
//        mark.setUser(student);
        return mark;
    }

    public void addFaculty(int facultyId, int userId) {
        if (!userDao.ifExistStudentFaculty(facultyId, userId)) {
            userDao.addFaculty(facultyId, userId);
        }
    }

    public void saveMark(Integer markId, Integer userId, int subjId, int mark) {
        if (markId == null) {
            userDao.createMark(userId, subjId, mark);
        } else {
            userDao.updateMark(markId, mark);
        }
    }

    public void subscribeOnfaculty(Integer studentId, Integer facultyId) {
        userDao.setFaculty(studentId, facultyId);
    }
}
