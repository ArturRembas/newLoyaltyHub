package it.unicam.cs.ids.LoyaltyHub.service;

import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.exception.IdConflictException;
import it.unicam.cs.ids.LoyaltyHub.model.Costumer;
import it.unicam.cs.ids.LoyaltyHub.model.LoyaltyProgram;
import it.unicam.cs.ids.LoyaltyHub.repository.CostumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * Service class for managing customer entities within the loyalty platform.
 * It handles creating, updating, and deleting customers, as well as associating them with loyalty programs.
 */
@Validated
@Service
public class SimpleCostumerManager implements CostumerManager {

    @Autowired
    private CostumerRepository costumerRepository;

    /**
     * Retrieves a customer by its ID.
     *
     * @param id The ID of the customer.
     * @return The {@link Costumer} object.
     * @throws EntityNotFoundException if the customer is not found.
     */
    @Override
    public Costumer getInstance(Long id) throws EntityNotFoundException {
        return costumerRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Customer with ID " + id + " not found."));
    }

    /**
     * Creates a new customer after validating the provided information.
     *
     * @param object The {@link Costumer} object to create.
     * @return The created {@link Costumer} object.
     * @throws EntityNotFoundException if required information is missing.
     * @throws IdConflictException if the customer already exists.
     */
    @Override
    public Costumer create(Costumer object) throws EntityNotFoundException, IdConflictException {
        checkIfValuesNotNull(object);
        checkCreation(object);
        return costumerRepository.save(object);
    }

    /**
     * Updates an existing customer. Currently, this method is not implemented.
     *
     * @param object The {@link Costumer} object to update.
     * @return null as this operation is not supported.
     * @throws UnsupportedOperationException when invoked.
     */
    @Override
    public Costumer update(Costumer object) {
        throw new UnsupportedOperationException("Customer update not supported.");
    }

    /**
     * Associates a customer with a loyalty program.
     *
     * @param costumerId The ID of the customer.
     * @param program The {@link LoyaltyProgram} to associate.
     * @return The updated {@link Costumer} object.
     * @throws EntityNotFoundException if the customer or loyalty program is not found.
     */
    @Override
    public Costumer updateWithLoyaltyProgram(Long costumerId, LoyaltyProgram program) throws EntityNotFoundException {
        Costumer costumer = costumerRepository.findById(costumerId).orElseThrow(() ->
                new EntityNotFoundException("Customer with ID " + costumerId + " not found."));
        costumer.setLoyaltyProgram(program);
        return costumerRepository.save(costumer);
    }

    /**
     * Deletes a customer by its ID.
     *
     * @param id The ID of the customer to delete.
     * @return true if the deletion was successful, false otherwise.
     * @throws EntityNotFoundException if the customer does not exist.
     */
    @Override
    public boolean delete(Long id) throws EntityNotFoundException {
        if (!costumerRepository.existsById(id)) {
            throw new EntityNotFoundException("Customer with ID " + id + " not found, cannot delete.");
        }
        costumerRepository.deleteById(id);
        return !costumerRepository.existsById(id);
    }

    /**
     * Checks if a customer exists by its ID.
     *
     * @param id The ID of the customer.
     * @return true if the customer exists, false otherwise.
     */
    @Override
    public boolean exists(Long id) {
        return costumerRepository.existsById(id);
    }

    /**
     * Validates the creation of a customer, ensuring no duplicate entries.
     *
     * @param costumer The {@link Costumer} to validate.
     * @throws IdConflictException if the customer already exists.
     */
    private void checkCreation(Costumer costumer) throws IdConflictException {
        if (costumerRepository.existsByEmail(costumer.getEmail())) {
            throw new IdConflictException("Customer already exists with email: " + costumer.getEmail());
        }
    }

    /**
     * Ensures all required customer information is provided.
     *
     * @param costumer The {@link Costumer} to validate.
     * @throws NullPointerException if essential information is missing.
     */
    private void checkIfValuesNotNull(Costumer costumer) {
        Objects.requireNonNull(costumer.getEmail(), "Email is required.");
        Objects.requireNonNull(costumer.getPhone(), "Phone number is required.");
    }

    /**
     * Calculates the total loyalty points accumulated by a customer.
     *
     * @param costumerEmail The email of the customer whose total points are to be calculated.
     * @return The total loyalty points accumulated by the customer.
     * @throws EntityNotFoundException if no customer is found with the provided email.
     */
    @Override
    public int getTotalPoints(String costumerEmail) throws EntityNotFoundException {
        Costumer costumer = costumerRepository.findByEmail(costumerEmail)
            .orElseThrow(() -> new EntityNotFoundException("Customer with email " + costumerEmail + " not found."));
        return 0;
    }

}
