package it.unicam.cs.ids.LoyaltyHub.repository;

import it.unicam.cs.ids.LoyaltyHub.model.FidelityCard;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for {@link FidelityCard} entities.
 * Provides CRUD operations as defined in the {@link CrudRepository}.
 * Additional query methods can be defined here if necessary to handle specific queries on {@link FidelityCard} entities.
 */
public interface FidelityCardRepository extends CrudRepository<FidelityCard, Long> {
    
}
