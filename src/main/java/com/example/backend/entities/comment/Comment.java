package com.example.backend.entities.comment;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class Comment {

    private int id;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private int postId;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String author;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String content;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String date;

    public Comment(){

    }

    public Comment(int id, String author, String content, String date, int postId){
        this.id = id;
        this.author = author;
        this.content = content;
        this.date = date;
        this.postId = postId;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public int getPostId() {
        return postId;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
