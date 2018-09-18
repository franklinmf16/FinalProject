package com.finalproj.demo.controller;


import com.finalproj.demo.domain.Student;
import com.finalproj.demo.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
    private Map<String, Object> getStudentByEmail(String email){
        Map<String, Object> modelMap = new HashMap<String, Object>();
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

    @RequestMapping(value = "/updatestudent", method = RequestMethod.POST)
    private Map<String, Object> updateStudent(@RequestBody Student student){
        HashMap<String, Object> modeMap = new HashMap<>();
        modeMap.put("success", studentService.updateStudent(student));
        return modeMap;
    }

    @RequestMapping(value = "/deletestudent", method = RequestMethod.GET)
    private Map<String, Object> deleteStudent(Integer studentid){
        HashMap<String, Object> modeMap = new HashMap<>();
        modeMap.put("success", studentService.deleteStudent(studentid));
        return modeMap;
    }


}
