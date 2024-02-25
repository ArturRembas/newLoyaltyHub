package it.unicam.cs.ids.LoyaltyHub.service;

import it.unicam.cs.ids.LoyaltyHub.model.FidelityCard;
import it.unicam.cs.ids.LoyaltyHub.model.LoyaltyProgram;

/**
 * Management interface for {@link FidelityCard} entities.
 * Extends the {@link EntityManager} interface to provide specific management operations for {@link FidelityCard} entities.
 */
public interface FidelityCardManager extends EntityManager<FidelityCard, Long> {

    /**
     * Creates a new {@link FidelityCard} associated with a {@link LoyaltyProgram} using the customer's email and the loyalty program's name.
     *
     * @param costumerEmail The email of the customer to whom the fidelity card is to be associated.
     * @param loyaltyProgramName The name of the loyalty program to associate with the new fidelity card.
     * @return The newly created {@link FidelityCard} with the associated loyalty program.
     */
    FidelityCard createWithLoyaltyProgram(String costumerEmail, String loyaltyProgramName);

    /**
     * Creates a new {@link FidelityCard} for a customer without associating it with a {@link LoyaltyProgram}.
     *
     * @param costumerEmail The email of the customer to whom the fidelity card is to be created.
     * @return The newly created {@link FidelityCard} without any associated loyalty program.
     * @throws Exception If the creation process encounters any issues.
     */
    FidelityCard createWithoutLoyaltyProgram(String costumerEmail) throws Exception;
}
