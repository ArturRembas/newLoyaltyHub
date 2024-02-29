package it.unicam.cs.ids.LoyaltyHub.service;

import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.model.Transaction;
import it.unicam.cs.ids.LoyaltyHub.repository.TransactionRepository;
import it.unicam.cs.ids.LoyaltyHub.repository.FidelityCardRepository;
import it.unicam.cs.ids.LoyaltyHub.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * Service class for managing transaction entities within the loyalty platform.
 * Provides functionalities to create, retrieve, delete transactions, and update their status.
 */
@Validated
@Service
public class SimpleTransactionManager implements TransactionManager {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private FidelityCardRepository fidelityCardRepository;

    @Autowired
    private ActivityRepository activityRepository;

    /**
     * Retrieves a transaction by its ID.
     *
     * @param id The ID of the transaction.
     * @return The {@link Transaction} object.
     * @throws EntityNotFoundException if the transaction is not found.
     */
    @Override
    public Transaction getInstance(Long id) throws EntityNotFoundException {
        return transactionRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Transaction with ID " + id + " not found."));
    }

    /**
     * Creates a new transaction and associates it with the corresponding fidelity card and activity.
     *
     * @param object The {@link Transaction} object to create.
     * @return The created {@link Transaction} object.
     */
    @Override
    public Transaction create(Transaction object) throws EntityNotFoundException {
        object.setFidelityCard(fidelityCardRepository.findById(object.getFidelityCard().getId())
            .orElseThrow(() -> new EntityNotFoundException("Fidelity card not found")));
        object.setActivity(activityRepository.findById(object.getActivity().getId())
            .orElseThrow(() -> new EntityNotFoundException("Activity not found")));
        return transactionRepository.save(object);
    }

    /**
     * Updates an existing transaction. This method is currently not implemented.
     *
     * @param object The {@link Transaction} object to update.
     * @return null as this operation is not supported.
     */
    @Override
    public Transaction update(Transaction object) {
        throw new UnsupportedOperationException("Transaction update not supported.");
    }

    /**
     * Deletes a transaction by its ID.
     *
     * @param id The ID of the transaction to delete.
     * @return true if the deletion was successful, false otherwise.
     * @throws EntityNotFoundException if the transaction does not exist.
     */
    @Override
    public boolean delete(Long id) throws EntityNotFoundException {
        if (!transactionRepository.existsById(id)) {
            throw new EntityNotFoundException("Transaction with ID " + id + " not found, cannot delete.");
        }
        transactionRepository.deleteById(id);
        return !transactionRepository.existsById(id);
    }

    /**
     * Checks if a transaction exists by its ID.
     *
     * @param id The ID of the transaction.
     * @return true if the transaction exists, false otherwise.
     */
    @Override
    public boolean exists(Long id) {
        return transactionRepository.existsById(id);
    }

    /**
     * Updates the validation status of a transaction.
     *
     * @param transactionId The ID of the transaction to update.
     * @throws EntityNotFoundException if the transaction is not found.
     */
    @Override
    public void updateStatus(Long transactionId) throws EntityNotFoundException {
        Transaction transaction = getInstance(transactionId);
        transaction.setValidated(!transaction.isValidated());
        transactionRepository.save(transaction);
    }
}
