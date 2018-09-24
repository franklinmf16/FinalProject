package com.pianoschool.lms.service;

import com.pianoschool.lms.common.ServerResponse;
import com.pianoschool.lms.domain.Teacher;

public interface ITeacherService {
    public ServerResponse<String> register(Teacher teacher);
    public ServerResponse<String> checkValid(String email);
    ServerResponse<Teacher> login(String email, String password);
    public ServerResponse selectQuestion(String email);
    public ServerResponse checkAnswer(String email, String question, String answer);
    public ServerResponse<String> forgetResetPassword(String email, String passwordNew, String forgetToken);
    public ServerResponse<String> resetPassword(String passwordOld, String passwordNew, Teacher teacher);

}
