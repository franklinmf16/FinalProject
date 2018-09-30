package com.pianoschool.lms.controller;

import com.pianoschool.lms.common.Const;
import com.pianoschool.lms.common.ServerResponse;
import com.pianoschool.lms.domain.Teacher;
import com.pianoschool.lms.service.TeacherService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import sun.util.resources.cldr.en.TimeZoneNames_en_ZA;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

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

//    @RequestMapping(value = "all", method = RequestMethod.GET, produces = "application/json")
//    public List<Teacher> all(HttpSession session) {
//
//        if (session.getAttribute(Const.CURRENT_USER) == null) {
//            throw new RuntimeException("Please Login first.");
//        }
//
//        return teacherService.findAll();
//    }


    @RequestMapping(value = "alls", method = RequestMethod.GET)
    public ServerResponse alls(HttpSession session) {

        if (session.getAttribute(Const.CURRENT_USER) == null) {
            return ServerResponse.createByErrorMessage("Please Login first.");
        }

        return teacherService.finds();
    }

    @RequestMapping(value = "feedback/{studentId}", method = RequestMethod.GET, produces = "application/json")
    public ServerResponse all(HttpSession session, @PathVariable("studentId") int studentId) {

        Teacher teacher = (Teacher) session.getAttribute(Const.CURRENT_USER);

        if ( teacher == null) {
            return ServerResponse.createByErrorMessage("Please Login first.");
        }

        return teacherService.searchFeedback(teacher.getTeacherId(), studentId);
    }


}
