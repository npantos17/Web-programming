package com.example.backend;

import com.example.backend.repository.*;
import com.example.backend.services.*;
//import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class HelloApplication extends ResourceConfig {
    public HelloApplication(){
        // Ukljucujemo validaciju

        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        // Definisemo implementacije u dependency container-u
        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {
                this.bind(UserRepositoryImpl.class).to(UserRepository.class).in(Singleton.class);
                this.bind(PostRepositoryImpl.class).to(PostRepository.class).in(Singleton.class);
                this.bind(CommentRepositoryImpl.class).to(CommentRepository.class).in(Singleton.class);
                this.bind(CategoryRepositoryImpl.class).to(CategoryRepository.class).in(Singleton.class);
                this.bind(TagRepositoryImpl.class).to(TagRepository.class).in(Singleton.class);

                this.bindAsContract(UserService.class);
                this.bindAsContract(PostService.class);
                this.bindAsContract(CommentService.class);
                this.bindAsContract(CategoryService.class);
                this.bindAsContract(TagService.class);
            }
        };
        register(binder);


        // Ucitavamo resurse
        packages("com.example.backend");
    }

}