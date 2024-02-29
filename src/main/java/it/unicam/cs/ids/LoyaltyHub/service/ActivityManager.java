package it.unicam.cs.ids.LoyaltyHub.service;

import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.exception.IdConflictException;
import it.unicam.cs.ids.LoyaltyHub.model.Activity;
import it.unicam.cs.ids.LoyaltyHub.model.LoyaltyProgram;

/**
 * Interface for managing {@link Activity} entities within the loyalty platform.
 * Extends the generic {@link EntityManager} for basic CRUD operations and includes
 * additional functionality to update activities with loyalty programs.
 */
public interface ActivityManager extends EntityManager<Activity, Long> {

    /**
     * Updates an existing {@link Activity} with a specified {@link LoyaltyProgram}.
     * This method allows linking or changing the loyalty program associated with an activity.
     *
     * @param activityId The ID of the activity to update.
     * @param program The loyalty program to associate with the activity.
     * @return The updated {@link Activity} with the associated loyalty program.
     * @throws EntityNotFoundException if the activity with the specified ID does not exist.
     * @throws IdConflictException if there are conflicts related to the loyalty program association.
     */
    Activity updateWithLoyaltyProgram(Long activityId, LoyaltyProgram program) throws EntityNotFoundException, IdConflictException;

	Activity getActivityByEmail(String activityEmail);
}
