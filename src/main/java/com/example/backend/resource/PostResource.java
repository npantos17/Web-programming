package com.example.backend.resource;

import com.example.backend.entities.post.Post;
import com.example.backend.services.CommentService;
import com.example.backend.services.PostService;
import com.example.backend.services.TagService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/posts")
public class PostResource {

    @Inject
    private PostService postService;

    @Inject
    private CommentService commentService;
    @Inject
    private TagService tagService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Post> getPosts(@HeaderParam("Authorization") String jwt){
//        if(!postService.isAdmin(jwt) && !postService.isContentCreator(jwt)){
//            return null;
//        }
//        return Response.ok(postService.allPosts()).build();
        return postService.allPosts();
    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Post findPost(@PathParam("id") Integer id){
//        if(!postService.isAdmin(jwt) && !postService.isContentCreator(jwt)){
//            return null;
//        }
        return postService.findPost(id);
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Post addPost(Post post){
//        if(!postService.isAdmin(jwt) && !postService.isContentCreator(jwt)){
//            return null;
//        }
        return postService.addPost(post);


    }
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public void deletePost(int id, @HeaderParam("Authorization") String jwt){
//        if(!postService.isAdmin(jwt) && !postService.isContentCreator(jwt)){
//            return;
//        }
        postService.deletePost(id);
    }
    @GET
    @Path("/page/{num}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Post> postsOnPage (@PathParam("num") Integer num) {
        return postService.postsOnPage(num);
    }
    @POST
    @Path("/visit/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void incrementViews(@PathParam("id") Integer id) {
        this.postService.incrementViews(id);
    }


    @GET
    @Path("/{id}/comments")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPostComments (@PathParam("id") Integer id) {
        return Response.ok(this.commentService.findCommentsPost(id)).build();
    }

    @GET
    @Path("/category/{id}")//(category_id)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findPostsByCategory(@PathParam("id") Integer id) {
        return Response.ok(this.postService.findPostsByCategory(id)).build();
    }

    @GET
    @Path("/category/{id}/{num}")//(category_id)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Post> postByCategoryByPage(@PathParam("id") Integer id, @PathParam("num") Integer num) {
        return postService.postByCatByPage(id,num);
    }

    @GET
    @Path("/tag/{id}")//(tag_id)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findPostsByTag (@PathParam("id") Integer tagId) {
        return Response.ok(this.postService.findPostsByTag(tagId)).build();
    }

    @GET
    @Path("/{id}/tag")//(article_id)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findPostTags(@PathParam("id") Integer id) {
        return Response.ok(this.tagService.tagsFromArticle(id)).build();
    }

    @GET
    @Path("/mostRecent")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findMostRecentArticles(){
        return Response.ok(this.postService.findMostRecentPosts()).build();
    }

    @GET
    @Path("/mostRead")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findMostReadPostsMonthly(){
        return Response.ok(this.postService.findMostReadPostsMonthly()).build();
    }
}
