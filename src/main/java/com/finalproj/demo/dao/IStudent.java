package com.finalproj.demo.dao;

import com.finalproj.demo.domain.Student;
import org.springframework.context.annotation.Bean;

import java.util.List;

public interface IStudent {
    List<Student> queryStudent();
    //search
    Student searchByEmail(String email);
    // insert
    int insertStudentbyId(Student student);
    //update
    int updateStudent(Student student);
    //delete
    int deleteStudent(Integer studentid);

}
