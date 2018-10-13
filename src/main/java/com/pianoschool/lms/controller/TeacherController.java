package com.pianoschool.lms.controller;

import com.pianoschool.lms.common.Const;
import com.pianoschool.lms.common.ServerResponse;
import com.pianoschool.lms.domain.Feedback;
import com.pianoschool.lms.domain.Teacher;
import com.pianoschool.lms.domain.model.TeacherFeedback;
import com.pianoschool.lms.service.TeacherService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.MediaType;
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

    @RequestMapping(value = "makefeedback", method = RequestMethod.POST, consumes = {"application/x-www-form-urlencoded"}, produces = "application/json")
    public ServerResponse makeFeedback(HttpSession session,  TeacherFeedback returnFeedback) {


        String feedback = returnFeedback.getFeedback();
        String resultId = returnFeedback.getStudentId();
        int studentId = Integer.parseInt(resultId);


        Teacher teacher = (Teacher) session.getAttribute(Const.CURRENT_USER);
        if (teacher == null) {
            return ServerResponse.createByErrorMessage("Please Login first.");
        }

        System.out.println("student id is ");
        System.out.println(studentId);


        ServerResponse response = teacherService.makeFeedback(teacher.getTeacherId(), studentId, feedback);
        return response;

    }

    @RequestMapping(value = "number", method = RequestMethod.GET, produces = "application/json")
    public ServerResponse getStudentNmuber(HttpSession session) {
        Teacher teacher = (Teacher) session.getAttribute(Const.CURRENT_USER);
        if (teacher == null) {
            return ServerResponse.createByErrorMessage("Please Login first.");
        }

        ServerResponse response = teacherService.getStudentNumber(teacher.getTeacherId());
        return response;
    }

    @RequestMapping(value = "newstudent", method = RequestMethod.GET, produces = "application/json")
    public ServerResponse getNewStudent(HttpSession session) {
        Teacher teacher = (Teacher) session.getAttribute(Const.CURRENT_USER);
        if (teacher == null) {
            return ServerResponse.createByErrorMessage("Please Login first.");
        }

        ServerResponse response = teacherService.getNewStudent(teacher.getTeacherId());
        return response;
    }

    @RequestMapping(value = "newfeedback", method = RequestMethod.GET, produces = "application/json")
    public ServerResponse getNewFeedback(HttpSession session) {
        Teacher teacher = (Teacher) session.getAttribute(Const.CURRENT_USER);
        if (teacher == null) {
            return ServerResponse.createByErrorMessage("Please Login first.");
        }

        ServerResponse response = teacherService.getNewFeedback(teacher.getTeacherId());
        return response;
    }


    @RequestMapping(value = "getstudentinfo", method = RequestMethod.GET, produces = "application/json")
    public ServerResponse getStudentEnrollmentInfomation(HttpSession session) {
        Teacher teacher = (Teacher) session.getAttribute(Const.CURRENT_USER);
        if (teacher == null) {
            return ServerResponse.createByErrorMessage("Please Login first.");
        }

        ServerResponse response = teacherService.getStudentEnrollmentInfo(teacher.getTeacherId());
        return response;
    }

    @RequestMapping(value = "getstudentlist", method = RequestMethod.GET, produces = "application/json")
    public ServerResponse getStudentList(HttpSession session) {
        Teacher teacher = (Teacher) session.getAttribute(Const.CURRENT_USER);
        if (teacher == null) {
            return ServerResponse.createByErrorMessage("Please Login first.");
        }

        ServerResponse response = teacherService.getStudentLists(teacher.getTeacherId());
        return response;
    }






}
