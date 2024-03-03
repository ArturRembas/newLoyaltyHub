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
public class SimpleRuleController implements RuleController{

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
	public Rule getInstance(@PathVariable Long id) throws EntityNotFoundException {
		return ruleManager.getInstance(id);
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
    @Override
	public boolean delete(@PathVariable Long id) throws IdConflictException, EntityNotFoundException {
		return ruleManager.delete(id);
	}

    /**
     * Checks if a rule exists by its ID.
     * @param id The ID of the rule to check.
     * @return True if the rule exists, false otherwise.
     */
    @GetMapping("/exists/{id}")
    @Override
    public boolean exists(@PathVariable Long id) {
        return ruleManager.exists(id);
    }

    @Override
    public Rule create(Rule object) throws EntityNotFoundException, IdConflictException {
        return ruleManager.create(object);
    }

	@Override
	public Rule update(Rule object) throws IdConflictException, EntityNotFoundException {
		return null;
	}
}
