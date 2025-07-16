package zuzz.learn.graphql_example.controller;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import zuzz.learn.graphql_example.dto.AccountRequest;
import zuzz.learn.graphql_example.dto.AccountResponse;
import zuzz.learn.graphql_example.entity.Account;
import zuzz.learn.graphql_example.repository.AccountRepository;
import zuzz.learn.graphql_example.service.AccountService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AccountGraphQLController {

    private final AccountRepository accountRepository;
    private final AccountService accountService;

    @QueryMapping
    public List<Account> listAccounts() {
        return accountRepository.findAll();
    }

    @QueryMapping
    public Account getAccountById(@Argument String id) {
        log.info("ID received: "+id);
        List<Account> accounts = accountRepository.findAll();

        for (Account account : accounts) {
            if (account.getId().trim().equals(id.trim())) {
                log.info("Account found: " + account.toString());
                return account;
            }
        }

        throw new RuntimeException(String.format("Account %s not found", id));
    }

    @MutationMapping
    public AccountResponse addAccount(@Argument AccountRequest account) {
        return accountService.addAccount(account);
    }

    @MutationMapping
    public AccountResponse updateAccount(@Argument String id, @Argument AccountRequest account) {
        return accountService.updateAccount(id, account);
    }

    @MutationMapping
    public String deleteAccount(@Argument String id) {
        accountRepository.deleteById(id);
        return "Account eliminated correctly";
    }
}
