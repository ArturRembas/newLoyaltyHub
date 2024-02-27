package it.unicam.cs.ids.LoyaltyHub.repository;

import it.unicam.cs.ids.LoyaltyHub.model.rules.Rule;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository interface for managing {@link Rule} entities. Provides methods to perform CRUD operations
 * and custom queries such as finding rules by loyalty program names and IDs, and finding a rule by its name.
 */
public interface RuleRepository extends CrudRepository<Rule, Long> {

    /**
     * Finds a list of {@link Rule} entities associated with a given loyalty program name.
     *
     * @param programName The name of the loyalty program.
     * @return A list of rules associated with the specified loyalty program name.
     */
    List<Rule> findByLoyaltyPrograms_ProgramName(String programName);

    /**
     * Finds a list of {@link Rule} entities associated with a given loyalty program ID using a custom query.
     *
     * @param loyaltyProgramId The ID of the loyalty program.
     * @return A list of rules associated with the specified loyalty program ID.
     */
    @Query("SELECT r FROM Rule r INNER JOIN r.loyaltyPrograms loyaltyPrograms WHERE loyaltyPrograms.loyaltyProgramId = ?1")
    List<Rule> findByLoyaltyPrograms_LoyaltyProgramId(Long loyaltyProgramId);

    /**
     * Finds a {@link Rule} entity by its rule name.
     *
     * @param ruleName The name of the rule.
     * @return The rule with the specified name, or {@code null} if not found.
     */
    Rule findByRuleName(String ruleName);
}
