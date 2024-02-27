package it.unicam.cs.ids.LoyaltyHub.repository;

import it.unicam.cs.ids.LoyaltyHub.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Repository interface for managing {@link User} entities. It provides standard CRUD operations 
 * and additional functionality to find a user by their email address.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Finds a user by their email address.
     *
     * @param email The email address associated with the user.
     * @return An {@link Optional} containing the found {@link User} or an empty {@link Optional} if no user is found.
     */
    Optional<User> findByEmail(String email);
}
