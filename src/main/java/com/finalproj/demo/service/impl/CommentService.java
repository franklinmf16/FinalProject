package com.finalproj.demo.service.impl;


import com.finalproj.demo.domain.Comment;
import com.finalproj.demo.repository.CommentsRepository;
import com.finalproj.demo.repository.StudentRepository;
import com.finalproj.demo.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentService implements ICommentService {

    @Autowired
    private CommentsRepository commentsRepository;

    @Override
    public Boolean addComment(Comment comment) {
        comment.setLastEditDate(new Date());
        commentsRepository.saveAndFlush(comment);
        return true;
    }
}
