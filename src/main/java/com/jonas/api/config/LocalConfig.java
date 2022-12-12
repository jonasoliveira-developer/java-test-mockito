package com.jonas.api.config;

import com.jonas.api.domain.User;
import com.jonas.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {
    @Autowired
    private UserRepository repository;
    @Bean
    public void startDB () {
        User u1 = new User(null, "Jonas Oliveira","jonas@eemail","12345");
        User u2 = new User(null, "Pablo Nascimento","pablo@eemail","12345");


        repository.saveAll(List.of(u1,u2));
    }
}
