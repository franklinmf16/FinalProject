package com.pianoschool.lms.repository;

import com.pianoschool.lms.domain.Enrollment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class EnrollmentRepositoryTest {

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Test
    public void findEnrollmentStudentInfoByTeacherId() {
    }


    @Test
    public void testFindEnrollmentIdByStudentId() {

        Enrollment enrollment = new Enrollment();
        enrollment.setEnrollmentId(100);
        enrollment.setCreateDate(new Date());
        enrollment.setTeacherId(1);
        enrollment.setStudentId(2);
        enrollment.setCourseId(1);

        enrollmentRepository.saveAndFlush(enrollment);

        Optional<Integer> foundId = enrollmentRepository.findEnrollmentIdByStudentId(2);
        Assert.assertTrue(foundId.isPresent());
        Assert.assertEquals(foundId.get().intValue(), 100);
    }
}