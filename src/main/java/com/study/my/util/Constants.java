package com.study.my.util;

public class Constants {
    public static final String EMAIL_FIELD = "email";
    public static final String FIRST_NAME_FIELD = "firstName";
    public static final String LAST_NAME_FIELD = "lastName";
    public static final String PATRONYMIC_FIELD = "patronymic";
    public static final String PASSWORD_FIELD = "password";
    public static final String SCHOOL_NAME_FIELD = "schoolName";
    public static final String CITY_FIELD = "city";
    public static final String REGION_FIELD = "region";
    public static final String FILE_NAME = "file";

    public static final String EMAIL_REGEX = "[A-Za-z0-9]+@[a-z]+\\.[a-z]+";
    public static final String NAME_REGEX = "([А-ЯЩІЄ][а-ящіїє']+)|([A-Z][a-z']+)";

    public static final String GET_USER_EMAIL_PSW = "SELECT u.*, ur.role FROM user_db u LEFT JOIN user_role ur " +
            "on ur.user_id=u.id WHERE u.email=? and u.password=?";

    public static final String GET_USER_EMAIL = "SELECT u.*, ur.role FROM user_db u LEFT JOIN user_role ur " +
            "on ur.user_id=u.id WHERE u.email=?";

    public static final String CREATE_WITH_ROLE = "WITH new_user AS (INSERT INTO user_db (email, password, first_name, " +
            "last_name, patronymic, city, region, school_name, diplom_image, enabled) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" +
            "returning id) INSERT INTO user_role (user_id, role) VALUES((select id from new_user), 'ROLE_USER')";


}
