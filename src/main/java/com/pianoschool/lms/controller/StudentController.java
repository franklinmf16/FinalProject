package com.pianoschool.lms.controller;


import com.pianoschool.lms.common.Const;
import com.pianoschool.lms.common.ServerResponse;
import com.pianoschool.lms.domain.Student;
import com.pianoschool.lms.service.StudentService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ServerResponse<String> register(@RequestBody Student student) {
        ServerResponse<String> response = studentService.register(student);
        return response;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ServerResponse<Student> login(String email, String password, HttpSession session) {
        ServerResponse<Student> response = studentService.login(email, password);
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public ServerResponse<String> logout(HttpSession session) {
        if (session.getAttribute(Const.CURRENT_USER) == null){
            return ServerResponse.createByErrorMessage("do not need to log out");
        }

        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess("log out success");
    }

    @RequestMapping(value = "getquestion", method = RequestMethod.GET)
    public ServerResponse<String> forgetQuestion(String email) {
        return studentService.selectQuestion(email);
    }

    @RequestMapping(value = "answer", method = RequestMethod.GET)
    public ServerResponse<String> forgetCheckAnswer(String email, String answer) {
        return studentService.checkAnswer(email, answer);
    }

    @RequestMapping(value = "resetwithanswer", method = RequestMethod.GET)
    public ServerResponse<String> forgetResetPassword(String email, String passwordNew, String forgetToken) {
        return studentService.forgetResetPassword(email, passwordNew, forgetToken);
    }

    @RequestMapping(value = "resetpasswordsession", method = RequestMethod.GET)
    public ServerResponse<String> resetPassword(HttpSession session, String passwordOld, String passwordNew) {
        Student student = (Student) session.getAttribute(Const.CURRENT_USER);

        if (student == null) {
            ServerResponse.createByErrorMessage("Student has not logged in");
        }

        return studentService.resetPassword(passwordOld, passwordNew, student);
    }

    @RequestMapping(value = "updateinfomation", method = RequestMethod.POST)
    public ServerResponse<Student> updateInformation(HttpSession session, @RequestBody Student student) {
        //see if the student has logged in
        Student currentStudent = (Student) session.getAttribute(Const.CURRENT_USER);

        if (currentStudent == null) {
            return ServerResponse.createByErrorMessage("Student has not logged in");
        }

        //protect id being changed
        student.setStudentId(currentStudent.getStudentId());
        student.setPassword(currentStudent.getPassword());

        // email must exist
        if (student.getEmail() == null) {
            student.setEmail(currentStudent.getEmail());
        }
        //update
        ServerResponse<Student> response = studentService.updateInformation(student);
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }


    @RequestMapping(value = "mycourse", method = RequestMethod.GET)
    public ServerResponse myInformation(HttpSession session){
        Student currentStudent = (Student) session.getAttribute(Const.CURRENT_USER);

        if (currentStudent == null) {
            return ServerResponse.createByErrorMessage("Student has not logged in");
        }

        ServerResponse result = studentService.getMyCourse(currentStudent.getStudentId());

        return result;
    }

    @RequestMapping(value = "mynewfeedback", method = RequestMethod.GET)
    public ServerResponse getfeedback(HttpSession session){
        Student currentStudent = (Student) session.getAttribute(Const.CURRENT_USER);

        if (currentStudent == null) {
            return ServerResponse.createByErrorMessage("Student has not logged in");
        }

        ServerResponse result = studentService.getFeedback(currentStudent.getStudentId());

        return result;
    }

    @RequestMapping(value = "myfeedbacklist", method = RequestMethod.GET)
    public ServerResponse getFeedbackList(HttpSession session){
        Student currentStudent = (Student) session.getAttribute(Const.CURRENT_USER);

        if (currentStudent == null) {
            return ServerResponse.createByErrorMessage("Student has not logged in");
        }

        ServerResponse result = studentService.getFeedbackList(currentStudent.getStudentId());

        return result;
    }

    @RequestMapping(value = "myteacher", method = RequestMethod.GET)
    public ServerResponse myTeacher(HttpSession session){
        Student currentStudent = (Student) session.getAttribute(Const.CURRENT_USER);

        if (currentStudent == null) {
            return ServerResponse.createByErrorMessage("Student has not logged in");
        }

        ServerResponse result = studentService.getTeacherName(currentStudent.getStudentId());

        return result;
    }




}
