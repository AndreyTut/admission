package com.study.my.dao;

public abstract class AbstractDaoFactory {

    private static AbstractDaoFactory daoFactory;

    public abstract UserDao createUserDao();

    public abstract DiplomaDao createDiplomaDao();

    public abstract FacultyDao createFacultyDao();

    public abstract SubjectDao createSubjectDao();

    public static AbstractDaoFactory getInstance() {
        if (daoFactory == null) {
            synchronized (AbstractDaoFactory.class) {
                if (daoFactory == null) {
                    AbstractDaoFactory temp = new DaoFactory();
                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
    }
}
