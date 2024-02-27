package it.unicam.cs.ids.LoyaltyHub.repository;

import it.unicam.cs.ids.LoyaltyHub.model.ActivityJoinRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for handling persistence operations for {@link ActivityJoinRequest} entities.
 * Extends {@link CrudRepository} to provide CRUD functionalities out-of-the-box.
 */
public interface ActivityJoinRequestRepository extends CrudRepository<ActivityJoinRequest, Long> {

    /**
     * Checks if an {@link ActivityJoinRequest} exists by the provided activity email.
     * 
     * @param activityEmail The email associated with the activity to check for existing join requests.
     * @return {@code true} if an activity join request exists with the given email, {@code false} otherwise.
     */
    @Query("SELECT (COUNT(a) > 0) FROM ActivityJoinRequest a WHERE a.activityEmail = ?1")
    boolean existsByEmail(String activityEmail);
}

