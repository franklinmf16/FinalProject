package com.finalproj.demo.service.impl;

import com.finalproj.demo.common.ServerResponse;
import com.finalproj.demo.domain.Student;
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
    public ServerResponse<Teacher> login(String username, String password) {
        return null;
    }

    @Override
    public ServerResponse<String> register(Teacher teacher) {
        return null;
    }

    @Override
    public ServerResponse<String> checkValid(String email) {
        return null;
    }

    @Override
    public ServerResponse selectQuestion(String email) {
        return null;
    }

    @Override
    public ServerResponse checkAnswer(String email, String question, String answer) {
        return null;
    }

    @Override
    public ServerResponse<String> forgetResetPassword(String email, String passwordNew, String forgetToken) {
        return null;
    }

    @Override
    public ServerResponse<String> resetPassword(String passwordOld, String passwordNew, Student student) {
        return null;
    }

    @Override
    public ServerResponse<Student> updateInformation(Student student) {
        return null;
    }
}
