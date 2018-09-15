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
        Student target = student.searchByEmail("heybok@gmail.com");
        assertEquals("bokk",target.getEnglishName());
    }

    @Test
    public void insertStudentbyId() {
        Student testStudent = new Student();
        testStudent.setCourse("high");
        testStudent.setEmail("504129976@qq.com");
        testStudent.setEnglishName("Franklin");
        testStudent.setTeacher("s");
        testStudent.setPassword("123");
        int effecttedNum = student.insertStudentbyId(testStudent);
        assertEquals(1,effecttedNum);
    }

    @Test
    public void updateStudent() {
        Student testStudent = new Student();
        testStudent.setEmail("bok@gmail.com");
        testStudent.setStudentid(8);
        int effectNum = student.updateStudent(testStudent);
        assertEquals(1,effectNum);
    }

    @Test
    public void deleteStudent() {
        int effecttedNum = student.deleteStudent(8);
        assertEquals(1,effecttedNum);
    }
}