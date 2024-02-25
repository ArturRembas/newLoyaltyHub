package it.unicam.cs.ids.LoyaltyHub.controller;

import it.unicam.cs.ids.LoyaltyHub.model.FidelityCard;

/**
 * Controller interface for {@link FidelityCard} entities.
 * Extends the {@link EntityController} interface to provide CRUD operations and additional functionalities specific to {@link FidelityCard} entities.
 * This interface includes methods to create {@link FidelityCard} entities with or without an associated loyalty program.
 */
public interface FidelityCardController extends EntityController<FidelityCard, Long> {

    /**
     * Creates a {@link FidelityCard} with an associated loyalty program.
     *
     * @param costumerEmail Email of the costumer to whom the card will be associated.
     * @param loyaltyProgramName Name of the loyalty program to associate with the card.
     * @return The created {@link FidelityCard} with the associated loyalty program.
     */
    FidelityCard createWithLoyaltyProgram(String costumerEmail, String loyaltyProgramName);

    /**
     * Creates a {@link FidelityCard} without an associated loyalty program.
     *
     * @param costumerEmail Email of the costumer to whom the card will be associated.
     * @return The created {@link FidelityCard} without an associated loyalty program.
     * @throws SpecificException if an error occurs during the creation process.
     */
    FidelityCard createWithoutLoyaltyProgram(String costumerEmail) throws Exception;
}
