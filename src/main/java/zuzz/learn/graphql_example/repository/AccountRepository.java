package zuzz.learn.graphql_example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import zuzz.learn.graphql_example.entity.Account;
import zuzz.learn.graphql_example.entity.AccountType;

@RepositoryRestResource
public interface AccountRepository extends JpaRepository<Account, String> {

    @RestResource(path = "/by-type")
    List<Account> findByAccountType(@Param("t") AccountType accountType);
}
