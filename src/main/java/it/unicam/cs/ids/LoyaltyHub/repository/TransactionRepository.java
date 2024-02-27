package it.unicam.cs.ids.LoyaltyHub.repository;

import it.unicam.cs.ids.LoyaltyHub.model.Transaction;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository interface for managing {@link Transaction} entities. Provides methods to perform CRUD operations
 * and custom queries such as updating the validation status of transactions.
 */
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    /**
     * Updates the validation status of transactions where the current validation status matches a specified value.
     *
     * @param newValidationStatus The new validation status to be set.
     * @param currentValidationStatus The current validation status to match for the update.
     * @return The number of transactions updated.
     */
    @Transactional
    @Modifying
    @Query("UPDATE Transaction t SET t.validated = ?1 WHERE t.validated = ?2")
    int updateValidatedByValidated(boolean newValidationStatus, boolean currentValidationStatus);
}
