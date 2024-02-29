package it.unicam.cs.ids.LoyaltyHub.service;

import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.model.LoyaltyProgram;

/**
 * Interface for managing {@link LoyaltyProgram} entities within the loyalty platform.
 * Extends the generic {@code EntityManager} interface to provide CRUD operations and
 * includes additional functionality for associating loyalty programs with specific rules.
 */
public interface LoyaltyProgramManager extends EntityManager<LoyaltyProgram, Long> {

    /**
     * Updates a {@link LoyaltyProgram} by associating it with a rule identified by the given rule name.
     * This method allows for the dynamic association of rules to loyalty programs, potentially altering
     * the program's behavior or benefits.
     *
     * @param programName The name of the loyalty program to update.
     * @param ruleName The name of the rule to associate with the loyalty program.
     * @return The updated {@link LoyaltyProgram} with the newly associated rule.
     * @throws EntityNotFoundException 
     */
    LoyaltyProgram updateWithRule(String programName, String ruleName) throws EntityNotFoundException;
}
