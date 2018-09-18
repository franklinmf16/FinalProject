package com.finalproj.demo.repository;

import com.finalproj.demo.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findStudentByEmail(String email);
    Student findStudentByEmailAndPassword(String username, String password);
    boolean existsByEmail(String email);
    Student findStudentByEmailAndQuestionAndAnswer(String email, String question, String answer);


}
