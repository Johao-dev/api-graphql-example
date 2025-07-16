package zuzz.learn.graphql_example.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import zuzz.learn.graphql_example.dto.AccountResponse;
import zuzz.learn.graphql_example.entity.Account;

@Component
public class AccountMapper {

    public AccountResponse fromAccount(Account account) {
        AccountResponse response = new AccountResponse();
        BeanUtils.copyProperties(account, response);
        return response;
    }
}
