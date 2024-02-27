package it.unicam.cs.ids.LoyaltyHub.repository;

import it.unicam.cs.ids.LoyaltyHub.model.CostumerJoinRequest;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for handling persistence operations for {@link CostumerJoinRequest} entities.
 * Extends {@link CrudRepository} to provide standard CRUD functionalities.
 */
public interface CostumerJoinRequestRepository extends CrudRepository<CostumerJoinRequest, Long> {

    /**
     * Checks if a {@link CostumerJoinRequest} exists by the given phone number.
     * 
     * @param phone The phone number to check for existing join requests.
     * @return {@code true} if a join request with the given phone number exists, {@code false} otherwise.
     */
    boolean existsByPhone(String phone);

    /**
     * Checks if a {@link CostumerJoinRequest} exists by the given customer email.
     * 
     * @param costumerEmail The email of the customer to check for existing join requests.
     * @return {@code true} if a join request with the given email exists, {@code false} otherwise.
     */
    boolean existsByCostumerEmail(String costumerEmail);
}
