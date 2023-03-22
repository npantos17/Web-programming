package com.example.backend.services;

import com.example.backend.entities.comment.Comment;
import com.example.backend.repository.CommentRepository;
import com.example.backend.repository.CommentRepositoryImpl;

import javax.inject.Inject;
import java.util.List;

public class CommentService {

    @Inject
    private CommentRepository commentRepository;

    public Comment addComment(Comment comment) {
        return commentRepository.addComment(comment);
    }

    public List<Comment> findCommentsPost(Integer id) {
        return commentRepository.findCommentsForPost(id);
    }

//    public List<Comment> allComments (){return commentRepository.allComments();}

}
