package com.finalproj.demo.service.impl;

import com.finalproj.demo.common.ServerResponse;
import com.finalproj.demo.common.TokenCache;
import com.finalproj.demo.domain.Comment;
import com.finalproj.demo.domain.Student;
import com.finalproj.demo.domain.Teacher;
import com.finalproj.demo.repository.CommentsRepository;
import com.finalproj.demo.repository.StudentRepository;
import com.finalproj.demo.repository.TeacherRepository;
import com.finalproj.demo.service.ITeacherService;
import com.finalproj.demo.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.apache.bcel.generic.RET;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TeacherService implements ITeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public ServerResponse<String> checkValid(String email) {
        boolean checkEmail = teacherRepository.existsByEmail(email);
        if(!checkEmail){
            return ServerResponse.createByErrorMessage("there is no such email");
        }
        // exist
        return ServerResponse.createBySuccess("is valid email");
    }

    @Override
    public ServerResponse<Teacher> login(String email, String password) {
        ServerResponse<String> checkValid = checkValid(email);

        if (!checkValid.isSuccess()){
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
    public ServerResponse<String> register(Teacher teacher) {
        boolean isExistsByEmail = teacherRepository.existsByEmail(teacher.getEmail());
        if(isExistsByEmail){
            ServerResponse.createByErrorMessage("Email has already existed");
        }
        teacher.setPriority("1");
        teacher.setEnrollDate(new Date());
        teacher.setLastEditDate(new Date());
        teacher.setPassword(MD5Util.MD5EncodeUtf8(teacher.getPassword()));

        Teacher teacherToInsert = teacherRepository.saveAndFlush(teacher);
        if (teacherToInsert == null){
            return ServerResponse.createByErrorMessage("Fail to register");
        }

        return ServerResponse.createBySuccess("success to register");

    }


    @Override
    public ServerResponse selectQuestion(String email) {
        ServerResponse validResponse = this.checkValid(email);
        if(!validResponse.isSuccess()){
            return ServerResponse.createByErrorMessage("These is not this user");
        }

        Teacher teacherByEmail = teacherRepository.findTeacherByEmail(email);
        if(StringUtils.isNotBlank(teacherByEmail.getQuestion())){
            return ServerResponse.createBySuccess("here is the question", teacherByEmail.getQuestion());
        }

        return ServerResponse.createByErrorMessage("Question is blank");

    }

    @Override
    public ServerResponse checkAnswer(String email, String question, String answer) {
        Teacher resultCount = teacherRepository.findTeacherByEmailAndQuestionAndAndAnswer(email,question,answer);

        if(resultCount == null){
            return ServerResponse.createByErrorMessage("worng answer");
        }

        String generatedToken = UUID.randomUUID().toString();
        TokenCache.setKey(TokenCache.TOKEN_PREFIX+email, generatedToken);

        return ServerResponse.createBySuccess(generatedToken);

    }

    @Override
    public ServerResponse<String> forgetResetPassword(String email, String passwordNew, String forgetToken) {
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

        if (StringUtils.equals(forgetToken,token)){
            Teacher locatedTeacher = teacherRepository.findTeacherByEmail(email);

            String md5password = MD5Util.MD5EncodeUtf8(passwordNew);
            locatedTeacher.setPassword(md5password);
            Teacher teacher = teacherRepository.saveAndFlush(locatedTeacher);
            return ServerResponse.createBySuccess("success to update password");

        }else {
            return ServerResponse.createByErrorMessage("token is wrong, please get a new token");
        }


    }

    @Override
    public ServerResponse<String> resetPassword(String passwordOld, String passwordNew, Teacher teacher) {
        Teacher selectedTeacher = teacherRepository.findTeacherByEmailAndPassword(teacher.getEmail(),teacher.getPassword());
        if (selectedTeacher == null){
            ServerResponse.createByErrorMessage("Old password wrong");
        }
        selectedTeacher.setPassword(MD5Util.MD5EncodeUtf8(passwordNew));
        Teacher newTeacher = teacherRepository.saveAndFlush(selectedTeacher);

        return ServerResponse.createBySuccess("success to update password");

    }

    @Override
    public ServerResponse makeComment(Integer teacherid, Integer studentid, String comment) {
        // get student

        Student studentTOComment = studentRepository.findById(studentid).get();
        Comment commentToMake = new Comment();
        commentToMake.setLastEditDate(new Date());
        commentToMake.setTheComment(comment);
        commentToMake.setStudentid(studentid);
        commentToMake.setTeacherid(teacherid);
        //todo get course id
        commentsRepository.saveAndFlush(commentToMake);

        return ServerResponse.createBySuccess("success to make comment");


    }

    @Override
    public ServerResponse listStudent(Teacher teacher) {
        // 通过老师的id获取学生的信息
        int teacherid = teacher.getTeacherid();
        // 找db 把老师对应的学生都找出来
        Boolean isExist = studentRepository.existsByTeacherid(teacherid);
        System.out.println(isExist);

        List<Student> studentList = studentRepository.findStudentByTeacherid(teacherid);


        if (studentList==null){
            return ServerResponse.createByErrorMessage("fail to get sutdents");
        }

        List<Student> students = new ArrayList<>();
        for (Student student : studentList) {
            Student inforToShow = new Student();
            inforToShow.setStudentid(student.getStudentid());
            inforToShow.setChineseName(student.getChineseName());
            inforToShow.setTeacher(student.getTeacher());
            inforToShow.setEmail(student.getEmail());
            inforToShow.setEnrollDate(student.getEnrollDate());
            inforToShow.setCourse(student.getCourse());
            students.add(inforToShow);
        }

        return ServerResponse.createBySuccess("get students", students);

    }





}
