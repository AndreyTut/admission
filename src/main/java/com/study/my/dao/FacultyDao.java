package com.study.my.dao;

import com.study.my.model.Faculty;

import java.util.List;

public interface FacultyDao extends GenericDao<Faculty> {
    List<Faculty> getForUser(Integer id);

    Faculty getOneForUser(int userId);

    void finalizeFaculty(int id);
}
