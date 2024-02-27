package it.unicam.cs.ids.LoyaltyHub.repository;

import it.unicam.cs.ids.LoyaltyHub.model.Costumer;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for handling CRUD operations for the {@link Costumer} entity.
 * It provides methods to retrieve customer data by email and to check the existence of a customer by email.
 */
public interface CostumerRepository extends CrudRepository<Costumer, Long> {

    /**
     * Finds a customer by their email.
     *
     * @param email The email address of the customer.
     * @return The {@link Costumer} entity associated with the given email, or {@code null} if none found.
     */
    Costumer findByEmail(String email);

    /**
     * Checks if a customer exists by their email.
     *
     * @param email The email address to check.
     * @return {@code true} if a customer with the given email exists, {@code false} otherwise.
     */
    boolean existsByEmail(String email);
}
