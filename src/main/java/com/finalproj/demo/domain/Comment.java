package com.finalproj.demo.domain;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "comments")
public class Comment {
    @Id
    @Column(name = "commentid")
    private int commentid;
    @Column(name = "teacherid")
    private int teacherid;
    @Column(name = "studentid")
    private int studentid;
    @Column(name = "courseid")
    private int courseid;
    @Column(name = "theComment")
    private String theComment;
    @Column(name = "lastEditDate")
    private Date lastEditDate;

}
