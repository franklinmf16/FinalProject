package com.pianoschool.lms.repository;

import com.pianoschool.lms.domain.Enrollment;

import com.pianoschool.lms.domain.model.IEnrollmentStudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer>{


    @Query("select s.fullName as fullName, f.feedback as feedback\n" +
            "from Enrollment e, Student s, Feedback f\n" +
            "where e.studentId = s.studentId \n" +
            "and e.teacherId = :teacherId \n" +
            "and f.enrollmentId = e.enrollmentId")
    List<IEnrollmentStudentInfo> findEnrollmentStudentInfoByTeacherId(@Param("teacherId") int teacherId);


    @Query("select e.enrollmentId from Enrollment e where e.studentId = :studentId ")
    Optional<Integer> findEnrollmentIdByStudentId(@Param("studentId") int studentId);


}
