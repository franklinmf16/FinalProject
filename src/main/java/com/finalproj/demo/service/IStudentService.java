package com.finalproj.demo.service;

import com.finalproj.demo.domain.Student;

import java.util.List;

public interface IStudentService {
    List<Student> getStudentList();
    Student getStudentByEmail(String email);
    boolean addStudent(Student student);
    boolean updateStudent(Student student);
    boolean deleteStudent(Integer studentid);

}
