package com.finalproj.demo.domain;

import lombok.Data;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
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
    @Column(name = "chineseName")
    private String chineseName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "roles")
    private Integer roles;
    @Column(name = "question")
    private String question;
    @Column(name = "answer")
    private String answer;


    public Student() {
    }

}
