package com.finalproj.demo.service.impl;

import com.finalproj.demo.domain.Teacher;
import com.finalproj.demo.repository.TeacherRepository;
import com.finalproj.demo.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService implements ITeacherService {

    @Autowired
    private TeacherRepository teacherRepository;


    @Override
    public List<Teacher> listTeacher() {
        List<Teacher> teachers = new ArrayList<>();
        teacherRepository.findAll().forEach(teachers::add);
        return teachers;
    }

//    @Override
//    public Teacher getTeacherById(Integer teacherid) {
//        return null;
//    }
//
//    @Override
//    public boolean addTeacher(Teacher teacher) {
//        return false;
//    }
//
//    @Override
//    public boolean updateTeacher(Teacher teacher) {
//        return false;
//    }
//
//    @Override
//    public boolean deleteTeacher(Integer teacherid) {
//        return false;
//    }
}
