package com.study.my.dao;

import com.study.my.model.Subject;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.study.my.util.Constants.*;

public class SubjectDaoImpl implements SubjectDao {

    private static final Logger LOGGER = Logger.getLogger(SubjectDaoImpl.class);
    private Connection connection;

    public SubjectDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Subject create(Subject entity) {
        return null;
    }

    @Override
    public Subject findById(int id) {
        return null;
    }

    @Override
    public List<Subject> findAll() {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL_SUBJECTS);
            List<Subject> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(getFromResultSet(resultSet));
            }
            LOGGER.debug("get all subjects: " + result);
            return result;
        } catch (SQLException e) {
            LOGGER.error(e.toString());
            throw new RuntimeException("Database connection error", e);
        }
    }

    @Override
    public boolean saveFacultySubjs(Integer id, List<Integer> subjIds) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement delStatement = connection.prepareStatement(DELETE_FACULTY_SUBJS);
            delStatement.setInt(1, id);
            boolean executeDel = delStatement.execute();
            PreparedStatement insertStatement = connection.prepareStatement(INSERT_FACULTY_SUBJECT);
            insertStatement.setInt(1, id);
            insertStatement.setInt(2, subjIds.get(0));
            insertStatement.setInt(3, id);
            insertStatement.setInt(4, subjIds.get(1));
            boolean executeInsert = insertStatement.execute();
            connection.commit();
            delStatement.close();
            insertStatement.close();
            return executeDel && executeInsert;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean update(Subject entity) {
        return false;
    }


    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Database connection error", e);
        }
    }

    private Subject getFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(ID_FIELD);
        String nameUa = resultSet.getString(NAME_UA);
        String nameEn = resultSet.getString(NAME_EN);
        return new Subject(id, nameEn, nameUa);
    }
}
