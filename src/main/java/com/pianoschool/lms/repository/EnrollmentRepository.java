package com.pianoschool.lms.repository;

import com.pianoschool.lms.domain.Enrollment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer>{
    @Query("select enrollment from enrollment where course_teacher_id in (select id from course_teacher where teacher_id = :teacherId)")
    Integer findEnrollmentByTeacherId(@Param("teacherId") Integer teacherId);
}
