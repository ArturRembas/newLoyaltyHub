package it.unicam.cs.ids.LoyaltyHub.repository;

import it.unicam.cs.ids.LoyaltyHub.model.LoyaltyProgram;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for {@link LoyaltyProgram} entities.
 * This interface extends the {@link CrudRepository} provided by Spring Data, facilitating CRUD operations for {@link LoyaltyProgram} entities.
 * Additional query methods can be defined here if necessary to handle specific queries related to loyalty programs.
 */
public interface LoyaltyProgramRepository extends CrudRepository<LoyaltyProgram, Long> {
    
}
