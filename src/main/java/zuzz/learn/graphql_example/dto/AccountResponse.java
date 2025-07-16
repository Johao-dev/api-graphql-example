package zuzz.learn.graphql_example.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zuzz.learn.graphql_example.entity.AccountType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {

    private String id;
    private LocalDate creationDate;
    private Double balance;
    private String badge;
    private AccountType accountType;
}
