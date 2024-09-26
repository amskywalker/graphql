package br.com.amskywalker.graphql.adapters.out.persistence;

import br.com.amskywalker.graphql.application.ports.output.UserRepository;
import br.com.amskywalker.graphql.domain.exceptions.UserNotFoundException;
import br.com.amskywalker.graphql.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaUserRepository implements UserRepository {
    private final SpringDataUserRepository springDataUserRepository;

    @Autowired
    public JpaUserRepository(SpringDataUserRepository springDataUserRepository) {
        this.springDataUserRepository = springDataUserRepository;
    }

    @Override
    @Transactional
    public User save(User user) {
        UserEntity userEntity = new UserEntity(user.uuid(), user.name(), user.birthDate(), user.motherName());
        UserEntity userEntitySaved = springDataUserRepository.save(userEntity);
        return new User(userEntitySaved.getId(), userEntitySaved.getName(), userEntitySaved.getBirthDate(), userEntitySaved.getMotherName());
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(UUID id) {
        Optional<UserEntity> hasUser = springDataUserRepository.findById(id);

        if (hasUser.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        UserEntity userEntity = hasUser.get();
        return new User(userEntity.getId(), userEntity.getName(), userEntity.getBirthDate(), userEntity.getMotherName());
    }

    @Override
    @Transactional
    public User update(User user) {
        if (!springDataUserRepository.existsById(user.uuid())) {
            throw new UserNotFoundException("User not found");
        }
        UserEntity userEntity = new UserEntity(user.uuid(), user.name(), user.birthDate(), user.motherName());
        UserEntity userEntityUpdated = springDataUserRepository.save(userEntity);
        return new User(userEntityUpdated.getId(), userEntityUpdated.getName(), userEntityUpdated.getBirthDate(), userEntityUpdated.getMotherName());
    }

    @Override
    @Transactional
    public Boolean delete(User user) {
        springDataUserRepository.deleteById(user.uuid());
        return !springDataUserRepository.existsById(user.uuid());
    }
}
