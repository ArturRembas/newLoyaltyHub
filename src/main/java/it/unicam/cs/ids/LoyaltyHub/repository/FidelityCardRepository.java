package it.unicam.cs.ids.LoyaltyHub.repository;

import it.unicam.cs.ids.LoyaltyHub.model.FidelityCard;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Repository interface for managing {@link FidelityCard} entities. Provides methods to perform CRUD operations and custom queries such as updating total points and finding cards by loyalty program name.
 */
public interface FidelityCardRepository extends CrudRepository<FidelityCard, Long> {

    /**
     * Finds a list of {@link FidelityCard} entities associated with a given loyalty program name.
     *
     * @param programName The name of the loyalty program.
     * @return A list of fidelity cards associated with the specified loyalty program name.
     */
    List<FidelityCard> findByLoyaltyPrograms_ProgramName(String programName);

    /**
     * Updates the total points of fidelity cards where the current total points match a specified value.
     *
     * @param newTotalPoints The new total points to be set.
     * @param existingTotalPoints The existing total points to match for the update.
     * @return The number of fidelity cards updated.
     */
    @Transactional
    @Modifying
    @Query("UPDATE FidelityCard f SET f.totalPoints = ?1 WHERE f.totalPoints = ?2")
    int updateTotalPointsByTotalPoints(int newTotalPoints, int existingTotalPoints);

    /**
     * Finds a {@link FidelityCard} entity by its ID.
     *
     * @param fidelityCardId The ID of the fidelity card.
     * @return The fidelity card with the specified ID, or {@code null} if not found.
     */
    FidelityCard findById(long fidelityCardId);
}
