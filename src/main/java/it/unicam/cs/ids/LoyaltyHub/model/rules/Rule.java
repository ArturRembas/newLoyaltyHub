package it.unicam.cs.ids.LoyaltyHub.model.rules;

import it.unicam.cs.ids.LoyaltyHub.model.LoyaltyProgram;
import it.unicam.cs.ids.LoyaltyHub.model.Transaction;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;
import java.util.HashSet;

/**
 * Represents a generic rule within the loyalty platform system. This abstract class
 * provides the framework for defining rules associated with loyalty programs and
 * their application to transactions.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public abstract class Rule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rule_id", nullable = false)
    private Long ruleId;

    private String ruleName;
    private RuleType type;

    @ManyToMany(mappedBy = "rules")
    private Set<LoyaltyProgram> loyaltyPrograms = new HashSet<>();

    /**
     * Constructs a new Rule with the specified name and type.
     *
     * @param ruleName the name of the rule
     * @param type     the type of the rule, as defined in RuleType enum
     */
    public Rule(String ruleName, RuleType type) {
        this.ruleName = ruleName;
        this.type = type;
    }

    /**
     * Abstract method to apply the rule logic to a given transaction. This method
     * must be implemented by subclasses to define specific rule behavior.
     *
     * @param transaction the transaction to which the rule is applied
     */
    public abstract void applyRule(Transaction transaction);

    /**
     * Associates a loyalty program with this rule.
     *
     * @param program the loyalty program to be associated with the rule
     */
    public void addLoyaltyProgram(LoyaltyProgram program) {
        this.loyaltyPrograms.add(program);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rule)) return false;
        Rule rule = (Rule) o;
        return Objects.equals(ruleId, rule.ruleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ruleId);
    }
}
