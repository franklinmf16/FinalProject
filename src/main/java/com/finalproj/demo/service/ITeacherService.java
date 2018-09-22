package com.finalproj.demo.service;

import com.finalproj.demo.common.ServerResponse;
import com.finalproj.demo.domain.Student;
import com.finalproj.demo.domain.Teacher;

import java.util.List;

//todo: find student info
//todo: make comment
//todo: list comment by student id and teacher id

public interface ITeacherService {
    ServerResponse<Teacher> login(String username, String password);
    public ServerResponse<String> register(Teacher teacher);
    public ServerResponse<String> checkValid(String email);
    public ServerResponse selectQuestion(String email);
    public ServerResponse checkAnswer(String email, String question, String answer);
    public ServerResponse<String> forgetResetPassword(String email, String passwordNew, String forgetToken);
    public ServerResponse<String> resetPassword(String passwordOld, String passwordNew, Teacher teacher);
    public ServerResponse makeComment(Integer teacherid, Integer studentid, String comment);

    // TODO: 21/9/18 teacher and student


    public ServerResponse listStudent(Teacher teacher);



//    public ServerResponse makeComment(String comment);
//    public ServerResponse<Student> getStudentInfo();
}
