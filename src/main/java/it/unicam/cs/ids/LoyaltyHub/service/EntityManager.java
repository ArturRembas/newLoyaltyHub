package it.unicam.cs.ids.LoyaltyHub.service;

import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.exception.IdConflictException;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * Provides a generic interface to manage CRUD operations for entities of type T with an ID of type I.
 * This interface is intended to be implemented by services that handle specific entity types within the LoyaltyHub system.
 */
@Transactional
public interface EntityManager <T, I extends Number> {

    /**
     * Retrieves an entity of type T using its ID.
     *
     * @param id The ID of the entity to retrieve.
     * @return The retrieved entity.
     * @throws EntityNotFoundException if the entity with the specified ID does not exist.
     */
    T getInstance(@Valid @NotNull I id) throws EntityNotFoundException;

    /**
     * Creates a new entity of type T.
     *
     * @param object The entity to be created.
     * @return The created entity.
     * @throws IdConflictException if there is an ID conflict.
     */
    T create(T object) throws EntityNotFoundException, IdConflictException;

    /**
     * Updates an existing entity of type T.
     *
     * @param object The entity to update.
     * @return The updated entity.
     * @throws EntityNotFoundException if the entity to update does not exist.
     */
    T update(T object) throws EntityNotFoundException, IdConflictException;

    /**
     * Deletes an entity of type T by its ID.
     *
     * @param id The ID of the entity to delete.
     * @return true if the entity was successfully deleted, false otherwise.
     * @throws EntityNotFoundException if the entity with the specified ID does not exist.
     */
    boolean delete(I id) throws EntityNotFoundException, IdConflictException;

    /**
     * Checks if an entity of type T with the given ID exists.
     *
     * @param id The ID of the entity to check.
     * @return true if the entity exists, false otherwise.
     */
    boolean exists(I id);
}
