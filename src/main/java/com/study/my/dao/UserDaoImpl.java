package com.study.my.dao;

import com.study.my.model.*;
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
            LOGGER.error(e.toString());
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
            LOGGER.error(e.toString());
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findWithDiploma(Integer id) {

        try (PreparedStatement statement = connection.prepareStatement(GET_USER_WITH_DIPLOMA)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            User user = getWithDiplomaFromResultSet(resultSet);
            LOGGER.debug("found user: " + user);
            LOGGER.debug("found diploma: " + user.getDiploma());
            return user;
        } catch (SQLException e) {
            LOGGER.error(e.toString());
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateDiplomaImage(int userId, byte[] image) {
        try (PreparedStatement statement = connection.prepareStatement(UPD_USER_DIPL_IMAGE)) {
            statement.setBytes(1, image);
            statement.setInt(2, userId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(e.toString());
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] getDiplImage(int id) {
        try (PreparedStatement statement = connection.prepareStatement(GET_IMG)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getBytes("diplom_image");
            }
        } catch (SQLException e) {
            LOGGER.error(e.toString());
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean setEnabled(int id, boolean enabled) {
        try (PreparedStatement statement = connection.prepareStatement(SET_ENABLED)) {
            statement.setBoolean(1, enabled);
            statement.setInt(2, id);
            LOGGER.debug("block user, set enabled = " + enabled + ", id = " + id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(e.toString());
            throw new RuntimeException(e);
        }
    }

    @Override
    public StudentMark getMark(Integer subjId, Integer userId) {
        try (PreparedStatement statement = connection.prepareStatement(GET_USERMARK)) {
            statement.setInt(1, userId);
            statement.setInt(2, subjId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                StudentMark mark = new StudentMark();
                mark.setId(resultSet.getInt("id"));
                mark.setMark(resultSet.getInt("mark"));
                return mark;
            }
            return new StudentMark();
        } catch (SQLException e) {
            LOGGER.error(e.toString());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addFaculty(int facultyId, int userId) {
        try (PreparedStatement statement = connection.prepareStatement(STUDENT_ADD_FACULTY)) {
            statement.setInt(1, userId);
            statement.setInt(2, facultyId);
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.toString());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createMark(Integer userId, int subjId, int mark) {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_STUDENT_MARK)) {
            statement.setInt(1, userId);
            statement.setInt(2, subjId);
            statement.setInt(3, mark);
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.toString());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateMark(Integer markId, int mark) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_STUDENT_MARK)) {
            statement.setInt(1, mark);
            statement.setInt(2, markId);
        } catch (SQLException e) {
            LOGGER.error(e.toString());
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean ifExistStudentFaculty(int facultyId, int userId) {
        try (PreparedStatement statement = connection.prepareStatement(STUDENT_FACULTY_SIMPLE)) {
            statement.setInt(1, userId);
            statement.setInt(2, facultyId);
            return (statement.executeQuery()).next();
        } catch (SQLException e) {
            LOGGER.error(e.toString());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setFaculty(Integer studentId, Integer facultyId) {
        try (PreparedStatement statement = connection.prepareStatement(SET_STUDENT_FACULTY)) {
            statement.setInt(1, facultyId);
            statement.setInt(2, studentId);
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.toString());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findByFaculty(int id, int sub1Id, int sub2Id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_SUBSCRIBER_FACULTY)) {
            preparedStatement.setInt(1, sub1Id);
            preparedStatement.setInt(2, sub2Id);
            preparedStatement.setInt(3, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> list = new ArrayList<>();
            while (resultSet.next()) {
                User user = getUserSimple(resultSet);
                StudentMark mark1 = new StudentMark();
                StudentMark mark2 = new StudentMark();
                mark1.setMark(resultSet.getInt("mark"));
                LOGGER.debug("first mark retrieved");
                resultSet.next();
                mark2.setMark(resultSet.getInt("mark"));
                LOGGER.debug("second mark retrieved");
                List<StudentMark> marks = new ArrayList<>();
                marks.add(mark1);
                marks.add(mark2);
                user.setMarks(marks);
                list.add(user);
            }
            return list;
        } catch (
                SQLException e) {
            LOGGER.error(e.toString());
            throw new RuntimeException(e);

        }

    }

    @Override
    public void setStatus(Integer id, int status) {
        try (PreparedStatement statement = connection.prepareStatement(STUDENT_SET_STATUS)) {
            statement.setInt(1, status);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.toString());
            throw new RuntimeException(e);
        }
    }

    @Override
    public User create(User user) {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_WITH_ROLE, Statement.RETURN_GENERATED_KEYS)) {
            fillStatment(user, statement);
            statement.setBytes(10, user.getDiplomImage());
            statement.setInt(11, user.getStatus());
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                    LOGGER.debug("created user: " + user);
                    return user;
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.toString());
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public User findById(int id) {
        try (PreparedStatement statement = connection.prepareStatement(GET_USER_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            return getUserFromResultSet(resultSet);
        } catch (SQLException e) {
            LOGGER.error(e.toString());
            throw new RuntimeException(e);
        }
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
            LOGGER.error(e.toString());
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(User user) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER)) {
            fillStatment(user, statement);
            statement.setInt(10, user.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(e.toString());
            throw new RuntimeException(e);
        }
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
            User user = getUser(resultSet);
            return user;
        }
        return null;
    }


    private User getWithDiplomaFromResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            User user = getUser(resultSet);
            Diploma diploma = Diploma.builder()
                    .biology(resultSet.getInt(SUBJ_BIOLOGY))
                    .chemistry(resultSet.getInt(SUBJ_CHEMISTRY))
                    .math(resultSet.getInt(SUBJ_MATH))
                    .physics(resultSet.getInt(SUBJ_PHYSICS))
                    .history(resultSet.getInt(SUBJ_HISTORY))
                    .literature(resultSet.getInt(SUBJ_LITERATURE))
                    .id(resultSet.getInt("d_id"))
                    .build();
            if (diploma.getId() == 0) {
                diploma.setId(null);
            }
            user.setDiploma(diploma);
            return user;
        }
        return null;
    }

    private void fillStatment(User user, PreparedStatement statement) throws SQLException {
        statement.setString(1, user.getEmail());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getFirstName());
        statement.setString(4, user.getLastName());
        statement.setString(5, user.getPatronymic());
        statement.setString(6, user.getCity());
        statement.setString(7, user.getRegion());
        statement.setString(8, user.getSchoolName());
        statement.setBoolean(9, user.isEnabled());
    }

    private User getUser(ResultSet resultSet) throws SQLException {

        User user = getUserSimple(resultSet);
        user.setRoles(Collections.singleton(Role.valueOf(resultSet.getString("role"))));
        return user;
    }

    private User getUserSimple(ResultSet resultSet) throws SQLException {
        User user = new User(resultSet.getString("email"));
        user.setId(resultSet.getInt("id"));
        user.setPassword(resultSet.getString("password"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setPatronymic(resultSet.getString("patronymic"));
        user.setCity(resultSet.getString("city"));
        user.setRegion(resultSet.getString("region"));
        user.setSchoolName(resultSet.getString("school_name"));
        user.setDiplomImage(resultSet.getBytes("diplom_image"));
        user.setEnabled(resultSet.getBoolean("enabled"));
        user.setStatus(resultSet.getInt("status"));
        LOGGER.debug("getting user: " + user);
        return user;
    }
}
