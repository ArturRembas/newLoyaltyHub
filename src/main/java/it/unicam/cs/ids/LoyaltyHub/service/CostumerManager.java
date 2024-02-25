package it.unicam.cs.ids.LoyaltyHub.service;

import it.unicam.cs.ids.LoyaltyHub.model.Costumer;

/**
 * Management interface for {@link Costumer} entities.
 * Extends the {@link EntityManager} interface to provide specific management operations for {@link Costumer} entities.
 * This interface can be extended to include custom management operations related to {@link Costumer} entities.
 */
public interface CostumerManager extends EntityManager<Costumer, Long> {
    
}
