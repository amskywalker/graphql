package br.com.amskywalker.graphql.application.ports.output;

import br.com.amskywalker.graphql.domain.model.Account;

import java.util.UUID;

public interface AccountRepository {
    Account save(Account account);
    Account findById(UUID id);
    Account update(Account account);
    Boolean delete(Account account);
}
