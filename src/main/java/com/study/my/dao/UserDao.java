package com.study.my.dao;

import com.study.my.model.User;

import java.util.Optional;

public interface UserDao extends GenericDao<User> {
    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String psw);

    User findWithDiploma(Integer id);
}
