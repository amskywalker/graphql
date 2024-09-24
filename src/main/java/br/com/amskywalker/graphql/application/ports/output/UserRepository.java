package br.com.amskywalker.graphql.application.ports.output;

import br.com.amskywalker.graphql.domain.model.User;

import java.util.UUID;

public interface UserRepository {
    User save(User user);
    User findById(UUID id);
    User update(User user);
    Boolean delete(User user);
}
