package tebogomkhize.projects.atmsimulation.account.respository;

import org.springframework.data.repository.CrudRepository;
import tebogomkhize.projects.atmsimulation.account.model.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
}
