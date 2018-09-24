package com.pianoschool.lms.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class Hello {


    @RequestMapping()
    private String myTest(){
        return "hello";
    }

}
