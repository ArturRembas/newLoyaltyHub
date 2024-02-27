package it.unicam.cs.ids.LoyaltyHub.repository;

import it.unicam.cs.ids.LoyaltyHub.model.Costumer;
import it.unicam.cs.ids.LoyaltyHub.model.LoyaltyProgram;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository interface for managing {@link LoyaltyProgram} entities. Provides methods to perform CRUD operations and custom queries such as finding programs by customer and checking existence by program name.
 */
public interface LoyaltyProgramRepository extends CrudRepository<LoyaltyProgram, Long> {

    /**
     * Finds a list of {@link LoyaltyProgram} entities associated with a given customer.
     *
     * @param costumer The {@link Costumer} entity to find associated loyalty programs for.
     * @return A list of loyalty programs associated with the specified customer.
     */
    List<LoyaltyProgram> findByFidelityCards_Costumer(Costumer costumer);

    /**
     * Checks if a {@link LoyaltyProgram} exists by its program name.
     *
     * @param programName The name of the loyalty program to check.
     * @return {@code true} if a loyalty program with the given name exists, {@code false} otherwise.
     */
    boolean existsByProgramName(String programName);

    /**
     * Finds a {@link LoyaltyProgram} entity by its program name.
     *
     * @param programName The name of the loyalty program.
     * @return The loyalty program with the specified name, or {@code null} if not found.
     */
    LoyaltyProgram findByProgramName(String programName);
}
