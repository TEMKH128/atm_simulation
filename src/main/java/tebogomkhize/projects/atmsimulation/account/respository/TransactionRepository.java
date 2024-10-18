package tebogomkhize.projects.atmsimulation.account.respository;

import java.util.List;
import java.time.LocalDate;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import tebogomkhize.projects.atmsimulation.account.model.Transaction;


@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
    List<Transaction> findByAccNumAndDateBetween(
        String accNum, LocalDate start, LocalDate end);
}
