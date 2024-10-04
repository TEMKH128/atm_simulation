package tebogomkhize.projects.atmsimulation.account.respository;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import tebogomkhize.projects.atmsimulation.account.model.Account;


@Repository
public interface AccountRepository extends CrudRepository <Account, String> {
    Optional<Account> findByFirstNameAndLastNameAndAgeAndEmail(
        String firstName, String lastName, int age, String email);
}

