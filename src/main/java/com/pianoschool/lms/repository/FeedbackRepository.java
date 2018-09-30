package com.pianoschool.lms.repository;

import com.pianoschool.lms.domain.Feedback;
import com.pianoschool.lms.domain.model.StudentFeedbackInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

    @Query("select f.feedback as feedback, e.studentId as studentId, e.courseId as courseId, s.fullName as fullName\n" +
            "from Feedback f, Enrollment e, Student s \n" +
            "where e.enrollmentId = f.enrollmentId and e.teacherId = :teacherId and e.studentId = :studentId and s.studentId = :studentId ")
    List<StudentFeedbackInfo> searchFeedback(@Param("teacherId")Integer teacherId,
                                             @Param("studentId") Integer studentId);

    /**
     * teacher make feedback to a specific student
     * @param teacherId
     * @param studentId
     * @param feedback
     * @return
     */
//    @Query("")
////    List makeFeedback(@Param("teacherId")Integer teacherId,
////                      @Param("studentId") Integer studentId,
////                      @Param("feedback") String feedback);

}

