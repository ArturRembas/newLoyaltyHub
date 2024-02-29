package it.unicam.cs.ids.LoyaltyHub.service;

import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.model.Transaction;

/**
 * Interface for managing transaction entities within the loyalty platform.
 * Extends the generic {@link EntityManager} interface with additional functionalities specific to transactions.
 */
public interface TransactionManager extends EntityManager<Transaction, Long> {

    /**
     * Updates the status of a given transaction.
     * This method is intended to toggle or set a new status for the transaction, such as marking it as validated.
     *
     * @param transactionId The ID of the transaction whose status is to be updated.
     * @throws EntityNotFoundException if the transaction with the given ID does not exist.
     */
    void updateStatus(Long transactionId) throws EntityNotFoundException;
}
