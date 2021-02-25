package com.study.my.model;

import java.util.List;

public class Faculty {
    private Integer id;
    private String nameEn;
    private String nameUa;
    private Integer vacancyBudge;
    private Integer vacancyContr;
    private List<Subject> subjects;
    private List<User> students;
    private boolean finalized = false;

    public Faculty(Integer id, String nameEn, String nameUa, Integer vacancyBudge, Integer vacancyContr, List<Subject> subjects, List<User> students) {
        this.id = id;
        this.nameEn = nameEn;
        this.nameUa = nameUa;
        this.vacancyBudge = vacancyBudge;
        this.vacancyContr = vacancyContr;
        this.subjects = subjects;
        this.students = students;
    }

    public static FacultyBuilder builder() {
        return new FacultyBuilder();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameUa() {
        return nameUa;
    }

    public void setNameUa(String nameUa) {
        this.nameUa = nameUa;
    }

    public Integer getVacancyBudge() {
        return vacancyBudge;
    }

    public void setVacancyBudge(Integer vacancyBudge) {
        this.vacancyBudge = vacancyBudge;
    }

    public Integer getVacancyContr() {
        return vacancyContr;
    }

    public void setVacancyContr(Integer vacancyContr) {
        this.vacancyContr = vacancyContr;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<User> getStudents() {
        return students;
    }

    public void setStudents(List<User> students) {
        this.students = students;
    }

    public boolean isFinalized() {
        return finalized;
    }

    public void setFinalized(boolean finalized) {
        this.finalized = finalized;
    }

    public static class FacultyBuilder {
        private Integer id;
        private String nameEn;
        private String nameUa;
        private Integer vacancyBudge;
        private Integer vacancyContr;
        private List<Subject> subjects;
        private List<User> students;

        FacultyBuilder() {
        }


        public FacultyBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public FacultyBuilder nameEn(String nameEn) {
            this.nameEn = nameEn;
            return this;
        }

        public FacultyBuilder nameUa(String nameUa) {
            this.nameUa = nameUa;
            return this;
        }

        public FacultyBuilder vacancyBudge(Integer vacancyBudge) {
            this.vacancyBudge = vacancyBudge;
            return this;
        }

        public FacultyBuilder vacancyContr(Integer vacancyContr) {
            this.vacancyContr = vacancyContr;
            return this;
        }

        public FacultyBuilder subjects(List<Subject> subjects) {
            this.subjects = subjects;
            return this;
        }

        public FacultyBuilder students(List<User> students) {
            this.students = students;
            return this;
        }

        public Faculty build() {
            return new Faculty(id, nameEn, nameUa, vacancyBudge, vacancyContr, subjects, students);
        }
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", nameEn='" + nameEn + '\'' +
                ", nameUa='" + nameUa + '\'' +
                ", vacancyBudge=" + vacancyBudge +
                ", vacancyContr=" + vacancyContr +
                ", subjects=" + subjects +
                ", students=" + students +
                '}';
    }
}
