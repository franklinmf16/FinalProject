package com.finalproj.demo.repository;

import com.finalproj.demo.domain.Student;
import com.finalproj.demo.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    boolean existsByEmail(String email);
    Teacher findTeacherByEmail(String email);
    Teacher findTeacherByEmailAndPassword(String email, String password);
    Teacher findTeacherByEmailAndQuestionAndAndAnswer(String email, String question, String answer);



}
