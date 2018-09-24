package com.pianoschool.lms.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@Table(name = "student")

public class Student {
    @Id
    @Column(name = "student_id")
    private int studentId;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "enroll_date")
    private Date enrollDate;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "last_edit_date")
    private Date lastEditDate;

    @Column(name = "question")
    private String question;

    @Column(name = "answer")
    private String answer;


}
