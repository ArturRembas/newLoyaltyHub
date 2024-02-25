package it.unicam.cs.ids.LoyaltyHub.repository;

import it.unicam.cs.ids.LoyaltyHub.model.CostumerWallet;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for {@link CostumerWallet} entities.
 * Extends {@link CrudRepository} provided by Spring Data to include CRUD operations and additional methods
 * for retrieving {@link CostumerWallet} entities based on specific attributes.
 */
public interface CostumerWalletRepository extends CrudRepository<CostumerWallet, Long> {

    /**
     * Finds a {@link CostumerWallet} by its wallet ID.
     *
     * @param walletId The ID of the {@link CostumerWallet} to find.
     * @return The found {@link CostumerWallet}, or {@code null} if no {@link CostumerWallet} with the given ID is found.
     */
    CostumerWallet findByWalletId(Long walletId);
}
