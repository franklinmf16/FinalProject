package com.finalproj.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Helllo {
    @RequestMapping("/hello")
    public String index(){
        return "start my project";
    }

}
