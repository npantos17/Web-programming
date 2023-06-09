package com.example.backend.cms;

import com.example.backend.entities.category.Category;
import com.example.backend.services.CategoryService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/cms_categories")
public class CmsCategoryResource {

    @Inject
    private CategoryService categoryService;

    @POST//radi
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Category addCategory (Category category){
        return this.categoryService.addCategory(category);
    }

    @PUT//radi
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Category updateCategory (Category category){
        return this.categoryService.updateCategory(category);
    }

    @DELETE//todo radi polovicno, brise ali treba raditi check za postove
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteCategory(@PathParam("id") Integer id){
        this.categoryService.deleteCategory(id);
    }


    //todo mozda trebas da dodaj opet find name find id

}
