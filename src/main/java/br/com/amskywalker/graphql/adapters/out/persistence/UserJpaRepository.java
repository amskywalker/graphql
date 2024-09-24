package br.com.amskywalker.graphql.adapters.out.persistence;

import br.com.amskywalker.graphql.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<User, UUID> {
}
