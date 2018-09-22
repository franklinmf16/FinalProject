package com.finalproj.demo.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "teacherinfo")
public class Teacher{
    //todo create and last update time
    @Id
    @Column(name = "teacherid")
    private int teacherid;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "priority")
    private String priority;

    @Column(name = "enrollDate")
    private Date enrollDate;

    @Column(name = "lastEditDate")
    private Date lastEditDate;

    @Column(name = "question")
    private String question;

    @Column(name = "answer")
    private String answer;


}
