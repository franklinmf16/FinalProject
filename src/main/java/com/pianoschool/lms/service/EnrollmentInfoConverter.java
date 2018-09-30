package com.pianoschool.lms.service;

import com.pianoschool.lms.domain.model.EnrollmentStudentInfo;
import com.pianoschool.lms.domain.model.IEnrollmentStudentInfo;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentInfoConverter {
    public EnrollmentStudentInfo build(IEnrollmentStudentInfo iEnroll) {
        EnrollmentStudentInfo info = new EnrollmentStudentInfo();
        info.setFeedBack(iEnroll.getFeedback());
        info.setFullName(iEnroll.getFullName());
        return info;
    }
}
