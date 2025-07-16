package zuzz.learn.graphql_example.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import zuzz.learn.graphql_example.dto.AccountRequest;
import zuzz.learn.graphql_example.dto.AccountResponse;
import zuzz.learn.graphql_example.entity.Account;
import zuzz.learn.graphql_example.repository.AccountRepository;
import zuzz.learn.graphql_example.service.AccountService;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class AccountController {

    private final AccountRepository accountRepository;
    private final AccountService accountService;

    @GetMapping("/accounts")
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @GetMapping("/accounts/{id}")
    public Account findById(@PathVariable String id) {
        return accountRepository.findById(id)
            .orElseThrow(() -> new RuntimeException(String.format("Account %s not found", id)));
    }

    @PostMapping("/accounts")
    public AccountResponse save(@RequestBody AccountRequest request) {
        return accountService.addAccount(request);
    }

    @PutMapping("/accounts/{id}")
    public Account update(@PathVariable String id, @RequestBody Account account) {
        Account accountDb = accountRepository.findById(id).orElseThrow();

        accountDb.setBalance(account.getBalance());
        accountDb.setBadge(account.getBadge());
        accountDb.setAccountType(account.getAccountType());
        accountDb.setCreationDate(LocalDate.now());

        return accountRepository.save(accountDb);
    }

    @DeleteMapping("/accounts/{id}")
    public void delete(@PathVariable String id) {
        accountRepository.deleteById(id);
    }
}
