package it.unicam.cs.ids.LoyaltyHub.service;

import it.unicam.cs.ids.LoyaltyHub.controller.EntityController;
import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.exception.IdConflictException;
import it.unicam.cs.ids.LoyaltyHub.model.Costumer;
import it.unicam.cs.ids.LoyaltyHub.model.LoyaltyProgram;

/**
 * Interface for managing {@link Costumer} entities within the loyalty platform.
 * Extends {@link EntityController} to provide generic entity management functionalities
 * and includes additional methods specific to costumer management, such as updating
 * costumer details with associated loyalty programs.
 */
public interface CostumerManager extends EntityController<Costumer, Long> {

    /**
     * Updates an existing {@link Costumer} with a specified {@link LoyaltyProgram}.
     * Associates the costumer with the given loyalty program, potentially altering their
     * membership status or benefits within the platform.
     *
     * @param costumerId The ID of the costumer to be updated.
     * @param program The loyalty program to associate with the costumer.
     * @return The updated {@link Costumer} entity with the associated loyalty program.
     * @throws EntityNotFoundException if the costumer with the specified ID does not exist.
     * @throws IdConflictException if there are conflicts related to the loyalty program association.
     */
    Costumer updateWithLoyaltyProgram(Long costumerId, LoyaltyProgram program) throws EntityNotFoundException, IdConflictException;
}
