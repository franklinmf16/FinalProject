package com.pianoschool.lms.controller;

import com.pianoschool.lms.common.Const;
import com.pianoschool.lms.common.ServerResponse;
import com.pianoschool.lms.domain.Teacher;
import com.pianoschool.lms.service.ITeacherService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private ITeacherService teacherService;

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ServerResponse<String> register(@RequestBody Teacher teacher){
        return teacherService.register(teacher);
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ServerResponse<Teacher> login(String email, String password, HttpSession session) {
        ServerResponse<Teacher> response = teacherService.login(email, password);
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
        return ServerResponse.createBySuccess("Success log out");
    }






}
