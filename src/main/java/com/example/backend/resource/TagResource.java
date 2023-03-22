package com.example.backend.resource;


import com.example.backend.entities.tag.Tag;
import com.example.backend.services.TagService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/tag")
public class TagResource {

    @Inject
    private TagService tagService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
//    public List<Tag> allTags() {
//        return this.tagService.allTags();
//    }
    public Response allTags() {
        return Response.ok(this.tagService.allTags()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Tag addTag (Tag tag){
        return this.tagService.addTag(tag);
    }

    @POST
    @Path("/tag_posts/{tagId}/{postId}")
    @Produces(MediaType.APPLICATION_JSON)
    public void addTagsArticles(@PathParam("tagId") Integer tagId, @PathParam("postId") Integer articleId) {
        this.tagService.addTagsArticles(tagId, articleId);
    }

    @GET
    @Path("/tag_posts/{postId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response tagsFromArticle(@PathParam("postId") Integer postId) {
        return Response.ok(this.tagService.tagsFromArticle(postId)).build();
    }
}
