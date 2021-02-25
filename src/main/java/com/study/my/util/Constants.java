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

    public static final String NAME_EN = "name_en";
    public static final String NAME_UA = "name_ua";
    public static final String VACS_BUDGET = "vacs";
    public static final String VACS_CONTRACT = "vacs_contr";
    public static final String FINALIZED = "finalized";


    public static final String EMAIL_REGEX = "[A-Za-z0-9]+@[a-z]+\\.[a-z]+";
    public static final String NAME_REGEX = "([А-ЯЩІЄ][а-ящіїє']+)|([A-Z][a-z']+)";

    public static final String GET_USER_EMAIL_PSW = "SELECT u.*, ur.role FROM user_db u LEFT JOIN user_role ur " +
            "on ur.user_id=u.id WHERE u.email=? and u.password=?";

    public static final String GET_USER_EMAIL = "SELECT u.*, ur.role FROM user_db u LEFT JOIN user_role ur " +
            "on ur.user_id=u.id WHERE u.email=?";

    public static final String GET_USER_ID = "SELECT u.*, ur.role FROM user_db u LEFT JOIN user_role ur " +
            "on ur.user_id=u.id WHERE u.id=?";

    public static final String CREATE_WITH_ROLE = "WITH new_user AS (INSERT INTO user_db (email, password, first_name, " +
            "last_name, patronymic, city, region, school_name, enabled, diplom_image, status) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" +
            "returning id) INSERT INTO user_role (user_id, role) VALUES((select id from new_user), 'ROLE_USER')";

    public static final String GET_ALL_USERS_ROLES = "SELECT u.*, ur.role FROM user_db u LEFT JOIN user_role ur ON ur.user_id=u.id";

    public static final String GET_USER_WITH_DIPLOMA = "SELECT u.*, d.math, d.physics, d.history, d.literature, d.chemistry, d. biology, d.id as d_id, " +
            "ur.role FROM user_db u LEFT JOIN diploma d ON d.user_id=u.id " +
            "LEFT JOIN user_role ur ON ur.user_id=u.id WHERE u.id=?";

    public static final String UPDATE_USER = "UPDATE user_db u SET email=?, password=?, first_name=?, last_name=?," +
            " patronymic=?, city=?, region=?, school_name=?, enabled=? WHERE u.id=?";
    public static final String SET_STUDENT_FACULTY = "UPDATE user_db SET faculty_id=? WHERE id=?";
    public static final String STUDENT_SET_STATUS = "UPDATE user_db SET status=? WHERE id=?";


    public static final String SET_ENABLED = "UPDATE user_db u SET enabled=? WHERE u.id=?";

    public static final String CREATE_DIPLOMA = "INSERT INTO diploma (math, physics, history, literature, chemistry, biology, user_id)" +
            "VALUES(?, ?, ?, ?, ?, ?, ?)";

    public static final String UPDATE_DIPLOMA = "UPDATE diploma SET math=?, physics=?, history=?, literature=?, chemistry=?, biology=? WHERE id=?";
    public static final String UPD_USER_DIPL_IMAGE = "UPDATE user_db SET diplom_image=? WHERE id=?";
    public static final String GET_IMG = "SELECT diplom_image FROM user_db WHERE id=?";

    public static final String GET_ALL_FACULTIES = "SELECT * FROM faculty";
    public static final String GET_FACULTY_BY_ID = "SELECT f.*, s.id as subj_id, s.name_en as subj_name_en, s.name_ua as subj_name_ua" +
            " FROM faculty f LEFT JOIN faculty_subject fs ON fs.faculty_id=f.id " +
            "LEFT JOIN subject s on fs.subject_id=s.id WHERE f.id=?";
    public static final String UPDATE_FACULTY = "UPDATE faculty SET name_en=?, name_ua=?, vacs=?, vacs_contr=? WHERE id=?";
    public static final String CREATE_FACULTY = "INSERT INTO faculty (name_en, name_ua, vacs, vacs_contr) VALUES(?, ?, ?, ?)";
    public static final String DELETE_FACULTY = "DELETE FROM faculty WHERE id=?";
    public static final String FINALIZE_FACULTY = "UPDATE faculty SET finalized=true WHERE id=?";

    public static final String GET_ALL_SUBJECTS = "SELECT * FROM subject";

    public static final String DELETE_FACULTY_SUBJS = "DELETE FROM faculty_subject WHERE faculty_id=?";
    public static final String INSERT_FACULTY_SUBJECT = "INSERT INTO faculty_subject values(?,?), (?,?)";
    public static final String GET_USERMARK = "SELECT id, mark FROM student_mark WHERE user_id=? AND subject_id=?";
    public static final String CREATE_STUDENT_MARK = "INSERT INTO student_mark (user_id, subject_id, mark) VALUES(?,?,?)";
    public static final String UPDATE_STUDENT_MARK = "UPDATE student_mark SET mark=? WHERE id=?";

    public static final String STUDENT_ADD_FACULTY = "INSERT INTO student_faculty VALUES(?,?)";
    public static final String STUDENT_FACULTY_SIMPLE = "SELECT * FROM student_faculty WHERE student_id=? and faculty_id=?";
    public static final String GET_STUDENT_FACULTIES = "SELECT sf.faculty_id, f.* FROM student_faculty sf LEFT JOIN faculty f ON sf.faculty_id=f.id where sf.student_id=?";
    public static final String GET_STUDENT_FACULTY = "SELECT * FROM faculty WHERE id=(SELECT faculty_id FROM user_db WHERE id=?)";

    public static final String GET_SUBSCRIBER_FACULTY = "SELECT s.*, sm.subject_id, sm.mark FROM user_db s LEFT JOIN student_mark sm ON " +
            "((sm.subject_id=? or sm.subject_id=?) and sm.user_id=s.id) WHERE s.faculty_id=? AND s.enabled=true";

    public static String SUBJ_CHEMISTRY = "chemistry";
    public static String SUBJ_LITERATURE = "literature";
    public static String SUBJ_MATH = "math";
    public static String SUBJ_HISTORY = "history";
    public static String SUBJ_PHYSICS = "physics";
    public static String SUBJ_BIOLOGY = "biology";
}
