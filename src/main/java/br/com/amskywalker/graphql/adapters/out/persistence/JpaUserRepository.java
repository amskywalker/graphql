package br.com.amskywalker.graphql.adapters.out.persistence;

import br.com.amskywalker.graphql.application.ports.output.UserRepository;
import br.com.amskywalker.graphql.domain.exceptions.UserNotFoundException;
import br.com.amskywalker.graphql.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public class JpaUserRepository implements UserRepository {
    public UserJpaRepository userJpaRepository;

    @Autowired
    public JpaUserRepository(UserJpaRepository userJpaRepository){
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public User save(User user) {
        return userJpaRepository.save(user);
    }

    @Override
    public User findById(UUID id) {
        Optional<User> hasUser = userJpaRepository.findById(id);

        if (hasUser.isEmpty()){
            throw new UserNotFoundException("User not found");
        }

        return hasUser.get();
    }

    @Override
    public User update(User user) {
        return userJpaRepository.save(user);
    }

    @Override
    public Boolean delete(User user) {
        userJpaRepository.delete(user);
        Optional<User> hasUser = userJpaRepository.findById(user.id());
        return hasUser.isEmpty();
    }
}
