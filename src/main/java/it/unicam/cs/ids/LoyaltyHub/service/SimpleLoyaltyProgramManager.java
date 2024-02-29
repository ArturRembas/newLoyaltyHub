package it.unicam.cs.ids.LoyaltyHub.service;

import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.model.LoyaltyProgram;
import it.unicam.cs.ids.LoyaltyHub.repository.LoyaltyProgramRepository;
import it.unicam.cs.ids.LoyaltyHub.repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * Service class for managing loyalty program entities within the loyalty platform.
 * Provides functionalities to create, retrieve, update with rules, and delete loyalty programs.
 */
@Validated
@Service
public class SimpleLoyaltyProgramManager implements LoyaltyProgramManager {

    @Autowired
    private LoyaltyProgramRepository loyaltyProgramRepository;

    @Autowired
    private RuleRepository ruleRepository;

    /**
     * Retrieves a loyalty program by its ID.
     *
     * @param id The ID of the loyalty program.
     * @return The {@link LoyaltyProgram} object.
     * @throws EntityNotFoundException if the loyalty program is not found.
     */
    @Override
    public LoyaltyProgram getInstance(Long id) throws EntityNotFoundException {
        return loyaltyProgramRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Loyalty program with ID " + id + " not found."));
    }

    /**
     * Creates a new loyalty program.
     *
     * @param object The {@link LoyaltyProgram} object to create.
     * @return The created {@link LoyaltyProgram} object.
     */
    @Override
    public LoyaltyProgram create(LoyaltyProgram object) {
        return loyaltyProgramRepository.save(object);
    }

    /**
     * Updates an existing loyalty program. This method is currently not implemented.
     *
     * @param object The {@link LoyaltyProgram} object to update.
     * @return null as this operation is not supported.
     */
    @Override
    public LoyaltyProgram update(LoyaltyProgram object) {
        throw new UnsupportedOperationException("Loyalty program update not supported.");
    }

    /**
     * Deletes a loyalty program by its ID.
     *
     * @param id The ID of the loyalty program to delete.
     * @return true if the deletion was successful, false otherwise.
     * @throws EntityNotFoundException if the loyalty program does not exist.
     */
    @Override
    public boolean delete(Long id) throws EntityNotFoundException {
        if (!loyaltyProgramRepository.existsById(id)) {
            throw new EntityNotFoundException("Loyalty program with ID " + id + " not found, cannot delete.");
        }
        loyaltyProgramRepository.deleteById(id);
        return !loyaltyProgramRepository.existsById(id);
    }

    /**
     * Checks if a loyalty program exists by its ID.
     *
     * @param id The ID of the loyalty program.
     * @return true if the loyalty program exists, false otherwise.
     */
    @Override
    public boolean exists(Long id) {
        return loyaltyProgramRepository.existsById(id);
    }

    /**
     * Updates a loyalty program with a specified rule by their names.
     *
     * @param programName The name of the loyalty program to update.
     * @param ruleName The name of the rule to associate with the loyalty program.
     * @return The updated {@link LoyaltyProgram} with the newly associated rule.
     * @throws EntityNotFoundException if the loyalty program or rule is not found.
     */
    @Override
    public LoyaltyProgram updateWithRule(String programName, String ruleName) throws EntityNotFoundException {
        LoyaltyProgram loyaltyProgram = loyaltyProgramRepository.findByProgramName(programName);
        if (loyaltyProgram == null) {
            throw new EntityNotFoundException("Loyalty program with name " + programName + " not found.");
        }

        var rule = ruleRepository.findByRuleName(ruleName);
        if (rule == null) {
            throw new EntityNotFoundException("Rule with name " + ruleName + " not found.");
        }

        loyaltyProgram.addRule(rule);
        return loyaltyProgramRepository.save(loyaltyProgram);
    }
}
