package com.finalproj.demo.dao;

import com.finalproj.demo.domain.Student;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class IStudentTest {

    @Autowired
    private IStudent student;

    @Test
    public void queryStudent() {
        List<Student> studentList = student.queryStudent();
        assertEquals(3, studentList.size());
    }

    @Test
    public void searchByEmail() {
    }

    @Test
    public void insertStudentbyId() {
    }
}