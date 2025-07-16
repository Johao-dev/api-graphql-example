package zuzz.learn.graphql_example.service.impl;

import java.util.UUID;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import zuzz.learn.graphql_example.dto.AccountRequest;
import zuzz.learn.graphql_example.dto.AccountResponse;
import zuzz.learn.graphql_example.entity.Account;
import zuzz.learn.graphql_example.mapper.AccountMapper;
import zuzz.learn.graphql_example.repository.AccountRepository;
import zuzz.learn.graphql_example.service.AccountService;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper mapper;

    @Override
    public AccountResponse addAccount(AccountRequest accountRequest) {
        Account account = Account.builder()
            .id(UUID.randomUUID().toString())
            .creationDate(LocalDate.now())
            .balance(accountRequest.getBalance())
            .accountType(accountRequest.getAccountType())
            .badge(accountRequest.getBadge())
            .build();

        Account accountDb = accountRepository.save(account);
        AccountResponse response = mapper.fromAccount(accountDb);

        return response;
    }

    @Override
    public AccountResponse updateAccount(String id, AccountRequest accountRequest) {
        Account account = Account.builder()
            .id(id)
            .creationDate(LocalDate.now())
            .balance(accountRequest.getBalance())
            .accountType(accountRequest.getAccountType())
            .badge(accountRequest.getBadge())
            .build();

        Account accountDb = accountRepository.save(account);
        AccountResponse response = mapper.fromAccount(accountDb);

        return response;
    }
}
