package br.com.amskywalker.graphql.application.ports.input;

import br.com.amskywalker.graphql.domain.model.User;

import java.util.UUID;

public interface UserService {
    User createUser(User user);
    User updateUser(User user);
    User getById(UUID uuid);
    Boolean deleteUser(User user);
}
