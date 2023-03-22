package com.example.backend.resource;

import com.example.backend.entities.comment.Comment;
import com.example.backend.services.CommentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/comments")
public class CommentResource {

    @Inject
    private CommentService commentService;

    @GET
    @Path("/post/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findCommentsForArticle(@PathParam("id") Integer postId) {
        return Response.ok(this.commentService.findCommentsPost(postId)).build();
    }

    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Comment addComment (Comment comment){
        return this.commentService.addComment(comment);
    }

}
