package it.unicam.cs.ids.LoyaltyHub.service;

import it.unicam.cs.ids.LoyaltyHub.model.Activity;
import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.exception.IdConflictException;

/**
 * Management interface for {@link Activity} entities.
 * Extends the {@link EntityManager} interface to provide specific management operations for {@link Activity} entities.
 */
public interface ActivityManager extends EntityManager<Activity, Long> {

    /**
     * Creates a new {@link Activity} with an associated admin email.
     * This method might throw an {@link IdConflictException} if the activity ID conflicts with an existing entity,
     * or an {@link EntityNotFoundException} if the associated admin email does not exist.
     *
     * @param activity The {@link Activity} to be created.
     * @return The created {@link Activity} entity.
     * @throws IdConflictException If there's a conflict with the activity's ID.
     * @throws EntityNotFoundException If the associated admin email is not found.
     */
    Activity createActivityWithAdminEmail(Activity activity) throws IdConflictException, EntityNotFoundException;

    /**
     * Retrieves an {@link Activity} entity by its ID.
     *
     * @param id The ID of the {@link Activity} to retrieve.
     * @return The found {@link Activity} entity.
     */
    Activity getActivityById(long id);
}
