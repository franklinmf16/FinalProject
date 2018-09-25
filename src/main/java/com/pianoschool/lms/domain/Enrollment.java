package com.pianoschool.lms.domain;


import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "enrollment")
public class Enrollment {
    @Id
    @Column(name = "enrollment_id")
    private int enrollmentId;

    @Column(name = "student_id")
    private String studentId;

    @Column(name = "course_teacher_id")
    private String courseTeacherId;

    @Column(name = "done")
    private Integer done;

    @Column(name = "enroll_date")
    private Date enrollDate;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "last_edit_date")
    private Date lastEditDate;



}
