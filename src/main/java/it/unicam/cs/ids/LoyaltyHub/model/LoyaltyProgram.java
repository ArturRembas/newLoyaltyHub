package it.unicam.cs.ids.LoyaltyHub.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "loyalty_program")
public class LoyaltyProgram {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "loyalty_program_seq")

    @SequenceGenerator(
            name = "loyalty_program_seq",
            sequenceName = "loyalty_program_seq",
            allocationSize = 1)
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


    public LoyaltyProgram(String programName, ActivityAdmin activityAdmin, Set<FidelityCard> fidelityCards, Set<Activity> activities) {
        this.programName = programName;
        this.activityAdmin = activityAdmin;
        this.fidelityCards = fidelityCards;
        this.activities = activities;
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