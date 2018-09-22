package com.finalproj.demo.repository;

import com.finalproj.demo.domain.Student;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findStudentByEmail(String email);
    Student findStudentByEmailAndPassword(String username, String password);
    boolean existsByEmail(String email);
    Student findStudentByEmailAndQuestionAndAnswer(String email, String question, String answer);
    List<Student> findStudentByTeacherid(Integer teacherid);


    //TODO FOR TEST
    Boolean existsByTeacherid(Integer teacherid);

}
