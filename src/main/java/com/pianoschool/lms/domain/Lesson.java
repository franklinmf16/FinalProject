package com.pianoschool.lms.domain;


import lombok.Data;
import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@Table(name = "lesson")
public class Lesson {

    @Id
    @Column(name = "lesson_id")
    private int lessonId;

    @Column(name = "course_id")
    private int courseId;

    @Column(name = "lesson_type")
    private String lessonType;

    @Column(name = "lesson_code")
    private String lessonCode;

}
