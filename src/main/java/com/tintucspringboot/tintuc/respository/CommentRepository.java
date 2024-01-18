package com.tintucspringboot.tintuc.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tintucspringboot.tintuc.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{
    List<Comment> findByPost_Id(int id);
}
