package com.pianoschool.lms.service;

import com.pianoschool.lms.common.ServerResponse;
import com.pianoschool.lms.domain.Student;

public interface IStudentService {
    public ServerResponse<String> register(Student student);
    public ServerResponse<String> isExist(String email);
    ServerResponse<Student> login(String email, String password);

    public ServerResponse selectQuestion(String email);
    public ServerResponse checkAnswer(String email, String answer);
    public ServerResponse<String> forgetResetPassword(String email, String passwordNew, String forgetToken);
    public ServerResponse<String> resetPassword(String passwordOld, String passwordNew, Student student);
    public ServerResponse<Student> updateInformation(Student student);


}
