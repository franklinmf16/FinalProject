package com.finalproj.demo.service.impl;

import com.finalproj.demo.dao.IStudent;
import com.finalproj.demo.domain.Student;
import com.finalproj.demo.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Service
public class StudentService implements IStudentService {

    @Autowired
    private IStudent studentDao;

    @Override
    public List<Student> getStudentList() {
        return studentDao.queryStudent();
    }

    @Override
    public Student getStudentByEmail(String email) {
        return studentDao.searchByEmail(email);
    }

    @Transactional
    @Override
    public boolean addStudent(Student student) {
        if(student.getEmail() != null){
           student.setEnrollDate((Data) new Date());
           student.setLastEditDate((Data) new Date());
            int effectedNum = studentDao.updateStudent(student);
            if(effectedNum > 0){
                return true;
            }else {
                throw new RuntimeException("fail to add student");
            }
        }else {
            throw new RuntimeException("please enter email");
        }
    }

    @Transactional
    @Override
    public boolean updateStudent(Student student) {
        if(student.getEmail()!=null){
            student.setLastEditDate((Data) new Date());
            int effectedNum = studentDao.updateStudent(student);
            if (effectedNum > 0){
                return true;
            }
            else {
                throw new RuntimeException("fail to update");
            }
        }else{
            throw new RuntimeException("id is wrong");
        }

    }

    @Transactional
    @Override
    public boolean deleteStudent(int studentid) {
        if (studentid > 0){
            int effectedNum = studentDao.deleteStudent(studentid);
            if (effectedNum > 0){
                return true;
            } else {
                throw new RuntimeException("fail to delete");
            }
        } else {
            throw new RuntimeException("id is wrong");
        }
    }
}
