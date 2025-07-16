package zuzz.learn.graphql_example.service;

import zuzz.learn.graphql_example.dto.AccountRequest;
import zuzz.learn.graphql_example.dto.AccountResponse;

public interface AccountService {

    AccountResponse addAccount(AccountRequest accountRequest);

    AccountResponse updateAccount(String id, AccountRequest accountRequest);
}
