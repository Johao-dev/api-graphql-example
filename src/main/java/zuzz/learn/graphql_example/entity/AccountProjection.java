package zuzz.learn.graphql_example.entity;

import org.springframework.data.rest.core.config.Projection;

@Projection(types = Account.class, name = "account_projection")
public interface AccountProjection {

    public String getId();

    public AccountType getAccountType();

    public Double getBalance();
}