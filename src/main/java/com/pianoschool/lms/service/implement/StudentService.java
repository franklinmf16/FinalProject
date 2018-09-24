package com.pianoschool.lms.service.implement;

import com.pianoschool.lms.common.ServerResponse;
import com.pianoschool.lms.domain.Student;
import com.pianoschool.lms.repository.StudentRepository;
import com.pianoschool.lms.service.IStudentService;
import com.pianoschool.lms.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class StudentService implements IStudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public ServerResponse<String> checkValid(String email) {
        boolean isExist = studentRepository.existsByEmail(email);
        if (!isExist) {
            ServerResponse.createBySuccess("is valid email");
        }
        return ServerResponse.createByErrorMessage("email has already existed");
    }


    @Override
    public ServerResponse<String> register(Student student) {
        if (student.getEmail() == null) {
            return ServerResponse.createByErrorMessage("Can't register without email");
        }
        ServerResponse<String> isValidEmail = checkValid(student.getEmail());
        if (isValidEmail.isSuccess()) {
            return ServerResponse.createByErrorMessage("email has already existed");
        }

        if (student.getFullName() == null){
            student.setFullName("Full name unset");
        }
        if (student.getPhone() == null){
            student.setPhone("phone name unset");
        }

        student.setEnrollDate(new Date());
        student.setLastEditDate(new Date());
        student.setCreateDate(new Date());
        student.setPassword(MD5Util.MD5EncodeUtf8(student.getPassword()));
        studentRepository.saveAndFlush(student);
        return ServerResponse.createBySuccess("success to register");

    }


}
