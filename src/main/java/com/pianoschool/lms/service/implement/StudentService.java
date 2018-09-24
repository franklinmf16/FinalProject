package com.pianoschool.lms.service.implement;

import com.pianoschool.lms.common.ServerResponse;
import com.pianoschool.lms.common.TokenCache;
import com.pianoschool.lms.domain.Student;
import com.pianoschool.lms.repository.StudentRepository;
import com.pianoschool.lms.service.IStudentService;
import com.pianoschool.lms.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

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
    public ServerResponse checkAnswer(String email, String answer) {

        Student resultCount = studentRepository.findStudentByEmailAndAndAnswer(email, answer);

        if (resultCount == null) {
            return ServerResponse.createByErrorMessage("wrong answer");
        }

        String forgetToken = UUID.randomUUID().toString();


        TokenCache.setKey(TokenCache.TOKEN_PREFIX + email, forgetToken);
        String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX + email);

        return ServerResponse.createBySuccess(forgetToken);

    }

    @Override
    public ServerResponse<String> forgetResetPassword(String email, String passwordNew, String forgetToken) {
        if (StringUtils.isBlank(forgetToken)) {
            return ServerResponse.createByErrorMessage("no token");
        }

        ServerResponse validResponse = this.isExist(email);
        if (validResponse.isSuccess()) {
            return ServerResponse.createByErrorMessage("There is no this user");
        }

        String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX + email);
        if (StringUtils.isBlank(token)) {
            return ServerResponse.createByErrorMessage("Token is expired");
        }

        if (StringUtils.equals(forgetToken, token)) {
            //Locate student objects
            Student locatedStudent = studentRepository.findStudentByEmail(email).get();
            //Encode new password
            String md5Password = MD5Util.MD5EncodeUtf8(passwordNew);
            //set the new password
            locatedStudent.setPassword(md5Password);
            //save
            Student student = studentRepository.saveAndFlush(locatedStudent);
            return ServerResponse.createBySuccess("success update password");
        } else {
            return ServerResponse.createByErrorMessage("token is wrong, please get a new token");
        }

    }

    @Override
    public ServerResponse<String> resetPassword(String passwordOld, String passwordNew, Student student) {
        Student selectStudent = studentRepository.findStudentByEmailAndPassword(student.getEmail(), MD5Util.MD5EncodeUtf8(passwordOld));

        if (selectStudent == null) {
            return ServerResponse.createByErrorMessage("fail to update password");
        }

        selectStudent.setPassword(MD5Util.MD5EncodeUtf8(passwordNew));
        studentRepository.saveAndFlush(selectStudent);
        return ServerResponse.createBySuccess("success update password");

    }

    @Override
    public ServerResponse<Student> updateInformation(Student student) {
        String resetEmail = student.getEmail();
        int studentId = student.getStudentId();
        Student studentInSystem = studentRepository.findById(studentId).get();
        String emailInSystem = studentInSystem.getEmail();

        // case2::student updated his email
        if (!resetEmail.equals(emailInSystem)){
            ServerResponse<String> validResponse = isExist(resetEmail);
            if (!validResponse.isSuccess()){
                return ServerResponse.createByErrorMessage("email has already exists");
            }
            student.setLastEditDate(new Date());
            studentRepository.saveAndFlush(student);
            return  ServerResponse.createBySuccess("updated",student);
        }

        // case2::student does not update his email
        student.setLastEditDate(new Date());
        studentRepository.saveAndFlush(student);

        return ServerResponse.createBySuccess("updated",student);
    }


}
