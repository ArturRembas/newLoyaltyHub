package it.unicam.cs.ids.LoyaltyHub.repository;

import it.unicam.cs.ids.LoyaltyHub.model.ActivityAdmin;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for {@link ActivityAdmin} entities.
 * Extends the {@link CrudRepository} interface provided by Spring Data to include additional methods for retrieving and checking {@link ActivityAdmin} entities.
 */
public interface ActivityAdminRepository extends CrudRepository<ActivityAdmin, Long> {

    /**
     * Retrieves an {@link ActivityAdmin} entity by its email.
     *
     * @param email The email of the {@link ActivityAdmin} to retrieve.
     * @return The {@link ActivityAdmin} entity with the given email or {@code null} if none found.
     */
    ActivityAdmin findByEmail(String email);

    /**
     * Checks whether an {@link ActivityAdmin} entity with the given ID exists.
     *
     * @param activityAdminId The ID of the {@link ActivityAdmin} to check.
     * @return {@code true} if an entity with the given ID exists, {@code false} otherwise.
     */
    boolean existsByActivityAdminId(Long activityAdminId);

    /**
     * Checks whether an {@link ActivityAdmin} entity with the given phone number exists.
     *
     * @param phone The phone number of the {@link ActivityAdmin} to check.
     * @return {@code true} if an entity with the given phone number exists, {@code false} otherwise.
     */
    boolean existsByPhone(String phone);

    /**
     * Checks whether an {@link ActivityAdmin} entity with the given email exists.
     *
     * @param email The email of the {@link ActivityAdmin} to check.
     * @return {@code true} if an entity with the given email exists, {@code false} otherwise.
     */
    boolean existsByEmail(String email);
}
