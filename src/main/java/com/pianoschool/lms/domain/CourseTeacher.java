package com.pianoschool.lms.domain;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@Table(name = "course_teacher")
public class CourseTeacher {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "course_id")
    private int course_id;

    @Column(name = "teacher_id")
    private int teacherId;

    @Column(name = "active")
    private boolean active;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "last_edit_date")
    private Date lastEditDate;



}
