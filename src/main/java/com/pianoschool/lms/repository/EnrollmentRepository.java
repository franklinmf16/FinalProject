package com.pianoschool.lms.repository;

import com.pianoschool.lms.domain.Enrollment;

import com.pianoschool.lms.domain.model.IEnrollmentStudentInfo;
import com.pianoschool.lms.domain.model.IStudentList;
import org.springframework.data.domain.Example;
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

    @Query("select s.studentId as studentId, s.fullName as fullName from Enrollment e, Student s where e.teacherId = :teacherId and s.studentId = e.studentId ")
    List<IStudentList> findStudentByTeacherId(@Param("teacherId") int teacherId);


    @Query("select e.enrollmentId from Enrollment e where e.studentId = :studentId ")
    Optional<Integer> findEnrollmentIdByStudentId(@Param("studentId") int studentId);


    @Override
    Optional<Enrollment> findById(Integer integer);

    int countEnrollmentByTeacherId(int teacherId);




}
