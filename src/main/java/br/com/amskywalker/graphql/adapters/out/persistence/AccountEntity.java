package br.com.amskywalker.graphql.adapters.out.persistence;

import br.com.amskywalker.graphql.domain.model.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private Double balance;

    private AccountType type;

    @OneToMany
    private List<UserEntity> userEntities;

    public AccountEntity(UUID uuid, Double balance, AccountType type) {}
}
