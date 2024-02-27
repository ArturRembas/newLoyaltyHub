package it.unicam.cs.ids.LoyaltyHub.controller;

import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.exception.IdConflictException;
import it.unicam.cs.ids.LoyaltyHub.model.rules.*;
import it.unicam.cs.ids.LoyaltyHub.service.RuleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing loyalty program rules.
 * Provides endpoints for rule creation, retrieval, and deletion.
 */
@RestController
@RequestMapping("/api/Rule")
public class SimpleRuleController {

    private final RuleManager ruleManager;

    @Autowired
    public SimpleRuleController(RuleManager ruleManager) {
        this.ruleManager = ruleManager;
    }

    /**
     * Retrieves a rule by its ID.
     * @param id The ID of the rule to retrieve.
     * @return The requested rule.
     * @throws EntityNotFoundException if the rule is not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Rule> getInstance(@PathVariable Long id) throws EntityNotFoundException {
        Rule rule = ruleManager.getInstance(id);
        return ResponseEntity.ok(rule);
    }

    /**
     * Creates a new simple point-based rule.
     * @return The created simple point-based rule.
     * @throws EntityNotFoundException, IdConflictException if the creation fails.
     */
    @PostMapping("/createNewRulePoint")
    public ResponseEntity<Rule> createNewRulePoint() throws EntityNotFoundException, IdConflictException {
        Rule rule = ruleManager.create(new SimpleRulePoint());
        return ResponseEntity.ok(rule);
    }

    /**
     * Creates a new level-based rule.
     * @return The created level-based rule.
     * @throws EntityNotFoundException, IdConflictException if the creation fails.
     */
    @PostMapping("/createNewLevelRule")
    public ResponseEntity<Rule> createNewLevelRule() throws EntityNotFoundException, IdConflictException {
        Rule rule = ruleManager.create(new SimpleRuleLevel());
        return ResponseEntity.ok(rule);
    }

    /**
     * Creates a new personalized cashback rule with specified value.
     * @param type The type of rule, must be "cashback".
     * @param value The cashback value for the rule.
     * @return The created personalized cashback rule.
     * @throws IdConflictException, EntityNotFoundException if the creation fails or type is invalid.
     */
    @PostMapping("/createNewPersonalizedRule/{type}/{value}")
    public ResponseEntity<Rule> createNewPersonalizedRule(@PathVariable String type, @PathVariable int value) throws IdConflictException, EntityNotFoundException {
        if (!"cashback".equals(type)) throw new IllegalArgumentException("Tipologia non valida");
        Rule rule = ruleManager.create(new PersonalizedCashbackRule(value));
        return ResponseEntity.ok(rule);
    }

    /**
     * Deletes a rule by its ID.
     * @param id The ID of the rule to be deleted.
     * @return True if the deletion was successful, false otherwise.
     * @throws IdConflictException, EntityNotFoundException if the deletion fails.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) throws IdConflictException, EntityNotFoundException {
        boolean deleted = ruleManager.delete(id);
        return ResponseEntity.ok(deleted);
    }

    /**
     * Checks if a rule exists by its ID.
     * @param id The ID of the rule to check.
     * @return True if the rule exists, false otherwise.
     */
    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> exists(@PathVariable Long id) {
        boolean exists = ruleManager.exists(id);
        return ResponseEntity.ok(exists);
    }
}
