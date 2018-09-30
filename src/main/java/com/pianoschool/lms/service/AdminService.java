package com.pianoschool.lms.service;

import com.pianoschool.lms.common.ServerResponse;
import com.pianoschool.lms.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService{

    @Autowired
    private AdminRepository adminRepository;

    public ServerResponse<String> checkValid(String email) {
        boolean isExist = adminRepository.existsByEmail(email);
        if (!isExist) {
            ServerResponse.createBySuccess("is valid email");
        }
        return ServerResponse.createByErrorMessage("email has already existed");
    }

}
