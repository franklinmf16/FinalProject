package com.pianoschool.lms.service.implement;

import com.pianoschool.lms.common.ServerResponse;
import com.pianoschool.lms.domain.Admin;
import com.pianoschool.lms.repository.AdminRepository;
import com.pianoschool.lms.service.IAdminService;
import com.pianoschool.lms.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AdminService implements IAdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public ServerResponse<String> checkValid(String email) {
        boolean isExist = adminRepository.existsByEmail(email);
        if (!isExist) {
            ServerResponse.createBySuccess("is valid email");
        }
        return ServerResponse.createByErrorMessage("email has already existed");
    }






}
