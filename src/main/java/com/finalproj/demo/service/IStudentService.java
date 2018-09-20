package com.finalproj.demo.service;

import com.finalproj.demo.common.ServerResponse;
import com.finalproj.demo.domain.Student;
import com.finalproj.demo.domain.Teacher;

public interface IStudentService {

    ServerResponse<Student> login(String username, String password);
    public ServerResponse<String> register(Student student);
    public ServerResponse<String> checkValid(String email);
    public ServerResponse selectQuestion(String email);
    public ServerResponse checkAnswer(String email, String question, String answer);
    public ServerResponse<String> forgetResetPassword(String email, String passwordNew, String forgetToken);
    public ServerResponse<String> resetPassword(String passwordOld, String passwordNew, Student student);
    public ServerResponse<Student> updateInformation(Student student);
}
