package com.pianoschool.lms.service;

import com.pianoschool.lms.common.ServerResponse;
import com.pianoschool.lms.domain.Student;

public interface IStudentService {
    public ServerResponse<String> register(Student student);
    public ServerResponse<String> checkValid(String email);

}
