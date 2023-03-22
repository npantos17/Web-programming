package com.example.backend.entities.post;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

public class Post {

    private int id;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private int categoryId;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String title;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String content;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private java.sql.Date creationDate;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private int viewCount;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String author;

    public Post(){

    }

    public Post(int id, int categoryId, String title, String content, java.sql.Date creationDate, int viewCount, String author){
        this.id = id;
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
        this.viewCount = viewCount;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public int getViewCount() {
        return viewCount;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public String getTitle() {
        return title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setCreationDate(java.sql.Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author=" + author +
                ", date=" + creationDate +
                ", views=" + viewCount +
                '}';
    }
}

