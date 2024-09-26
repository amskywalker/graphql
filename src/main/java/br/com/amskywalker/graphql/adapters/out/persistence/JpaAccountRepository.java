package br.com.amskywalker.graphql.adapters.out.persistence;

import br.com.amskywalker.graphql.application.ports.output.AccountRepository;
import br.com.amskywalker.graphql.domain.exceptions.AccountNotFoundException;
import br.com.amskywalker.graphql.domain.model.Account;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaAccountRepository implements AccountRepository {

    private final SpringDataAccountRepository springDataAccountRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public JpaAccountRepository(SpringDataAccountRepository springDataAccountRepository, ModelMapper modelMapper){
        this.springDataAccountRepository = springDataAccountRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Account save(Account account) {
        AccountEntity accountEntity = modelMapper.map(account, AccountEntity.class);
        AccountEntity accountCreated = springDataAccountRepository.save(accountEntity);
        return modelMapper.map(accountCreated, Account.class);
    }

    @Override
    public Account findById(UUID id) {
        Optional<AccountEntity> hasAccount = springDataAccountRepository.findById(id);

        if (hasAccount.isEmpty()) {
            throw new AccountNotFoundException("Account not found");
        }

        AccountEntity accountEntity = hasAccount.get();
        return modelMapper.map(accountEntity, Account.class);
    }
    @Override
    @Transactional
    public Account update(Account account) {
        Optional<AccountEntity> hasAccount = springDataAccountRepository.findById(account.uuid());

        if (hasAccount.isEmpty()) {
            throw new AccountNotFoundException("Account not found");
        }

        AccountEntity accountEntity = hasAccount.get();
        modelMapper.map(account, accountEntity);

        AccountEntity accountUpdated = springDataAccountRepository.save(accountEntity);

        return modelMapper.map(accountUpdated, Account.class);
    }

    @Override
    public Boolean delete(Account account) {
        springDataAccountRepository.deleteById(account.uuid());
        return !springDataAccountRepository.existsById(account.uuid());
    }
}
