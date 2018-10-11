package com.pianoschool.lms.repository;

import com.pianoschool.lms.domain.Course;
import com.pianoschool.lms.domain.CourseTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseTeacherRepository extends JpaRepository<CourseTeacher, Integer> {

}
