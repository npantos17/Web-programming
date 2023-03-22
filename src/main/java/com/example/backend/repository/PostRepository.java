package com.example.backend.repository;

import com.example.backend.entities.post.Post;

import java.util.List;

public interface PostRepository {

    public Post addPost(Post post);
    public Post editPost(Post post);
    public Post findPost(Integer id);
    public Integer countPost(Integer catId, Integer tagId);
    public void deletePost(Integer id);
    public List<Post> allPosts();
    public List<Post> findPostsByPage(Integer page);
    public List<Post> findPostsByCategory(Integer categoryId);
    public List<Post> findPostsByTag(Integer tagId);
    public List<Post> searchPost(String search);
    public List<Post> postsOnPage(Integer pageNum);
    public void incrementViews (Integer postId);
    public List<Post> findMostRecentPosts();
    public List<Post> findMostReadMonthlyPosts();
    public List<Post> postByCatByPage(Integer categoryId, Integer pageNum);
}
