package com.finalproj.demo.service.impl;

import com.finalproj.demo.common.Const;
import com.finalproj.demo.common.ServerResponse;
import com.finalproj.demo.common.TokenCache;
import com.finalproj.demo.domain.Student;
import com.finalproj.demo.repository.StudentRepository;
import com.finalproj.demo.service.IStudentService;
import com.finalproj.demo.util.MD5Util;
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
    public ServerResponse<Student> login(String email, String password) {

        boolean checkEmail = studentRepository.existsByEmail(email);
        if (!checkEmail){
            System.out.println("con");
            return ServerResponse.createByErrorMessage("Student does not exist");
        }

        //todo password MD5
        String md5Password = MD5Util.MD5EncodeUtf8(password);

        Student student = studentRepository.findStudentByEmailAndPassword(email,md5Password);
        System.out.println(student.getEmail());
        if (student == null){
            return ServerResponse.createByErrorMessage("Password is wrong");
        }

        // set password empty
        student.setPassword(org.apache.commons.lang3.StringUtils.EMPTY);
        return ServerResponse.createBySuccess("Success", student);

    }

    @Override
    public ServerResponse<String> register(Student student){
        boolean checkEmail = studentRepository.existsByEmail(student.getEmail());
        if (checkEmail){
            return ServerResponse.createByErrorMessage("email already existed");
        }

        student.setEnrollDate(new Date());
        student.setLastEditDate(new Date());
        student.setRoles(Const.Role.ROLE_STUDENT);
        // MD5 encoding
        student.setPassword(MD5Util.MD5EncodeUtf8(student.getPassword()));

        Student studentInserted = studentRepository.saveAndFlush(student);
        if(studentInserted == null){
            return ServerResponse.createByErrorMessage("Fail to register");
        }

        return ServerResponse.createBySuccess("success to register");
    }

    //check if email has been used
    @Override
    public ServerResponse<String> checkValid(String email) {
        boolean checkEmail = studentRepository.existsByEmail(email);
        if(!checkEmail){
            return ServerResponse.createBySuccess("not such email");
        }
        return ServerResponse.createBySuccess("is valid email");
    }


    @Override
    public ServerResponse selectQuestion(String email) {
        ServerResponse validResponse = this.checkValid(email);
        if(!validResponse.isSuccess()){
            return ServerResponse.createByErrorMessage("There is no this user");
        }
        Student studentByEmail = studentRepository.findStudentByEmail(email).get();
        if(org.apache.commons.lang3.StringUtils.isNotBlank(studentByEmail.getQuestion())){
            return ServerResponse.createBySuccess(studentByEmail.getQuestion());
        }

        return ServerResponse.createByErrorMessage("Question is blank");

    }

    @Override
    public ServerResponse checkAnswer(String email, String question, String answer) {
        Student resultCount = studentRepository.findStudentByEmailAndQuestionAndAnswer(email, question, answer);
        System.out.println(resultCount);

        if (resultCount == null){
            return ServerResponse.createByErrorMessage("wrong answer");
        }

        String forgetToken = UUID.randomUUID().toString();

        TokenCache.setKey(TokenCache.TOKEN_PREFIX+email,forgetToken);
        String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX+email);

        return ServerResponse.createBySuccess(forgetToken);

    }

    @Override
    public ServerResponse<String> forgetResetPassword(String email, String passwordNew, String forgetToken){
        if (StringUtils.isBlank(forgetToken)){
            return ServerResponse.createByErrorMessage("no token");
        }

        ServerResponse validResponse = this.checkValid(email);
        if(!validResponse.isSuccess()){
            return ServerResponse.createByErrorMessage("There is no this user");
        }

        String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX+email);
        if(StringUtils.isBlank(token)){
            return ServerResponse.createByErrorMessage("Token is expired");
        }

        if(StringUtils.equals(forgetToken, token)){
            //Locate student objects
            Student locatedStudent = studentRepository.findStudentByEmail(email).get();
            //Encode new password
            String md5Password = MD5Util.MD5EncodeUtf8(passwordNew);
            //set the new password
            locatedStudent.setPassword(md5Password);
            //save
            Student student = studentRepository.saveAndFlush(locatedStudent);
            if (student!= null){
                return ServerResponse.createBySuccess("success to update password");
            }
        }else {
            return ServerResponse.createByErrorMessage("token is wrong, please get a new token");
        }
        return ServerResponse.createByErrorMessage("fail to update the new password");

    }

    @Override
    public ServerResponse<String> resetPassword(String passwordOld, String passwordNew, Student student) {
        Student selectedStudent = studentRepository.findStudentByEmailAndPassword(student.getEmail(),MD5Util.MD5EncodeUtf8(passwordOld));
        if (selectedStudent == null){
            ServerResponse.createByErrorMessage("Old password wrong");
        }
        selectedStudent.setPassword(MD5Util.MD5EncodeUtf8(passwordNew));
        Student newStudent = studentRepository.saveAndFlush(selectedStudent);
        if(newStudent != null){
            return ServerResponse.createBySuccess("success update password");
        }
        return ServerResponse.createByErrorMessage("fail to update password");
    }

    @Override
    public ServerResponse<Student> updateInformation(Student student) {
        //if email needed to be updated, email has to check
        String resetEmail = student.getEmail();
        // check is student reset its email
        int studentid = student.getStudentid();
        Student studentInSystem = studentRepository.findById(studentid).get();
        String emailInSystem = studentInSystem.getEmail();

        // case2::student updated his email
        if (!resetEmail.equals(emailInSystem)){
            ServerResponse<String> validResponse = checkValid(resetEmail);
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






















