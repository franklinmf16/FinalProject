package com.pianoschool.lms.repository;

import com.pianoschool.lms.domain.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {

}
