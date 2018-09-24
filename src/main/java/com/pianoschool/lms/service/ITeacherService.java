package com.pianoschool.lms.service;

import com.pianoschool.lms.common.ServerResponse;
import com.pianoschool.lms.domain.Teacher;

public interface ITeacherService {
    public ServerResponse<String> register(Teacher teacher);
    public ServerResponse<String> checkValid(String email);
}
