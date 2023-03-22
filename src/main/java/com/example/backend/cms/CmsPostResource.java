package com.example.backend.cms;


import com.example.backend.entities.post.Post;
import com.example.backend.services.PostService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/cms_posts")
public class CmsPostResource {

    @Inject
    private PostService postService;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Post addPost(Post post) {
        return this.postService.addPost(post);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Post updatePost(@Valid Post post) {
        return this.postService.editPost(post);
    }

    @DELETE
    @Path("/{id}")
    public void deletePost(@PathParam("id") Integer id) {
        this.postService.deletePost(id);
    }

    @GET
    @Path("/search/{search}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Post> searchPost(@PathParam("search") String search){
        return this.postService.searchPost(search);
    }
}
