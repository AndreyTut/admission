package com.study.my.util;

public class Constants {
    public static final String ID_FIELD = "id";
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

    public static final String GET_USER_ID = "SELECT u.*, ur.role FROM user_db u LEFT JOIN user_role ur " +
            "on ur.user_id=u.id WHERE u.id=?";

    public static final String CREATE_WITH_ROLE = "WITH new_user AS (INSERT INTO user_db (email, password, first_name, " +
            "last_name, patronymic, city, region, school_name, enabled, diplom_image) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" +
            "returning id) INSERT INTO user_role (user_id, role) VALUES((select id from new_user), 'ROLE_USER')";

    public static final String GET_ALL_USERS_ROLES = "SELECT u.*, ur.role FROM user_db u LEFT JOIN user_role ur ON ur.user_id=u.id";

    public static final String GET_USER_WITH_DIPLOMA = "SELECT u.*, d.math, d.physics, d.history, d.literature, d.chemistry, d. biology, d.id as d_id, " +
            "ur.role FROM user_db u LEFT JOIN diploma d ON d.user_id=u.id " +
            "LEFT JOIN user_role ur ON ur.user_id=u.id WHERE u.id=?";

    public static final String UPDATE_USER = "UPDATE user_db u SET email=?, password=?, first_name=?, last_name=?," +
            " patronymic=?, city=?, region=?, school_name=?, enabled=? WHERE u.id=?";

    public static final String SET_ENABLED = "UPDATE user_db u SET enabled=? WHERE u.id=?";

    public static final String CREATE_DIPLOMA = "INSERT INTO diploma (math, physics, history, literature, chemistry, biology, user_id)" +
            "VALUES(?, ?, ?, ?, ?, ?, ?)";

    public static final String UPDATE_DIPLOMA = "UPDATE diploma SET math=?, physics=?, history=?, literature=?, chemistry=?, biology=? WHERE id=?";
    public static final String UPD_USER_DIPL_IMAGE = "UPDATE user_db SET diplom_image=? WHERE id=?";
    public static final String GET_IMG = "SELECT diplom_image FROM user_db WHERE id=?";

    public static String SUBJ_CHEMISTRY = "chemistry";
    public static String SUBJ_LITERATURE = "literature";
    public static String SUBJ_MATH = "math";
    public static String SUBJ_HISTORY = "history";
    public static String SUBJ_PHYSICS = "physics";
    public static String SUBJ_BIOLOGY = "biology";
}
