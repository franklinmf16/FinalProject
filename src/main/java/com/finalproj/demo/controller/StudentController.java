package com.finalproj.demo.controller;


import com.finalproj.demo.domain.Student;
import com.finalproj.demo.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private IStudentService studentService;


    @RequestMapping(value = "/liststudent", method = RequestMethod.GET)
    private Map<String, Object> listStudent(){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<Student> students = new ArrayList<Student>();

        students = studentService.getStudentList();
        modelMap.put("studentslist", students);
        return modelMap;
    }

    @RequestMapping(value = "/getstudentbyemail", method = RequestMethod.GET)
    private Map<String, Object> getStudentByEmail(String email){ Map<String, Object> modelMap = new HashMap<String, Object>();
        Student targetStudent = studentService.getStudentByEmail(email);
        modelMap.put("student",targetStudent);
        return modelMap;
    }

    @RequestMapping(value = "/addstudent", method = RequestMethod.POST)
    private Map<String, Object> addStudent(@RequestBody Student student){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("success", studentService.addStudent(student));
        return modelMap;
    }

}
