package com.pianoschool.lms.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

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
    @JsonIgnore
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "enroll_date")
    private Date enrollDate;

    @Column(name = "create_date")
    @JsonIgnore
    private Date createDate;

    @Column(name = "last_edit_date")
    @JsonIgnore
    private Date lastEditDate;

    @Column(name = "question")
    @JsonIgnore
    private String question;

    @Column(name = "answer")
    @JsonIgnore
    private String answer;


}
