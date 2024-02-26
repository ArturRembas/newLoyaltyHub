package it.unicam.cs.ids.LoyaltyHub.model.rules;

import it.unicam.cs.ids.LoyaltyHub.model.Transaction;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a basic rule within the loyalty platform where points are awarded
 * based on the transaction amount. The point calculation is based on a fixed
 * rate, defined by the point calculator, which is set to 1 by default.
 */
@Getter
@Setter
@Entity
public class SimpleRulePoint extends Rule {

    private static final int POINT_CALCULATOR = 1; // Fixed point calculation rate

    /**
     * Constructs a new SimpleRulePoint with a default rule name and type.
     */
    public SimpleRulePoint() {
        super("Standard Point Rule", RuleType.RULE_POINT);
    }

    /**
     * Applies the point-based rule to the given transaction by adding points to the
     * fidelity card associated with the transaction. The number of points added is
     * equal to the transaction price multiplied by the point calculator value.
     *
     * @param transaction the transaction to apply the point-based rule to
     */
    @Override
    public void applyRule(Transaction transaction) {
        int pointsToAdd = transaction.getPrice() * POINT_CALCULATOR;
        transaction.getFidelityCard().addPoints(pointsToAdd);
    }
}

