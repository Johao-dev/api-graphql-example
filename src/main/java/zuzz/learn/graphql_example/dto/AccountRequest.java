package zuzz.learn.graphql_example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zuzz.learn.graphql_example.entity.AccountType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {

    private Double balance;
    private String badge;
    private AccountType accountType;
}
