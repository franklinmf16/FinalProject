package com.pianoschool.lms.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Data
@Table(name = "admin")
public class Admin {
    @Id
    @Column(name = "admin_id")
    private int teacherId;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "last_edit_date")
    private Date lastEditDate;


}