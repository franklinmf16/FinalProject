package com.pianoschool.lms.controller;


import com.pianoschool.lms.common.Const;
import com.pianoschool.lms.common.ServerResponse;
import com.pianoschool.lms.domain.Student;
import com.pianoschool.lms.service.IStudentService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private IStudentService studentService;

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
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess("log out success");
    }

    @RequestMapping(value = "getquestion", method = RequestMethod.GET)
    public ServerResponse<String> forgetQuestion(String email) {
        return studentService.selectQuestion(email);
    }







}
