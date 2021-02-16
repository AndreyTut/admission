package com.study.my.service;

import com.study.my.dao.AbstractDaoFactory;
import com.study.my.dao.DaoFactory;
import com.study.my.dao.UserDao;
import com.study.my.model.Role;
import com.study.my.model.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserService {

    private UserDao userDao;

    public UserService() {
        AbstractDaoFactory daoFactory = DaoFactory.getInstance();
        this.userDao = daoFactory.createUserDao();
    }


    public Optional<User> getLogged(String email, String password) {
        return userDao.findByEmailAndPassword(email, password);
    }

    public boolean create(User user) {
        return userDao.create(user);
    }

    public List<User> getAll() {
        List<User> list = userDao.findAll();
        return list.stream()
                .filter(user -> user.getRoles().contains(Role.ROLE_USER))
                .filter(User::isEnabled)
                .collect(Collectors.toList());
    }
}
