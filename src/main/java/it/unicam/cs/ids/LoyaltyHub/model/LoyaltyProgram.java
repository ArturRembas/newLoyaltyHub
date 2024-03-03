package it.unicam.cs.ids.LoyaltyHub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.unicam.cs.ids.LoyaltyHub.model.rules.Rule;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * Represents a loyalty program in the loyalty platform system. It is identified by a unique ID
 * and contains information about the program name, associated fidelity cards, enrolled activities,
 * and applicable rules.
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
public class LoyaltyProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loyaltyProgramGenerator")
    @SequenceGenerator(name = "loyaltyProgramGenerator", allocationSize = 1)
    @Column(name = "loyalty_program_id", nullable = false)
    private Long loyaltyProgramId;

    private String programName;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "loyalty_program_fidelity_cards",
            joinColumns = @JoinColumn(name = "loyalty_program_loyalty_program_id"),
            inverseJoinColumns = @JoinColumn(name = "fidelity_cards_card_id"))
    private Set<FidelityCard> fidelityCards = new LinkedHashSet<>();

    @OneToMany(mappedBy = "loyaltyProgram", orphanRemoval = true)
    @JsonIgnore
    private Set<Activity> enrolledActivities = new LinkedHashSet<>();

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "loyalty_program_rules",
            joinColumns = @JoinColumn(name = "loyalty_program_loyalty_program_id"),
            inverseJoinColumns = @JoinColumn(name = "rules_rule_id"))
    private Set<Rule> rules = new LinkedHashSet<>();

    /**
     * Constructs a new LoyaltyProgram with the specified program name.
     *
     * @param programName The name of the loyalty program.
     */
    public LoyaltyProgram(String programName) {
        this.programName = programName;
    }

    /**
     * Constructs a new LoyaltyProgram with the specified program name and a single rule.
     *
     * @param programName The name of the loyalty program.
     * @param rule        A rule to be associated with the loyalty program.
     */
    public LoyaltyProgram(String programName, Rule rule) {
        this.programName = programName;
        this.rules.add(rule);
    }

    /**
     * Adds a rule to the loyalty program.
     *
     * @param rule The rule to be added.
     * @return true if the rule was added successfully, false otherwise.
     */
    public boolean addRule(Rule rule) {
        rule.addLoyaltyProgram(this);
        return rules.add(rule);
    }

    /**
     * Enrolls an activity in the loyalty program.
     *
     * @param optional The activity to be enrolled.
     */
    public void enrollActivity(Activity optional) {
        this.enrolledActivities.add(optional);
        optional.setLoyaltyProgram(this);
        optional.setProgramName(this.programName);
    }

    /**
     * Unenrolls an activity from the loyalty program.
     *
     * @param activity The activity to be unenrolled.
     */
    public void unEnrolledActivity(Activity activity) {
        this.enrolledActivities.remove(activity);
        activity.setLoyaltyProgram(null);
        activity.setProgramName(null);
    }

    /**
     * Enrolls a customer in the loyalty program by adding their fidelity card to the program.
     *
     * @param costumer The customer to be enrolled.
     */
    public void enrollCostumer(Costumer costumer) {
        this.fidelityCards.add(costumer.getFidelityCard());
        costumer.getFidelityCard().addLoyaltyProgram(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        LoyaltyProgram that = (LoyaltyProgram) o;
        return loyaltyProgramId != null && Objects.equals(loyaltyProgramId, that.loyaltyProgramId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    /**
     * Retrieves the name of the loyalty program.
     *
     * @return The name of the loyalty program.
     */
    public String getProgramName() {
        return this.programName;
    }

    /**
     * Retrieves the ID of the loyalty program.
     *
     * @return The ID of the loyalty program.
     */
	public Long getLoyaltyProgramId() {
		return this.loyaltyProgramId;
	}

	/**
     * Retrieves the enrolled activities of the loyalty program.
     *
     * @return The enrolled activities of the loyalty program.
     */
	public Set<Activity> getEnrolledActivities() {
		return this.enrolledActivities;
	}

	/**
     * Retrieves the fidelity cards of the loyalty program.
     *
     * @return The fidelity cards of the loyalty program.
     */
	public Iterable<FidelityCard> getFidelityCards() {
		return this.fidelityCards;
	}
	
	/**
     * Retrieves the rules of the loyalty program.
     *
     * @return The rules of the loyalty program.
     */
	public Set<Rule> getRules() {
		return this.rules;
	}


}
