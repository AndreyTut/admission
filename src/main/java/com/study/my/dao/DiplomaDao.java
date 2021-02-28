package com.study.my.dao;

import com.study.my.model.Diploma;

public interface DiplomaDao {
    Diploma create(Diploma diploma);

    boolean update(Diploma diploma);

    void close();

}
