package com.pianoschool.lms.repository;

import com.pianoschool.lms.domain.Enrollment;
import com.pianoschool.lms.domain.TeacherFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherFeedbackRepository extends JpaRepository<TeacherFeedback, Integer> {


}

