package it.unicam.cs.ids.LoyaltyHub.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import org.hibernate.Hibernate;

/**
 * Represents a loyalty program in the LoyaltyHub system.
 * This entity manages the details and rules of a loyalty program, including associated fidelity cards and activities.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "loyalty_program")
public class LoyaltyProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loyalty_program_seq")
    @SequenceGenerator(name = "loyalty_program_seq", sequenceName = "loyalty_program_seq", allocationSize = 1)
    @Column(name = "loyalty_program_id", nullable = false)
    private Long loyaltyProgramId;

    private String programName;
    private int multiplier;
    private int thresholdCounter;

    @ManyToOne(optional = false)
    @JoinColumn(name = "activity_admin_id", nullable = false)
    private ActivityAdmin activityAdmin;

    @ManyToMany(mappedBy = "loyaltyPrograms")
    private Set<FidelityCard> fidelityCards = new LinkedHashSet<>();

    @OneToMany(mappedBy = "loyaltyProgram", orphanRemoval = true)
    private Set<Activity> activities = new LinkedHashSet<>();

	public LoyaltyProgram() {
		
	}
    
    /**
     * Creates a new LoyaltyProgram with specified details.
     *
     * @param programName    The name of the loyalty program.
     * @param activityAdmin  The admin responsible for the loyalty program.
     * @param fidelityCards  The set of fidelity cards associated with this loyalty program.
     * @param activities     The set of activities that participate in this loyalty program.
     */
    public LoyaltyProgram(String programName, ActivityAdmin activityAdmin, Set<FidelityCard> fidelityCards, Set<Activity> activities) {
        this.programName = programName;
        this.activityAdmin = activityAdmin;
        this.fidelityCards = fidelityCards;
        this.activities = activities;
    }


    // equals, hashCode and toString methods
    @Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
			return false;
		LoyaltyProgram that = (LoyaltyProgram) o;
		return loyaltyProgramId != null && Objects.equals(loyaltyProgramId, that.loyaltyProgramId);
	}

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "loyaltyProgramId = " + loyaltyProgramId + ", " +
                "programName = " + programName + ", " +
                "multiplier = " + multiplier + ", " +
                "thresholdCounter = " + thresholdCounter + ", " +
                "activityAdmin = " + activityAdmin + ")";
    }
}