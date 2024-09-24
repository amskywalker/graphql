package br.com.amskywalker.graphql.application.services;

import br.com.amskywalker.graphql.application.ports.input.UserService;
import br.com.amskywalker.graphql.application.ports.output.UserRepository;
import br.com.amskywalker.graphql.domain.model.User;

import java.util.UUID;

public class UserServiceImpl implements UserService {
    protected final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.update(user);
    }

    @Override
    public User getById(UUID uuid) {
        return userRepository.findById(uuid);
    }

    @Override
    public Boolean deleteUser(User user) {
        return userRepository.delete(user);
    }
}
