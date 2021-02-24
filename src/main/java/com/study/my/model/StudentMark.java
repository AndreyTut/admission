package com.study.my.model;

public class StudentMark {
    private Integer id;
    private User user;
    private Subject subject;
    private Integer mark;

    public StudentMark(Subject subject) {
        this.subject = subject;
    }

    public StudentMark() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }
}
