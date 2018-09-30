package com.pianoschool.lms.repository;

import com.pianoschool.lms.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    boolean existsByEmail(String email);
    Student findStudentByEmailAndPassword(String email, String password);
    Optional<Student> findStudentByEmail(String email);
    Student findStudentByEmailAndAndAnswer(String email, String answer);


}


