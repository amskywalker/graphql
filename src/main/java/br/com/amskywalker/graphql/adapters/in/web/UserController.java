package br.com.amskywalker.graphql.adapters.in.web;

import br.com.amskywalker.graphql.application.services.UserServiceImpl;
import br.com.amskywalker.graphql.domain.model.User;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class UserController {

    public UserServiceImpl userService;

    @QueryMapping
    public ResponseEntity<User> userById(@Argument UUID uuid){
        return ResponseEntity.ok(userService.getById(uuid));
    }
}
