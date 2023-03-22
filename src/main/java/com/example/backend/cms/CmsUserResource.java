package com.example.backend.cms;

import com.example.backend.entities.user.User;
import com.example.backend.services.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/cms_users")
public class CmsUserResource {

    @Inject
    private UserService userService;

    //todo mozda ovde premestiti find by email

    @POST//radi
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User addUser (User user){
        return this.userService.addUser(user);
    }

    @PUT//radi
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User editUser (User user){
        return this.userService.editUser(user);
    }

    @DELETE//radi
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteUser(@PathParam("id") Integer id){
        this.userService.deleteUser(id);
    }

//    @PUT//ne radi
    @PUT
    @Path("/activate/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void activateUser(@PathParam("id")Integer id){
        this.userService.activateUser(id);
    }

//    @PUT//ne radi
    @PUT
    @Path("/deactivate/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void deactivateUser(@PathParam("id")Integer id){
        this.userService.deactivateUser(id);
    }



}
