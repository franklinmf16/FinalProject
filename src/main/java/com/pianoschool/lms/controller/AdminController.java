package com.pianoschool.lms.controller;


        import com.pianoschool.lms.common.ServerResponse;
        import com.pianoschool.lms.domain.Admin;
        import com.pianoschool.lms.service.IAdminService;
        import org.springframework.beans.factory.annotation.*;
        import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private IAdminService adminService;


}
