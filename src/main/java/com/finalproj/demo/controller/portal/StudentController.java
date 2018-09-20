package com.finalproj.demo.controller.portal;


import com.finalproj.demo.common.Const;
import com.finalproj.demo.common.ServerResponse;
import com.finalproj.demo.domain.Student;
import com.finalproj.demo.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class StudentController {

    @Autowired
    private IStudentService iStudentService;


    /**
     * login
     * @param email
     * @param password
     * @param session
     * @return
     */

    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    public ServerResponse<Student> login(String email, String password, HttpSession session){
        ServerResponse<Student> response = iStudentService.login(email,password);
        if (response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return response;
    }

    /**
     * Logout
     * @param session
     * @return
     */
    @RequestMapping(value = "logout.do", method = RequestMethod.GET)
    public ServerResponse<String> logout(HttpSession session){
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();

    }

    /**
     * Register
     * @param student
     * @return
     */
    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    public ServerResponse<String> register(@RequestBody Student student){
        ServerResponse<String> response = iStudentService.register(student);
        return response;
    }


    /**
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "getuserinfo.do", method = RequestMethod.GET)
    public ServerResponse<Student> getStudentInfo(HttpSession session){
        Student student = (Student) session.getAttribute(Const.CURRENT_USER);
        if (student == null){
            return ServerResponse.createByErrorMessage("Student has not log in");
        }
        return ServerResponse.createBySuccess(student);
    }


    @RequestMapping(value = "forget_get_question.do", method = RequestMethod.GET)
    public ServerResponse<String> forgetQuestion(String email){
        return iStudentService.selectQuestion(email);
    }

    @RequestMapping(value = "foget_check_answer.do", method = RequestMethod.GET)
    public ServerResponse<String> forgetCheckAnswer(String email, String question, String answer){
        return iStudentService.checkAnswer(email,question,answer);
    }

    @RequestMapping(value = "foget_reset_password.do", method = RequestMethod.GET)
    public ServerResponse<String> forgetResetPassword(String email, String passwordNew, String forgetToken){
        return iStudentService.forgetResetPassword(email,passwordNew,forgetToken);
    }

    @RequestMapping(value = "reset_password.do", method = RequestMethod.GET)
    public ServerResponse<String> resetPassword(HttpSession session, String passwordOld, String passwordNew){
        Student student = (Student) session.getAttribute(Const.CURRENT_USER);

        if (student == null){
            ServerResponse.createBySuccess("Student has not logged in");
        }
        return iStudentService.resetPassword(passwordOld,passwordNew,student);
    }

    //todo: method is get
    //todo: no request body
    @RequestMapping(value = "update_information.do", method = RequestMethod.POST)
    public ServerResponse<Student> updateInformation (HttpSession session, @RequestBody Student student){
        //see if the student has logged in
        Student currentStudent = (Student) session.getAttribute(Const.CURRENT_USER);

        if(currentStudent == null){
            return ServerResponse.createByErrorMessage("Student has not logged in");
        }

        //protect id being changed
        student.setStudentid(currentStudent.getStudentid());
        student.setPassword(currentStudent.getPassword());
        // email must exist
        if (student.getEmail()==null){
            student.setEmail(currentStudent.getEmail());
        }
        //update
        ServerResponse<Student> response = iStudentService.updateInformation(student);
        if (response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return response;

    }





}
