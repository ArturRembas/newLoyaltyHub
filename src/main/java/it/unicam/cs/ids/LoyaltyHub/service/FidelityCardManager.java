package it.unicam.cs.ids.LoyaltyHub.service;

import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.exception.IdConflictException;
import it.unicam.cs.ids.LoyaltyHub.model.FidelityCard;

/**
 * Interface for managing {@link FidelityCard} entities within the loyalty platform.
 * Provides CRUD operations through the {@code EntityManager} interface and includes
 * additional functionality for updating fidelity card points.
 */
public interface FidelityCardManager extends EntityManager<FidelityCard, Long> {

    /**
     * Updates the points on a specified {@link FidelityCard}.
     *
     * @param object The {@link FidelityCard} object to update.
     * @param points The new points value to set on the fidelity card.
     * @return The updated points value.
     * @throws EntityNotFoundException if the fidelity card is not found.
     * @throws IdConflictException if there is a conflict with the fidelity card's ID.
     */
    int updatePoints(FidelityCard object, int points) throws EntityNotFoundException, IdConflictException;
}
