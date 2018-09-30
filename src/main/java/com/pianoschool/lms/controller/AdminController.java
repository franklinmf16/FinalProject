package com.pianoschool.lms.controller;

import com.pianoschool.lms.service.AdminService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;



}
