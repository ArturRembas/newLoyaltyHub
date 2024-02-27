package it.unicam.cs.ids.LoyaltyHub.controller;

import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.exception.IdConflictException;
import it.unicam.cs.ids.LoyaltyHub.model.Costumer;
import it.unicam.cs.ids.LoyaltyHub.model.FidelityCard;

/**
 * Controller interface for {@link FidelityCard} entities.
 * Extends the {@link EntityController} interface to provide CRUD operations and additional functionalities specific to {@link FidelityCard} entities.
 * This interface includes methods to create {@link FidelityCard} entities with or without an associated loyalty program.
 */
public interface FidelityCardController extends EntityController<FidelityCard, Long> {

    /**
     * Add a {@link FidelityCard} to a {@link Costumer}.
     *
     * @param (@link Costumer)
     * @throws (@link IdConflictException), (@link EntityNotFoundException)
     */
	void addNewCardToCostumer(Costumer costumer) throws IdConflictException, EntityNotFoundException;
}
