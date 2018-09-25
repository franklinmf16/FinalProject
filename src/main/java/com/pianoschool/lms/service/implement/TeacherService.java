package com.pianoschool.lms.service.implement;

import com.pianoschool.lms.common.ServerResponse;
import com.pianoschool.lms.domain.Teacher;
import com.pianoschool.lms.repository.TeacherRepository;
import com.pianoschool.lms.service.ITeacherService;
import com.pianoschool.lms.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
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

    @Override
    public ServerResponse<Teacher> login(String email, String password) {
        ServerResponse<String> checkValid = checkValid(email);

        if (checkValid.isSuccess()){
            return ServerResponse.createByErrorMessage("email is wrong");
        }

        String md5password = MD5Util.MD5EncodeUtf8(password);

        Teacher teacher = teacherRepository.findTeacherByEmailAndPassword(email,md5password);
        if (teacher==null){
            return ServerResponse.createByErrorMessage("Password is wrong");
        }

        teacher.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess("Success log in as teacher",teacher);

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
    public ServerResponse<String> resetPassword(String passwordOld, String passwordNew, Teacher teacher) {
        return null;
    }

    @Override
    public ServerResponse getStudentEnrollmentInfo(Integer teacherId) {
        // 通过teacherId查找老师自己学生的enrollemmt

        return null;
    }


}
