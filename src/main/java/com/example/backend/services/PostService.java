package com.example.backend.services;

import com.example.backend.entities.post.Post;
import com.example.backend.repository.PostRepository;
import com.example.backend.repository.PostRepositoryImpl;

import javax.inject.Inject;
import java.util.List;

public class PostService {

    @Inject
    private PostRepository postRepository;
//    private PostRepositoryImpl postRepository;

    public Post addPost(Post post) {
        return postRepository.addPost(post);
    }

    public List<Post> allPosts(){
        return postRepository.allPosts();
    }

    public List<Post> postsByPage(Integer pageNum){
        return postRepository.findPostsByPage(pageNum);
    }

    public Post findPost(Integer id) {
        return postRepository.findPost(id);
    }

    public List<Post> searchPost(String search) {
        return postRepository.searchPost(search);
    }

    public void deletePost(Integer id) {
        postRepository.deletePost(id);
    }

    public List<Post> postByCatByPage (Integer categoryId, Integer pageNum){
        return postRepository.postByCatByPage(categoryId, pageNum);
    }

    public Post editPost(Post post) {
        return postRepository.editPost(post);
    }

    public List<Post> postsOnPage(Integer pageNum){
        return postRepository.postsOnPage(pageNum);
    }

    public void incrementViews(Integer id) {
        postRepository.incrementViews(id);
    }

    public List<Post> findPostsByCategory(Integer catId) {
        return postRepository.findPostsByCategory(catId);
    }

    public List<Post> findPostsByTag(Integer tagId) {
        return postRepository.findPostsByTag(tagId);
    }

    public List<Post> findMostRecentPosts() {
        return postRepository.findMostRecentPosts();
    }

    public List<Post> findMostReadPostsMonthly() {
        return postRepository.findMostReadMonthlyPosts();
    }
}
