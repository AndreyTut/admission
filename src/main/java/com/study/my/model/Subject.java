package com.study.my.model;

public class Subject {
    private Integer id;
    private String nameEn;
    private String nameUa;

    public Subject(Integer id, String nameEn, String nameUa) {
        this.id = id;
        this.nameEn = nameEn;
        this.nameUa = nameUa;
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

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", nameEn='" + nameEn + '\'' +
                ", nameUa='" + nameUa + '\'' +
                '}';
    }
}
