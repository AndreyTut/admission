package com.study.my.dao;

import com.study.my.model.Subject;

import java.util.List;

public interface SubjectDao extends GenericDao<Subject> {
    boolean saveFacultySubjs(Integer id, List<Integer> subjIds);
}
