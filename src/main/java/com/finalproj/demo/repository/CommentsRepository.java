package com.finalproj.demo.repository;

import com.finalproj.demo.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentsRepository extends JpaRepository<Comment, Integer> {
    Optional<Comment>findCommentsByStudentid(Integer studentid);
}
