package com.finalproj.demo.controller;

import com.finalproj.demo.domain.Comment;
import com.finalproj.demo.domain.Teacher;
import com.finalproj.demo.service.ICommentService;
import com.finalproj.demo.service.ITeacherService;
import com.sun.org.apache.bcel.internal.generic.NEW;
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
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private ICommentService comments;

    @Autowired
    private ITeacherService teacherService;


    @RequestMapping(value = "/makecomment", method = RequestMethod.POST)
    public Map<String, Object> makeComment(@RequestBody Comment comment){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("success", comments.addComment(comment));
        return modelMap;
    }

    @RequestMapping(value = "listcomment", method = RequestMethod.GET)
    public Map<String, Object> listComment(){
        HashMap<String, Object> modeMap = new HashMap<>();
        return null;

    }


    @RequestMapping(value = "/listteacher", method = RequestMethod.GET)
    public Map<String, Object> listTeacher(){
        HashMap<String, Object> modeMap = new HashMap<>();
        modeMap.put("teacherlist", teacherService.listTeacher());
        return modeMap;
    }




}
