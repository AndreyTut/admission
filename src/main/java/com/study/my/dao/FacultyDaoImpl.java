package com.study.my.dao;

import com.study.my.model.Faculty;
import com.study.my.model.Subject;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.study.my.util.Constants.*;

public class FacultyDaoImpl implements FacultyDao {

    private static final Logger LOGGER = Logger.getLogger(FacultyDaoImpl.class);
    private Connection connection;

    public FacultyDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Faculty create(Faculty faculty) {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_FACULTY, Statement.RETURN_GENERATED_KEYS)) {
            fillFacultyStatement(faculty, statement);
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    faculty.setId(generatedKeys.getInt(1));
                    return faculty;
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.toString());
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Faculty findById(int id) {
        try (PreparedStatement statement = connection.prepareStatement(GET_FACULTY_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Faculty faculty = getFromResultSet(resultSet);
                List<Subject> list = new ArrayList<>();
                list.add(getSubjectFromResultSet(resultSet));
                if (resultSet.next()) {
                    list.add(getSubjectFromResultSet(resultSet));
                }
                faculty.setSubjects(list);
                LOGGER.debug("get faculty by id: " + faculty);
                return faculty;
            }
        } catch (SQLException e) {
            LOGGER.error(e.toString());
            throw new RuntimeException(e);
        }
        return null;
    }

    private Subject getSubjectFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("subj_id");
        String nameUa = resultSet.getString("subj_name_ua");
        String nameEn = resultSet.getString("subj_name_en");
        return new Subject(id, nameEn, nameUa);
    }

    @Override
    public List<Faculty> findAll() {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL_FACULTIES);
            List<Faculty> faculties = new ArrayList<>();
            while (resultSet.next()) {
                faculties.add(getFromResultSet(resultSet));
            }
            return faculties;
        } catch (SQLException e) {
            LOGGER.error(e.toString());
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Faculty faculty) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_FACULTY)) {
            fillFacultyStatement(faculty, preparedStatement);
            preparedStatement.setInt(5, faculty.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(e.toString());
            throw new RuntimeException(e);
        }
    }

    private void fillFacultyStatement(Faculty faculty, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, faculty.getNameEn());
        preparedStatement.setString(2, faculty.getNameUa());
        preparedStatement.setInt(3, faculty.getVacancyBudge());
        preparedStatement.setInt(4, faculty.getVacancyContr());
    }

    @Override
    public void delete(int id) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement delSubjStatement = connection.prepareStatement(DELETE_FACULTY_SUBJS);
            delSubjStatement.setInt(1, id);
            delSubjStatement.executeUpdate();
            PreparedStatement delFaculStatement = connection.prepareStatement(DELETE_FACULTY);
            delFaculStatement.setInt(1, id);
            delFaculStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                LOGGER.error(e.toString());
                throw new RuntimeException(e);
            }
            LOGGER.error(e.toString());
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Faculty> getForUser(Integer userId) {
        try (PreparedStatement statement = connection.prepareStatement(GET_STUDENT_FACULTIES)) {
            LOGGER.debug("getting user faculties for user with id: " + userId);
            statement.setInt(1, userId);
            List<Faculty> faculties = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                faculties.add(getFromResultSet(resultSet));
            }
            return faculties;
        } catch (SQLException e) {
            LOGGER.error(e.toString());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Faculty getOneForUser(int userId) {
        try (PreparedStatement statement = connection.prepareStatement(GET_STUDENT_FACULTY)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getFromResultSet(resultSet);
            }
            return null;
        } catch (SQLException e) {
            LOGGER.error(e.toString());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void finalizeFaculty(int id) {
        try (PreparedStatement statement = connection.prepareStatement(FINALIZE_FACULTY)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Faculty getFromResultSet(ResultSet resultSet) throws SQLException {

        Faculty faculty = Faculty.builder()
                .id(resultSet.getInt(ID_FIELD))
                .nameEn(resultSet.getString(NAME_EN))
                .nameUa(resultSet.getString(NAME_UA))
                .vacancyBudge(resultSet.getInt(VACS_BUDGET))
                .vacancyContr(resultSet.getInt(VACS_CONTRACT))
                .build();
        faculty.setFinalized(resultSet.getBoolean(FINALIZED));
        return faculty;
    }

}
