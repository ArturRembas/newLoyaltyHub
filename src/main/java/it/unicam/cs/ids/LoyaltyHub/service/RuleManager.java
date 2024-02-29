package it.unicam.cs.ids.LoyaltyHub.service;

import it.unicam.cs.ids.LoyaltyHub.model.rules.Rule;

/**
 * Interface for managing {@link Rule} entities within the loyalty platform.
 * Extends the generic {@code EntityManager} interface to provide CRUD operations and
 * possibly additional functionalities specific to rule management.
 */
public interface RuleManager extends EntityManager<Rule, Long> {
    
}
