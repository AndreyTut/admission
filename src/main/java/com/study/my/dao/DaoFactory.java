package com.study.my.dao;

import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DaoFactory extends AbstractDaoFactory {
    private static Logger LOGGER = Logger.getLogger(DiplomaDao.class);
    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public UserDao createUserDao() {
        return new UserDaoImpl(getConnection());
    }

    @Override
    public DiplomaDao createDiplomaDao() {
        return new DiplomaDaoImpl(getConnection());
    }

    @Override
    public FacultyDao createFacultyDao() {
        return new FacultyDaoImpl(getConnection());
    }

    @Override
    public SubjectDao createSubjectDao() {
        return new SubjectDaoImpl(getConnection());
    }


    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            LOGGER.error(e.toString());
            throw new RuntimeException("Database connection error", e);
        }
    }
}
