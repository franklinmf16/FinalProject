package com.pianoschool.lms.domain;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@Table(name = "feedback")
public class Feedback {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "enrollment_id")
    private int enrollmentId;

    @Column(name = "feedback")
    private String feedback;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "last_edit_date")
    private Date lastEditDate;

}
