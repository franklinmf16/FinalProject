package com.pianoschool.lms.domain;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@Table(name = "teacher_feedback")
public class TeacherFeedback {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "enrollment_id")
    private int enrollmentId;

    @Column(name = "comments")
    private String comments;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "last_edit_date")
    private Date lastEditDate;

}
