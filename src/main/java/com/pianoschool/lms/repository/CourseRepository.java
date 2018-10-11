package com.pianoschool.lms.repository;

import com.pianoschool.lms.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

//    Course findCourseByCourse_id(Integer courseId);


    @Override
    Optional<Course> findById(Integer courseId);
}
