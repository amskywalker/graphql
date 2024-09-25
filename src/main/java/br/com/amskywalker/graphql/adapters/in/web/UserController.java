package br.com.amskywalker.graphql.adapters.in.web;

import br.com.amskywalker.graphql.application.services.UserServiceImpl;
import br.com.amskywalker.graphql.domain.model.User;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
public class UserController {
    public UserServiceImpl userService;

    public UserController(UserServiceImpl userService){
        this.userService = userService;
    }

    @QueryMapping
    public ResponseEntity<User> userById(@Argument UUID uuid){
        return ResponseEntity.ok(userService.getById(uuid));
    }

    @MutationMapping
    public User createUser(@Argument String name, @Argument String birthDate, @Argument String motherName) {
        User newUser = new User(UUID.randomUUID(), name, LocalDateTime.parse(birthDate), motherName);
        return userService.createUser(newUser);
    }
}
