package it.unicam.cs.ids.LoyaltyHub.service;

import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.model.rules.Rule;
import it.unicam.cs.ids.LoyaltyHub.repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * Service class for managing rule entities within the loyalty platform.
 * Provides functionalities to create, retrieve, and delete rules.
 */
@Validated
@Service
public class SimpleRuleManager implements RuleManager {

    @Autowired
    private RuleRepository ruleRepository;

    /**
     * Retrieves a rule by its ID.
     *
     * @param id The ID of the rule.
     * @return The {@link Rule} object.
     * @throws EntityNotFoundException if the rule is not found.
     */
    @Override
    public Rule getInstance(Long id) throws EntityNotFoundException {
        return ruleRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Rule with ID " + id + " not found."));
    }

    /**
     * Creates a new rule.
     *
     * @param object The {@link Rule} object to create.
     * @return The created {@link Rule} object.
     */
    @Override
    public Rule create(Rule object) {
        return ruleRepository.save(object);
    }

    /**
     * Updates an existing rule. This method is currently not implemented.
     *
     * @param object The {@link Rule} object to update.
     * @return null as this operation is not supported.
     */
    @Override
    public Rule update(Rule object) {
        throw new UnsupportedOperationException("Rule update not supported.");
    }

    /**
     * Deletes a rule by its ID.
     *
     * @param id The ID of the rule to delete.
     * @return true if the deletion was successful, false otherwise.
     * @throws EntityNotFoundException if the rule does not exist.
     */
    @Override
    public boolean delete(Long id) throws EntityNotFoundException {
        if (!ruleRepository.existsById(id)) {
            throw new EntityNotFoundException("Rule with ID " + id + " not found, cannot delete.");
        }
        ruleRepository.deleteById(id);
        return !ruleRepository.existsById(id);
    }

    /**
     * Checks if a rule exists by its ID.
     *
     * @param id The ID of the rule.
     * @return true if the rule exists, false otherwise.
     */
    @Override
    public boolean exists(Long id) {
        return ruleRepository.existsById(id);
    }
}
