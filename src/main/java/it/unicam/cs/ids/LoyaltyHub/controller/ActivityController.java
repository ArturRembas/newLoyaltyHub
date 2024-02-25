package it.unicam.cs.ids.LoyaltyHub.controller;

import it.unicam.cs.ids.LoyaltyHub.model.Activity;
import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.exception.IdConflictException;

/**
 * Controller interface for {@link Activity} entities.
 * Extends the {@link EntityController} interface to provide CRUD operations and potentially other custom functionalities specific to {@link Activity} entities.
 * This interface is intended to be implemented by controllers that handle HTTP requests for {@link Activity} entities.
 */
public interface ActivityController extends EntityController<Activity, Long> {

    /**
     * Creates a new {@link Activity} with an associated admin.
     * This method may throw {@link IdConflictException} if there is an ID conflict or {@link EntityNotFoundException} if the associated admin is not found.
     *
     * @param activity The {@link Activity} to be created with an associated admin.
     * @return The created {@link Activity} entity.
     * @throws IdConflictException If there is a conflict with the activity's ID.
     * @throws EntityNotFoundException If the associated admin is not found.
     */
    Activity createActivityWithAdmin(Activity activity) throws IdConflictException, EntityNotFoundException;
}
