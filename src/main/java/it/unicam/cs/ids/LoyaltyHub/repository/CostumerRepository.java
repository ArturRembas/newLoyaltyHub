package it.unicam.cs.ids.LoyaltyHub.repository;

import it.unicam.cs.ids.LoyaltyHub.model.Costumer;
import org.springframework.data.repository.CrudRepository;
import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;

/**
 * Repository interface for {@link Costumer} entities.
 * Provides CRUD operations and additional methods to retrieve and verify {@link Costumer} entities based on various attributes.
 */
public interface CostumerRepository extends CrudRepository<Costumer, Long> {

    /**
     * Finds a {@link Costumer} by their email.
     * 
     * @param email The email of the {@link Costumer} to find.
     * @return The found {@link Costumer}.
     * @throws EntityNotFoundException If no {@link Costumer} with the given email is found.
     */
    Costumer findByEmail(String email) throws EntityNotFoundException;

    /**
     * Checks if a {@link Costumer} with the given ID exists.
     * 
     * @param costumerId The ID of the {@link Costumer} to check.
     * @return {@code true} if a {@link Costumer} with the given ID exists, {@code false} otherwise.
     */
    boolean existsByCostumerId(Long costumerId);

    /**
     * Finds a {@link Costumer} by their ID.
     * 
     * @param costumerId The ID of the {@link Costumer} to find.
     * @return The found {@link Costumer}.
     */
    Costumer findByCostumerId(Long costumerId);

    /**
     * Checks if a {@link Costumer} with the given email exists.
     * 
     * @param email The email of the {@link Costumer} to check.
     * @return {@code true} if a {@link Costumer} with the given email exists, {@code false} otherwise.
     */
    boolean existsByEmail(String email);

    /**
     * Checks if a {@link Costumer} with the given phone number exists.
     * 
     * @param phone The phone number of the {@link Costumer} to check.
     * @return {@code true} if a {@link Costumer} with the given phone number exists, {@code false} otherwise.
     */
    boolean existsByPhone(String phone);
}
