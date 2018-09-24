package com.pianoschool.lms.controller;

import com.pianoschool.lms.common.ServerResponse;
import com.pianoschool.lms.domain.Teacher;
import com.pianoschool.lms.service.ITeacherService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private ITeacherService teacherService;

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ServerResponse<String> register(@RequestBody Teacher teacher){
        return teacherService.register(teacher);
    }

}
