package it.unicam.cs.ids.LoyaltyHub.service;

import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.exception.IdConflictException;
import it.unicam.cs.ids.LoyaltyHub.model.ActivityJoinRequest;
import it.unicam.cs.ids.LoyaltyHub.repository.ActivityJoinRequestRepository;
import it.unicam.cs.ids.LoyaltyHub.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * Service class for managing activity join requests within the loyalty platform.
 * It handles the creation, retrieval, and deletion of join requests, along with validation of request data.
 */
@Validated
@Service
public class SimpleActivityJoinRequestManager implements ActivityJoinRequestManager {

    @Autowired
    private ActivityJoinRequestRepository requestRepository;

    @Autowired
    private ActivityRepository activityRepository;

    /**
     * Retrieves an activity join request by its ID.
     *
     * @param id The ID of the activity join request.
     * @return The retrieved {@link ActivityJoinRequest}.
     * @throws EntityNotFoundException if the request is not found.
     */
    @Override
    public ActivityJoinRequest getInstance(Long id) throws EntityNotFoundException {
        return requestRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Activity join request with ID: " + id + " not found."));
    }

    /**
     * Creates a new activity join request after performing necessary validations.
     *
     * @param object The {@link ActivityJoinRequest} to create.
     * @return The saved {@link ActivityJoinRequest}.
     * @throws EntityNotFoundException if required data is missing.
     * @throws IdConflictException if the request conflicts with existing data.
     */
    @Override
    public ActivityJoinRequest create(ActivityJoinRequest object) throws EntityNotFoundException, IdConflictException {
        this.checkIfValuesAreNotNull(object);
        this.checkCreation(object);
        return requestRepository.save(object);
    }

    /**
     * Updates an existing activity join request. This method is not implemented and will always return null.
     *
     * @param object The {@link ActivityJoinRequest} to update.
     * @return null as this operation is not supported.
     * @throws EntityNotFoundException, IdConflictException as this operation is not implemented.
     */
    @Override
    public ActivityJoinRequest update(ActivityJoinRequest object) throws EntityNotFoundException, IdConflictException {
        throw new UnsupportedOperationException("Update operation is not supported for activity join requests.");
    }

    /**
     * Deletes an activity join request by its ID.
     *
     * @param id The ID of the activity join request to delete.
     * @return {@code true} if the request was successfully deleted, {@code false} otherwise.
     * @throws EntityNotFoundException if the request does not exist.
     */
    @Override
    public boolean delete(Long id) throws EntityNotFoundException {
        if (!this.exists(id)) {
            throw new EntityNotFoundException("Activity join request with ID: " + id + " does not exist.");
        }
        requestRepository.deleteById(id);
        return !this.exists(id);
    }

    /**
     * Checks the existence of an activity join request by its ID.
     *
     * @param id The ID of the activity join request.
     * @return {@code true} if the request exists, {@code false} otherwise.
     */
    @Override
    public boolean exists(Long id) {
        return requestRepository.existsById(id);
    }

    /**
     * Validates the creation of an activity join request, ensuring no conflicts with existing data.
     *
     * @param request The {@link ActivityJoinRequest} to validate.
     * @throws EntityNotFoundException, IdConflictException if the request conflicts with existing activities or requests.
     */
    private void checkCreation(ActivityJoinRequest request) throws EntityNotFoundException, IdConflictException {
        if (requestRepository.existsByEmail(request.getActivityEmail())) {
            throw new IdConflictException("A request already exists with this email: " + request.getActivityEmail());
        }
        if (activityRepository.existsByEmail(request.getActivityEmail())) {
            throw new IdConflictException("An activity already exists with this email: " + request.getActivityEmail());
        }
    }

    /**
     * Ensures all required values in an activity join request are provided.
     *
     * @param request The {@link ActivityJoinRequest} to check.
     * @throws NullPointerException if essential data is missing.
     */
    private void checkIfValuesAreNotNull(ActivityJoinRequest request) {
        Objects.requireNonNull(request.getActivityEmail(), "Activity email is required.");
        Objects.requireNonNull(request.getPhone(), "Phone number is required.");
    }
}
