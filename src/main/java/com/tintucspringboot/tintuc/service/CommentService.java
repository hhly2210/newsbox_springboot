package com.tintucspringboot.tintuc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tintucspringboot.tintuc.model.Comment;
import com.tintucspringboot.tintuc.respository.CommentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    public List<Comment> getComments(int id){
        return commentRepository.findByPost_Id(id);
    }
}
