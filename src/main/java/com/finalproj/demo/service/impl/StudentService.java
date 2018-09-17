package com.finalproj.demo.service.impl;

import com.finalproj.demo.dao.IStudent;
import com.finalproj.demo.domain.Student;
import com.finalproj.demo.repository.StudentRepo;
import com.finalproj.demo.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements IStudentService {
//
//    @Autowired
//    private IStudent studentDado;

    @Autowired
    private StudentRepo studentRepo;

    @Override
    public List<Student> getStudentList() {
        List<Student> foundStudents = new ArrayList<>();
        studentRepo.findAll().forEach(foundStudents::add);
        return foundStudents;
    }

    @Override
    public Student getStudentByEmail(String email) {
        return studentRepo.findStudentByEmail(email).orElseThrow(() -> new RuntimeException("Cannot find the student."));
    }

    @Transactional
    @Override
    public boolean addStudent(Student student) {
        if (student.getEmail() == null){
            return false;
        }
        student.setEnrollDate(new Date());
        student.setLastEditDate(new Date());
        studentRepo.saveAndFlush(student);
        return true;

    }

    @Transactional
    @Override
    public boolean updateStudent(Student student) {

        if (studentRepo.findById(student.getStudentid()) == null){
            return false;
        }

        student.setLastEditDate(new Date());
        studentRepo.saveAndFlush(student);
        return true;
    }

    @Transactional
    @Override
    public boolean deleteStudent(Integer studentid) {
        studentRepo.deleteById(studentid);
        return true;
    }
}
