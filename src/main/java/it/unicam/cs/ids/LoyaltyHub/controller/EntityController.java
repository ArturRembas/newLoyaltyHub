package it.unicam.cs.ids.LoyaltyHub.controller;

import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.exception.IdConflictException;

/**
 * Generic interface for entity controllers that defines standard CRUD operations.
 * This interface is designed to be implemented by controllers that manage HTTP requests for specific entity types.
 *
 * @param <T> the type of the entity
 * @param <I> the type of the entity identifier
 */
public interface EntityController<T, I> {

    /**
     * Retrieves an entity by its identifier.
     *
     * @param id the identifier of the entity to be retrieved
     * @return the retrieved entity
     * @throws EntityNotFoundException if the entity with the specified identifier is not found
     */
    T getInstance(I id) throws EntityNotFoundException;

    /**
     * Creates a new entity.
     *
     * @param object the entity to be created
     * @return the created entity
     * @throws EntityNotFoundException if any related entities are not found
     * @throws IdConflictException if there is an identifier conflict with an existing entity
     */
    T create(T object) throws EntityNotFoundException, IdConflictException;

    /**
     * Updates an existing entity.
     *
     * @param object the entity to be updated
     * @return the updated entity
     * @throws IdConflictException if there is an identifier conflict during the update process
     * @throws EntityNotFoundException if the entity to be updated is not found
     */
    T update(T object) throws IdConflictException, EntityNotFoundException;

    /**
     * Deletes an entity by its identifier.
     *
     * @param id the identifier of the entity to be deleted
     * @return true if the entity was successfully deleted, false otherwise
     * @throws IdConflictException if there is an identifier conflict during the deletion process
     * @throws EntityNotFoundException if the entity to be deleted is not found
     */
    boolean delete(I id) throws IdConflictException, EntityNotFoundException;

    /**
     * Checks if an entity exists by its identifier.
     *
     * @param id the identifier of the entity to check
     * @return true if the entity exists, false otherwise
     */
    boolean exists(I id);
}
