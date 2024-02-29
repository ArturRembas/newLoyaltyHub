package it.unicam.cs.ids.LoyaltyHub.service;

import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.exception.IdConflictException;
import it.unicam.cs.ids.LoyaltyHub.model.Activity;
import it.unicam.cs.ids.LoyaltyHub.model.LoyaltyProgram;
import it.unicam.cs.ids.LoyaltyHub.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * Service class for managing activities within the loyalty platform.
 * It handles the creation, retrieval, and deletion of activities, as well as updating activities with loyalty programs.
 */
@Validated
@Service
public class SimpleActivityManager implements ActivityManager {

    @Autowired
    private ActivityRepository activityRepository;

    /**
     * Retrieves an activity by its ID.
     *
     * @param id The ID of the activity.
     * @return The retrieved {@link Activity}.
     * @throws EntityNotFoundException if the activity is not found.
     */
    @Override
    public Activity getInstance(Long id) throws EntityNotFoundException {
        return activityRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Activity with ID: " + id + " not found."));
    }

    /**
     * Creates a new activity after performing necessary validations.
     *
     * @param object The {@link Activity} to create.
     * @return The saved {@link Activity}.
     * @throws EntityNotFoundException, IdConflictException if validations fail.
     */
    @Override
    public Activity create(Activity object) throws EntityNotFoundException, IdConflictException {
        checkIfValuesAreNotNull(object);
        checkCreation(object);
        return activityRepository.save(object);
    }

    /**
     * Updates an existing activity. This method is currently not implemented.
     *
     * @param object The {@link Activity} to update.
     * @return null as this operation is not supported.
     */
    @Override
    public Activity update(Activity object) throws EntityNotFoundException, IdConflictException {
        throw new UnsupportedOperationException("Update operation is not supported.");
    }

    /**
     * Updates an activity with a specified loyalty program.
     *
     * @param activityId The ID of the activity to update.
     * @param program The {@link LoyaltyProgram} to associate with the activity.
     * @return The updated {@link Activity} with the associated loyalty program.
     * @throws EntityNotFoundException if the activity or loyalty program is not found.
     */
    @Override
    public Activity updateWithLoyaltyProgram(Long activityId, LoyaltyProgram program) throws EntityNotFoundException {
        Activity activity = activityRepository.findById(activityId).orElseThrow(() ->
                new EntityNotFoundException("Activity with ID: " + activityId + " not found."));
        activity.setLoyaltyProgram(program);
        return activityRepository.save(activity);
    }

    /**
     * Deletes an activity by its ID.
     *
     * @param id The ID of the activity to delete.
     * @return {@code true} if the activity was successfully deleted, {@code false} otherwise.
     * @throws EntityNotFoundException if the activity does not exist.
     */
    @Override
    public boolean delete(Long id) throws EntityNotFoundException {
        if (!activityRepository.existsById(id)) {
            throw new EntityNotFoundException("Activity with ID: " + id + " not found, cannot delete.");
        }
        activityRepository.deleteById(id);
        return !activityRepository.existsById(id);
    }

    /**
     * Checks the existence of an activity by its ID.
     *
     * @param id The ID of the activity.
     * @return {@code true} if the activity exists, {@code false} otherwise.
     */
    @Override
    public boolean exists(Long id) {
        return activityRepository.existsById(id);
    }

    /**
     * Validates the creation of an activity, ensuring no conflicts with existing data.
     *
     * @param activity The {@link Activity} to validate.
     * @throws IdConflictException if the activity conflicts with existing activities.
     */
    private void checkCreation(Activity activity) throws IdConflictException {
        if (activityRepository.existsByEmail(activity.getEmail())) {
            throw new IdConflictException("Activity already exists with email: " + activity.getEmail());
        }
    }

    /**
     * Ensures all required values in an activity are provided.
     *
     * @param activity The {@link Activity} to check.
     * @throws NullPointerException if essential data is missing.
     */
    private void checkIfValuesAreNotNull(Activity activity) {
        Objects.requireNonNull(activity.getEmail(), "Email is required.");
        Objects.requireNonNull(activity.getPhone(), "Phone number is required.");
    }
}
