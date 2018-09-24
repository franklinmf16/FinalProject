package com.pianoschool.lms.service;

import com.pianoschool.lms.common.ServerResponse;
import com.pianoschool.lms.domain.Admin;

public interface IAdminService {
    public ServerResponse<String> checkValid(String email);
}
