package com.example.backend.entities.tag;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Tag {

    private int id;
//    @NotNull(message = "Title field is required")
//    @NotEmpty(message = "Title field is required")
//    private int postId;
    @NotNull(message = "Keyword field is required")
    @NotEmpty(message = "Keyword field is required")
    private String keyword;

    public Tag(){

    }

    public Tag(int id, String keyword){

        this.id = id;
        this.keyword = keyword;
    }

//    public int getPostId() {
//        return postId;
//    }

    public String getKeyword() {
        return keyword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public void setPostId(int postId) {
//        this.postId = postId;
//    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
