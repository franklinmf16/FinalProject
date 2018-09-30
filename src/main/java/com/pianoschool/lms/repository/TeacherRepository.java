package com.pianoschool.lms.repository;

import com.pianoschool.lms.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    boolean existsByEmail(String email);
//    Teacher findTeacherByEmail(String email);
    Teacher findTeacherByEmailAndPassword(String email, String password);
//    Teacher findTeacherByEmailAndQuestionAndAndAnswer(String email, String question, String answer);
//    Teacher findTeacherByEmailaAndAnswer(String email, String answer);

}
