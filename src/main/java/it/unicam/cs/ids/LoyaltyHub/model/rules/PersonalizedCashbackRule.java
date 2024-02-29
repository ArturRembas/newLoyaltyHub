package it.unicam.cs.ids.LoyaltyHub.model.rules;

import it.unicam.cs.ids.LoyaltyHub.model.Transaction;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a personalized cashback rule extending a simple point-based rule.
 * This rule allows for a customizable discount percentage to be applied as cashback
 * to the fidelity card associated with a transaction.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class PersonalizedCashbackRule extends SimpleRulePoint {

    /**
     * The discount percentage to apply as cashback.
     */
    private int discount;

    /**
     * Constructs a new PersonalizedCashbackRule with the specified discount percentage.
     * Sets the rule type to cashback and assigns a default rule name.
     *
     * @param discount the cashback discount percentage to be applied
     */
    public PersonalizedCashbackRule(int discount) {
        this.setType(RuleType.RULE_CASHBACK);
        this.setRuleName("Personalized Cashback Rule");
        this.discount = discount;
    }

    /**
     * Sets the rule type.
     *
     * @param ruleType The type of the rule.
     */
    private void setType(RuleType ruleType) {
        this.type = ruleType;
    }

    /**
     * Sets the name of the rule.
     *
     * @param ruleName The name of the rule.
     */
    private void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

	/**
     * Applies the cashback rule to the specified transaction by calculating the cashback
     * amount based on the transaction price and the set discount percentage. The cashback
     * is then added to the fidelity card associated with the transaction.
     *
     * @param transaction the transaction to apply the cashback rule to
     */
    @Override
    public void applyRule(Transaction transaction) {
        double cashbackAmount = transaction.getPrice() * (double) discount / 100;
        transaction.getFidelityCard().addCashback(Math.round(cashbackAmount));
    }
}

