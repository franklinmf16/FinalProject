package com.finalproj.demo.domain;

import javax.xml.crypto.Data;

public class Student {

    private int studentid;
    private String email;
    private String englishName;
    private String course;
    private String teacher;
    private Data enrollDate;
    private Data lastEditDate;
    private String password;

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Data getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(Data enrollDate) {
        this.enrollDate = enrollDate;
    }

    public Data getLastEditDate() {
        return lastEditDate;
    }

    public void setLastEditDate(Data lastEditDate) {
        this.lastEditDate = lastEditDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
