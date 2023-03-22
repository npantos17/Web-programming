package com.example.backend.repository;

import com.example.backend.entities.comment.Comment;

import java.util.List;

public interface CommentRepository {

    public Comment addComment(Comment comment);
    public List<Comment> findCommentsForPost(Integer id);
}
