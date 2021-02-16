package com.study.my.dao;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConnectionPoolHolder {
    private static volatile DataSource dataSource;

    public static DataSource getDataSource() {

        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {

                    String username;
                    String password;
                    String url;
                    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                    try (InputStream inputStream = classLoader.getResourceAsStream("db.properties")) {
                        Properties properties = new Properties();
                        properties.load(inputStream);
                        url = properties.getProperty("db.url");
                        username = properties.getProperty("db.user");
                        password = properties.getProperty("db.password");
                    } catch (IOException e) {
                        throw new IllegalStateException("Invalid config file ");
                    }
                    BasicDataSource ds = new BasicDataSource();
                    ds.setDriverClassName("org.postgresql.Driver");
                    ds.setUrl(url);
                    ds.setUsername(username);
                    ds.setPassword(password);
                    ds.setMinIdle(5);
                    ds.setMaxIdle(10);
                    ds.setMaxOpenPreparedStatements(100);
                    dataSource = ds;
                }
            }
        }
        return dataSource;
    }
}
