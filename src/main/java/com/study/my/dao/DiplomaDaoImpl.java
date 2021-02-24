package com.study.my.dao;

import com.study.my.model.Diploma;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static com.study.my.util.Constants.CREATE_DIPLOMA;
import static com.study.my.util.Constants.UPDATE_DIPLOMA;

public class DiplomaDaoImpl implements DiplomaDao {

    private static Logger LOGGER = Logger.getLogger(DiplomaDao.class);
    private Connection connection;

    public DiplomaDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Diploma create(Diploma diploma) {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_DIPLOMA)) {
            fillStatement(statement, diploma);
            statement.setInt(7, diploma.getUserId());
            statement.execute();
            return diploma;
        } catch (SQLException e) {
            LOGGER.error(e.toString());
            //TODO change om custom exception
            throw new RuntimeException(e);
        }
    }

    @Override
    public Diploma findById(int id) {
        return null;
    }

    @Override
    public List<Diploma> findAll() {
        return null;
    }

    @Override
    public boolean update(Diploma diploma) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_DIPLOMA)) {
            fillStatement(statement, diploma);
            statement.setInt(7, diploma.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(e.toString());
            //TODO change om custom exception
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

    private void fillStatement(PreparedStatement statement, Diploma diploma) throws SQLException {
        statement.setInt(1, diploma.getMath());
        statement.setInt(2, diploma.getPhysics());
        statement.setInt(3, diploma.getHistory());
        statement.setInt(4, diploma.getLiterature());
        statement.setInt(5, diploma.getChemistry());
        statement.setInt(6, diploma.getBiology());
    }
}
