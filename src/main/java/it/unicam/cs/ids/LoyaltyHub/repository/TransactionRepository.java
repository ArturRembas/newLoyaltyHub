package it.unicam.cs.ids.LoyaltyHub.repository;

import it.unicam.cs.ids.LoyaltyHub.model.Transactions;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for {@link Transactions} entities.
 * This interface extends the {@link CrudRepository} provided by Spring Data,
 * facilitating CRUD operations for {@link Transactions} entities.
 * 
 * Additional query methods can be defined here if necessary to handle specific queries
 * related to transaction entities.
 */
public interface TransactionRepository extends CrudRepository<Transactions, Long> {
    
}
