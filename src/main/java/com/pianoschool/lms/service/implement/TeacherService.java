package com.pianoschool.lms.service.implement;

import com.pianoschool.lms.common.ServerResponse;
import com.pianoschool.lms.domain.Teacher;
import com.pianoschool.lms.repository.TeacherRepository;
import com.pianoschool.lms.service.ITeacherService;
import com.pianoschool.lms.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TeacherService implements ITeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public ServerResponse<String> checkValid(String email) {
        boolean isExist = teacherRepository.existsByEmail(email);
        if (!isExist) {
            ServerResponse.createBySuccess("is valid email");
        }
        return ServerResponse.createByErrorMessage("email has already existed");
    }

    @Override
    public ServerResponse<String> register(Teacher teacher) {
        if (teacher.getEmail() == null) {
            return ServerResponse.createByErrorMessage("Can't register without email");
        }

        ServerResponse<String> isValidEmail = checkValid(teacher.getEmail());
        if (isValidEmail.isSuccess()) {
            return ServerResponse.createByErrorMessage("email has already existed");
        }

        teacher.setCreateDate(new Date());
        teacher.setLastEditDate(new Date());
        teacher.setPassword(MD5Util.MD5EncodeUtf8(teacher.getPassword()));
        teacherRepository.saveAndFlush(teacher);
        return ServerResponse.createBySuccess("success to register as a teacher");

    }


}
