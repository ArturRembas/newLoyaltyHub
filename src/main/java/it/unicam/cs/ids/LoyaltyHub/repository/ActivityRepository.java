package it.unicam.cs.ids.LoyaltyHub.repository;

import it.unicam.cs.ids.LoyaltyHub.model.Activity;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for {@link Activity} entities.
 * Extends the {@link CrudRepository} interface provided by Spring Data to include additional methods
 * for retrieving and checking {@link Activity} entities based on their attributes.
 */
public interface ActivityRepository extends CrudRepository<Activity, Long> {

    /**
     * Checks whether an {@link Activity} entity with the given ID exists.
     *
     * @param activityId The ID of the {@link Activity} to check.
     * @return {@code true} if an entity with the given ID exists, {@code false} otherwise.
     */
    boolean existsByActivityId(Long activityId);

    /**
     * Deletes an {@link Activity} entity with the specified ID.
     *
     * @param activityId The ID of the {@link Activity} to be deleted.
     */
    void deleteByActivityId(Long activityId);

    /**
     * Checks whether an {@link Activity} entity with the given VAT code exists.
     *
     * @param vatCode The VAT code of the {@link Activity} to check.
     * @return {@code true} if an entity with the given VAT code exists, {@code false} otherwise.
     */
    boolean existsByVatCode(String vatCode);

    /**
     * Checks whether an {@link Activity} entity with the given phone number exists.
     *
     * @param phone The phone number of the {@link Activity} to check.
     * @return {@code true} if an entity with the given phone number exists, {@code false} otherwise.
     */
    boolean existsByPhone(String phone);

    /**
     * Checks whether an {@link Activity} entity with the given email exists.
     *
     * @param email The email of the {@link Activity} to check.
     * @return {@code true} if an entity with the given email exists, {@code false} otherwise.
     */
    boolean existsByEmail(String email);
}
