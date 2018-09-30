package com.pianoschool.lms.service;

import com.pianoschool.lms.common.ServerResponse;
import com.pianoschool.lms.domain.Feedback;
import com.pianoschool.lms.domain.Teacher;
import com.pianoschool.lms.domain.model.EnrollmentStudentInfo;
import com.pianoschool.lms.domain.model.IEnrollmentStudentInfo;
import com.pianoschool.lms.domain.model.StudentFeedbackInfo;
import com.pianoschool.lms.repository.EnrollmentRepository;
import com.pianoschool.lms.repository.FeedbackRepository;
import com.pianoschool.lms.repository.StudentRepository;
import com.pianoschool.lms.repository.TeacherRepository;
import com.pianoschool.lms.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherService   {

    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private EnrollmentInfoConverter converter;



    private ServerResponse<String> checkValid(String email) {
        boolean isExist = teacherRepository.existsByEmail(email);
        if (!isExist) {
            ServerResponse.createBySuccess("is valid email");
        }
        return ServerResponse.createByErrorMessage("email has already existed");
    }


    public ServerResponse<String> register(Teacher teacher) {
        if (teacher.getEmail() == null) {
            return ServerResponse.createByErrorMessage("Can't register without email");
        }

        ServerResponse<String> isValidEmail = checkValid(teacher.getEmail());
        if (isValidEmail.isSuccess()) {
            return ServerResponse.createByErrorMessage("email has already existed");
        }

        teacher.setCreateDate(new Date());
        teacher.setLastEditDate(new Date());
        teacher.setPassword(MD5Util.MD5EncodeUtf8(teacher.getPassword()));
        teacherRepository.saveAndFlush(teacher);
        return ServerResponse.createBySuccess("success to register as a teacher");

    }



    public ServerResponse<Teacher> login(String email, String password) {
        ServerResponse<String> checkValid = checkValid(email);

        if (checkValid.isSuccess()){
            return ServerResponse.createByErrorMessage("email is wrong");
        }

        String md5password = MD5Util.MD5EncodeUtf8(password);

        Teacher teacher = teacherRepository.findTeacherByEmailAndPassword(email,md5password);
        if (teacher==null){
            return ServerResponse.createByErrorMessage("Password is wrong");
        }

        //teacher.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess("Success log in as teacher",teacher);

    }

    //todo question answer and reset password
    public ServerResponse selectQuestion(String email) {
        return null;
    }

    public ServerResponse checkAnswer(String email, String question, String answer) {
        return null;
    }

    public ServerResponse<String> forgetResetPassword(String email, String passwordNew, String forgetToken) {
        return null;
    }

    public ServerResponse<String> resetPassword(String passwordOld, String passwordNew, Teacher teacher) {
        return null;
    }

    public ServerResponse getStudentEnrollmentInfo(int teacherId) {
        // 通过teacherId查找老师自己学生的enrollemmt

        List<IEnrollmentStudentInfo> a = enrollmentRepository.findEnrollmentStudentInfoByTeacherId(teacherId);
        List<EnrollmentStudentInfo> enrollInfo = a.stream().map(info -> converter.build(info)).collect(Collectors.toList());

        return ServerResponse.createBySuccess("success", enrollInfo);
    }

    public ServerResponse searchFeedback(int teacherId, int studentId) {
        List<StudentFeedbackInfo> result = feedbackRepository.searchFeedback(teacherId, studentId);
        return  ServerResponse.createBySuccess("success", result);

    }

    public ServerResponse makeFeedback(int teacherId, int studentId, String feedback) {
        //1. 找出学生ID对应的enrollmentId
        //2. new 一个feedback
        //3. save and flush feedback

        Optional<Integer> enrollmentId = enrollmentRepository.findEnrollmentIdByStudentId(studentId);

        if (!enrollmentId.isPresent()){
            return ServerResponse.createByErrorMessage("wrong student Id");
        }

        Feedback myFeedback = new Feedback();

        myFeedback.setEnrollmentId(enrollmentId.get());
        myFeedback.setFeedback(feedback);
        myFeedback.setCreateDate(new Date());
        myFeedback.setLastEditDate(new Date());


        feedbackRepository.saveAndFlush(myFeedback);

        return ServerResponse.createBySuccess("success add feedback");
    }


}
