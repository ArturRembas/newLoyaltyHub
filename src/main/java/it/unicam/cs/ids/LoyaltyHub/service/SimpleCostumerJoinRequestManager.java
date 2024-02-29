package it.unicam.cs.ids.LoyaltyHub.service;

import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.exception.IdConflictException;
import it.unicam.cs.ids.LoyaltyHub.model.CostumerJoinRequest;
import it.unicam.cs.ids.LoyaltyHub.repository.CostumerJoinRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * Service class for managing customer join requests within the loyalty platform.
 * It handles the creation, retrieval, and deletion of join requests, along with validation of request data.
 */
@Validated
@Service
public class SimpleCostumerJoinRequestManager implements CostumerJoinRequestManager {

    @Autowired
    private CostumerJoinRequestRepository requestRepository;

    /**
     * Retrieves a customer join request by its ID.
     *
     * @param id The ID of the customer join request.
     * @return The retrieved {@link CostumerJoinRequest}.
     * @throws EntityNotFoundException if the request is not found.
     */
    @Override
    public CostumerJoinRequest getInstance(Long id) throws EntityNotFoundException {
        return requestRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Customer join request with ID: " + id + " not found."));
    }

    /**
     * Creates a new customer join request after performing necessary validations.
     *
     * @param object The {@link CostumerJoinRequest} to create.
     * @return The saved {@link CostumerJoinRequest}.
     * @throws EntityNotFoundException, IdConflictException if validations fail.
     */
    @Override
    public CostumerJoinRequest create(CostumerJoinRequest object) throws EntityNotFoundException, IdConflictException {
        checkIfValuesAreNotNull(object);
        checkCreation(object);
        return requestRepository.save(object);
    }

    /**
     * Updates an existing customer join request. This method is currently not implemented.
     *
     * @param object The {@link CostumerJoinRequest} to update.
     * @return null as this operation is not supported.
     */
    @Override
    public CostumerJoinRequest update(CostumerJoinRequest object) throws EntityNotFoundException, IdConflictException {
        throw new UnsupportedOperationException("Update operation is not supported for customer join requests.");
    }

    /**
     * Deletes a customer join request by its ID.
     *
     * @param id The ID of the customer join request to delete.
     * @return {@code true} if the request was successfully deleted, {@code false} otherwise.
     * @throws EntityNotFoundException if the request does not exist.
     */
    @Override
    public boolean delete(Long id) throws EntityNotFoundException, IdConflictException {
        if (!this.exists(id)) {
            throw new EntityNotFoundException("Customer join request with ID: " + id + " does not exist.");
        }
        requestRepository.deleteById(id);
        return !this.exists(id);
    }

    /**
     * Checks the existence of a customer join request by its ID.
     *
     * @param id The ID of the customer join request.
     * @return {@code true} if the request exists, {@code false} otherwise.
     */
    @Override
    public boolean exists(Long id) {
        return requestRepository.existsById(id);
    }

    /**
     * Validates the creation of a customer join request, ensuring no conflicts with existing data.
     *
     * @param request The {@link CostumerJoinRequest} to validate.
     * @throws IdConflictException if the request conflicts with existing requests.
     */
    private void checkCreation(CostumerJoinRequest request) throws IdConflictException {
        if (requestRepository.existsByCostumerEmail(request.getCostumerEmail()) || 
            requestRepository.existsByPhone(request.getPhone())) {
            throw new IdConflictException("A request already exists with this email or phone number.");
        }
    }

    /**
     * Ensures all required values in a customer join request are provided.
     *
     * @param request The {@link CostumerJoinRequest} to check.
     * @throws NullPointerException if essential data is missing.
     */
    private void checkIfValuesAreNotNull(CostumerJoinRequest request) {
        Objects.requireNonNull(request.getCostumerEmail(), "Customer email is required.");
        Objects.requireNonNull(request.getPhone(), "Phone number is required.");
    }
}
