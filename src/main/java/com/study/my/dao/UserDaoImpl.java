package com.study.my.dao;

import com.study.my.model.Role;
import com.study.my.model.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.study.my.util.Constants.*;

public class UserDaoImpl implements UserDao {

    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    private Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<User> findByEmail(String email) {

        try (PreparedStatement statement = connection.prepareStatement(GET_USER_EMAIL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            return Optional.ofNullable(getUserFromResultSet(resultSet));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String psw) {
        try (PreparedStatement statement = connection.prepareStatement(GET_USER_EMAIL_PSW)) {
            statement.setString(1, email);
            statement.setString(2, psw);
            ResultSet resultSet = statement.executeQuery();
            return Optional.ofNullable(getUserFromResultSet(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findWithDiploma(Integer id) {

        try (PreparedStatement statement = connection.prepareStatement(GET_STUDENT_WITH_DIPLOMA)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = getUserFromResultSet(resultSet);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean create(User user) {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_WITH_ROLE)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getPatronymic());
            statement.setString(6, user.getCity());
            statement.setString(7, user.getRegion());
            statement.setString(8, user.getSchoolName());
            statement.setBytes(9, user.getDiplomImage());
            statement.setBoolean(10, user.isEnabled());
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL_USERS_ROLES);
            List<User> users = new ArrayList<>();
            User user = getUserFromResultSet(resultSet);
            while (user != null) {
                users.add(user);
                user = getUserFromResultSet(resultSet);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            User user = new User(resultSet.getString("email"));
            user.setId(resultSet.getInt("id"));
            user.setRoles(Collections.singleton(Role.valueOf(resultSet.getString("role"))));
            user.setPassword(resultSet.getString("password"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setPatronymic(resultSet.getString("patronymic"));
            user.setCity(resultSet.getString("city"));
            user.setRegion(resultSet.getString("region"));
            user.setSchoolName(resultSet.getString("school_name"));
            user.setDiplomImage(resultSet.getBytes("diplom_image"));
            user.setEnabled(resultSet.getBoolean("enabled"));
            return user;
        }
        return null;
    }

//    private Diploma
}
