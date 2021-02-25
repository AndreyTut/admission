package com.study.my.dao;

import com.study.my.model.StudentMark;
import com.study.my.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends GenericDao<User> {
    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String psw);

    User findWithDiploma(Integer id);

    boolean updateDiplomaImage(int userId, byte[] image);

    byte[] getDiplImage(int studentId);

    boolean setEnabled(int id, boolean enabled);

    StudentMark getMark(Integer id, Integer userId);

    void addFaculty(int facultyId, int userId);

    void createMark(Integer userId, int subjId, int mark);

    void updateMark(Integer markId, int mark);

    boolean ifExistStudentFaculty(int facultyId, int userId);

    void setFaculty(Integer studentId, Integer facultyId);

    public List<User> findByFaculty(int id, int sub1Id, int sub2Id);

    void setStatus(Integer id, int status);
}
