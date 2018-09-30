package com.pianoschool.lms.controller;

import com.pianoschool.lms.common.Const;
import com.pianoschool.lms.common.ServerResponse;
import com.pianoschool.lms.domain.Feedback;
import com.pianoschool.lms.domain.Teacher;
import com.pianoschool.lms.service.TeacherService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    /**
     * Teacher Register
     *
     * @param teacher
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ServerResponse<String> register(@RequestBody Teacher teacher) {
        return teacherService.register(teacher);
    }


    /**
     * teacehr login
     *
     * @param email
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ServerResponse<Teacher> login(String email, String password, HttpSession session) {
        ServerResponse<Teacher> response = teacherService.login(email, password);
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }

    /**
     * teacher logout
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public ServerResponse<String> logout(HttpSession session) {

        if (session.getAttribute(Const.CURRENT_USER) == null) {
            return ServerResponse.createByErrorMessage("do not need to log out");
        }

        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess("Success log out");
    }


    /**
     * get teacher's students feedback
     *
     * @param session
     * @param studentId
     * @return
     */
    @RequestMapping(value = "feedback/{studentId}", method = RequestMethod.GET, produces = "application/json")
    public ServerResponse all(HttpSession session, @PathVariable("studentId") int studentId) {

        Teacher teacher = (Teacher) session.getAttribute(Const.CURRENT_USER);

        if (teacher == null) {
            return ServerResponse.createByErrorMessage("Please Login first.");
        }

        return teacherService.searchFeedback(teacher.getTeacherId(), studentId);
    }

    @RequestMapping(value = "makefeedback", method = RequestMethod.POST, produces = "application/json")
    public ServerResponse makeFeedback(HttpSession session, int studentId, String feedback) {
        Teacher teacher = (Teacher) session.getAttribute(Const.CURRENT_USER);
        if (teacher == null) {
            return ServerResponse.createByErrorMessage("Please Login first.");
        }

        ServerResponse response = teacherService.makeFeedback(teacher.getTeacherId(), studentId, feedback);
        return response;

    }


}
