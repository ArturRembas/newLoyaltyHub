package it.unicam.cs.ids.LoyaltyHub.model.rules;

/**
 * Represents the types of rules that can be implemented within the loyalty platform.
 * Each enum constant defines a distinct category of rule that can be applied to transactions
 * or loyalty program behaviors.
 */
public enum RuleType {
    /**
     * Represents a rule based on accumulating points through transactions.
     */
    RULE_POINT,

    /**
     * Represents a rule that involves levels or tiers within the loyalty program,
     * where higher levels offer greater rewards.
     */
    RULE_LEVEL,

    /**
     * Represents a cashback rule where a percentage of the transaction amount
     * is returned to the customer's loyalty account.
     */
    RULE_CASHBACK
}

