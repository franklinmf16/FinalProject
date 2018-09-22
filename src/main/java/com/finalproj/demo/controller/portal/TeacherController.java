package com.finalproj.demo.controller.portal;

import com.finalproj.demo.common.Const;
import com.finalproj.demo.common.ResponseCode;
import com.finalproj.demo.common.ServerResponse;
import com.finalproj.demo.domain.Comment;
import com.finalproj.demo.domain.Student;
import com.finalproj.demo.domain.Teacher;
import com.finalproj.demo.service.ICommentService;
import com.finalproj.demo.service.ITeacherService;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/teacher")
public class TeacherController {
//    @Autowired
//    private ICommentService comments;

    @Autowired
    private ITeacherService iTeacherService;


//todo: makeComment
//todo: listComment



    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    public ServerResponse<Teacher> login(String email, String password, HttpSession session){
        ServerResponse<Teacher> response = iTeacherService.login(email, password);
        if (response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return response;
    }

    @RequestMapping(value = "logout.do", method = RequestMethod.GET)
    public ServerResponse<String> logout(HttpSession session){
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess("Success log out");
    }

    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    public ServerResponse<String> register(@RequestBody Teacher teacher){
        ServerResponse<String> response = iTeacherService.register(teacher);
        return response;
    }

    // todo : get all teacher that has log?
    @RequestMapping(value = "get_teacher_info.do", method = RequestMethod.POST)
    public ServerResponse<Teacher> getTeacherInfo(HttpSession session){
        Teacher teacher = (Teacher) session.getAttribute(Const.CURRENT_USER);
        if (teacher != null){
            return ServerResponse.createBySuccess(teacher);
        }
        return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"Teacher has not login");
    }


    @RequestMapping(value = "get_student_info.do", method = RequestMethod.POST)
    public ServerResponse listStudents(HttpSession session){

        Teacher currentTeacher = (Teacher) session.getAttribute(Const.CURRENT_USER);
        if (currentTeacher == null){
            return ServerResponse.createByErrorMessage("teacher should login first");
        }

        ServerResponse response = iTeacherService.listStudent(currentTeacher);
        if (!response.isSuccess()){
            System.out.println("fail to get students");
        }

        return response;
    }


    @RequestMapping(value = "make_comments.do", method = RequestMethod.POST)
    public ServerResponse makeComment(HttpSession session, Integer studentid, String comments){

        Teacher currentTeacher = (Teacher) session.getAttribute(Const.CURRENT_USER);
        if (currentTeacher == null){
            return ServerResponse.createByErrorMessage("teacher should login first");
        }

        ServerResponse response = iTeacherService.makeComment(currentTeacher.getTeacherid(), studentid, comments);
        return response;

    }

    @RequestMapping(value = "forget_get_question.do", method = RequestMethod.GET)
    public ServerResponse<String> forgetQuestion(String email){
        return iTeacherService.selectQuestion(email);
    }


    @RequestMapping(value = "foget_check_answer.do", method = RequestMethod.GET)
    public ServerResponse<String> forgetCheckAnswer(String email, String question, String answer){
        return iTeacherService.checkAnswer(email,question,answer);
    }

    @RequestMapping(value = "foget_reset_password.do", method = RequestMethod.GET)
    public ServerResponse<String> forgetResetPassword(String email, String passwordNew, String forgetToken){
        return iTeacherService.forgetResetPassword(email,passwordNew,forgetToken);
    }

    @RequestMapping(value = "reset_password.do", method = RequestMethod.GET)
    public ServerResponse<String> resetPassword(HttpSession session, String passwordOld, String passwordNew){
        Teacher teacher = (Teacher) session.getAttribute(Const.CURRENT_USER);

        if (teacher == null){
            ServerResponse.createBySuccess("Student has not logged in");
        }
        return iTeacherService.resetPassword(passwordOld,passwordNew,teacher);
    }




}
