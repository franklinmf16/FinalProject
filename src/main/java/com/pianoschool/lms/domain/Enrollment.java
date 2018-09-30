package com.pianoschool.lms.domain;


import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "enrollment")
public class Enrollment {
    @Id
    @Column(name = "id")
    private int enrollmentId;

    @Column(name = "student_id")
    private int studentId;

    @Column(name = "teacher_id")
    private int teacherId;

    @Column(name = "course_id")
    private int courseId;

    @Column(name = "done")
    private boolean done;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "last_edit_date")
    private Date lastEditDate;

//    private String fullName;
//
//    private String courseName;



}
