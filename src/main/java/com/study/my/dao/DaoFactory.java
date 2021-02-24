package com.study.my.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DaoFactory extends AbstractDaoFactory {

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
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
