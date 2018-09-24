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
    public ServerResponse<String> isExist(String email) {
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

        ServerResponse<String> isValidEmail = isExist(student.getEmail());
        if (isValidEmail.isSuccess()) {
            return ServerResponse.createByErrorMessage("email has already existed");
        }

        if (student.getFullName() == null) {
            student.setFullName("Full name unset");
        }
        if (student.getPhone() == null) {
            student.setPhone("phone name unset");
        }

        student.setEnrollDate(new Date());
        student.setLastEditDate(new Date());
        student.setCreateDate(new Date());
        student.setPassword(MD5Util.MD5EncodeUtf8(student.getPassword()));
        studentRepository.saveAndFlush(student);
        return ServerResponse.createBySuccess("success to register");

    }

    @Override
    public ServerResponse<Student> login(String email, String password) {

        boolean checkEmail = studentRepository.existsByEmail(email);
        if (!checkEmail) {
            System.out.println("wrong");
            return ServerResponse.createByErrorMessage("Student does not exist");
        }

        String md5Password = MD5Util.MD5EncodeUtf8(password);

        Student student = studentRepository.findStudentByEmailAndPassword(email, md5Password);
        if (student == null) {
            return ServerResponse.createByErrorMessage("Password is wrong");
        }

        student.setPassword(org.apache.commons.lang3.StringUtils.EMPTY);
        return ServerResponse.createBySuccess("Success", student);
    }

    @Override
    public ServerResponse selectQuestion(String email) {
        ServerResponse<String> validResponse = this.isExist(email);
        if (validResponse.isSuccess()) {
            return ServerResponse.createByErrorMessage("There is no this user");
        }
        Student studentByEmail = studentRepository.findStudentByEmail(email).get();
        if (org.apache.commons.lang3.StringUtils.isNotBlank(studentByEmail.getQuestion())) {
            return ServerResponse.createBySuccess(studentByEmail.getQuestion());
        }
        return ServerResponse.createByErrorMessage("Question is blank");

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
