package com.pianoschool.lms.controller;


import com.pianoschool.lms.common.ServerResponse;
import com.pianoschool.lms.domain.Student;
import com.pianoschool.lms.service.IStudentService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ServerResponse<String> register(@RequestBody Student student){
        ServerResponse<String> response = studentService.register(student);
        return response;
    }

}
