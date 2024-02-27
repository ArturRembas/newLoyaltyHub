package it.unicam.cs.ids.LoyaltyHub.repository;

import it.unicam.cs.ids.LoyaltyHub.model.PlatformAdmin;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for managing CRUD operations for {@link PlatformAdmin} entities.
 * Provides methods to find platform administrators by email and check for their existence based on email.
 */
public interface PlatformAdminRepository extends CrudRepository<PlatformAdmin, Long> {

    /**
     * Checks if a {@link PlatformAdmin} exists by their email.
     *
     * @param email The email address to check for an existing platform administrator.
     * @return {@code true} if a platform admin with the given email exists, {@code false} otherwise.
     */
    boolean existsByEmail(String email);

    /**
     * Finds a {@link PlatformAdmin} by their email.
     *
     * @param email The email address of the platform administrator to find.
     * @return The {@link PlatformAdmin} entity associated with the given email, or {@code null} if none found.
     */
    PlatformAdmin findByEmail(String email);
}
