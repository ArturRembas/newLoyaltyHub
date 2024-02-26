package it.unicam.cs.ids.LoyaltyHub.model.rules;

import it.unicam.cs.ids.LoyaltyHub.model.Transaction;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.HashMap;

/**
 * Represents a level-based rule within the loyalty platform, defining thresholds for different levels
 * based on the points accumulated through transactions.
 */
@Entity
@Getter
@Setter
public class SimpleRuleLevel extends SimpleRulePoint {
    private static final int LEVELS = 3; // Since levels are fixed, this can be a constant

    @Transient
    private final Map<String, Integer> thresholds;

    /**
     * Constructs a new SimpleRuleLevel with predefined thresholds for each level.
     */
    public SimpleRuleLevel() {
        this.setType(RuleType.RULE_LEVEL);
        this.setRuleName("Standard Level Rule");
        thresholds = new HashMap<>();
        initializeThresholds();
    }

    private void initializeThresholds() {
        thresholds.put("Base", 100);
        thresholds.put("Intermedio", 300);
        thresholds.put("Avanzato", 500);
    }

    /**
     * Applies the level rule to the specified transaction by updating the level of the associated
     * fidelity card based on the total points accumulated.
     *
     * @param transaction the transaction to apply the level rule to
     */
    @Override
    public void applyRule(Transaction transaction) {
        super.applyRule(transaction);
        updateFidelityCardLevel(transaction);
    }

    private void updateFidelityCardLevel(Transaction transaction) {
        int totalPoints = transaction.getFidelityCard().getTotalPoints();
        if (totalPoints <= thresholds.get("Base")) {
            transaction.getFidelityCard().setLevel("Base");
        } else if (totalPoints <= thresholds.get("Intermedio")) {
            transaction.getFidelityCard().setLevel("Intermedio");
        } else {
            transaction.getFidelityCard().setLevel("Avanzato");
        }
    }
}
