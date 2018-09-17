package com.finalproj.demo.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "studentinfo")
public class Student {
    @Id
    @Column(name = "studentid")
    private int studentid;
    @Column(name = "email")
    private String email;
    @Column(name = "englishName")
    private String englishName;
    @Column(name = "course")
    private String course;
    @Column(name = "teacher")
    private String teacher;
    @Column(name = "enrollDate")
    private Date enrollDate;
    @Column(name = "lastEditTime")
    private Date lastEditDate;
    @Column(name = "password")
    private String password;

    public Student() {
    }

    public Student(String email, String englishName, String course, String teacher, String password) {
        this.email = email;
        this.englishName = englishName;
        this.course = course;
        this.teacher = teacher;
        this.password = password;
    }
}
