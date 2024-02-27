package it.unicam.cs.ids.LoyaltyHub.repository;

import it.unicam.cs.ids.LoyaltyHub.model.Activity;
import it.unicam.cs.ids.LoyaltyHub.model.LoyaltyProgram;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository interface for handling persistence operations for {@link Activity} entities.
 * Extends {@link CrudRepository} to provide standard CRUD functionalities.
 */
public interface ActivityRepository extends CrudRepository<Activity, Long> {

    /**
     * Updates the {@link LoyaltyProgram} associated with all {@link Activity} entities.
     *
     * @param loyaltyProgram The new loyalty program to associate with activities.
     * @return The number of entities updated.
     */
    @Transactional
    @Modifying
    @Query("UPDATE Activity a SET a.loyaltyProgram = ?1")
    int updateLoyaltyProgramBy(LoyaltyProgram loyaltyProgram);

    /**
     * Retrieves an {@link Activity} entity based on its email.
     *
     * @param email The email associated with the activity.
     * @return The {@link Activity} entity if found, or {@code null} otherwise.
     */
    Activity findByEmail(String email);

    /**
     * Checks if an {@link Activity} entity exists with the given email.
     *
     * @param email The email to check for existence.
     * @return {@code true} if an entity with the given email exists, {@code false} otherwise.
     */
    boolean existsByEmail(String email);

    /**
     * Finds an {@link Activity} entity based on its ID.
     *
     * @param activityId The ID of the activity.
     * @return The {@link Activity} entity if found, or {@code null} otherwise.
     */
    Activity findById(long activityId);
}
