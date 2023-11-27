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
@Table(name = "fidelity_card")
public class FidelityCard {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fidelity_card_seq")
    @SequenceGenerator(
            name = "fidelity_card_seq",
            sequenceName = "fidelity_card_seq",
            allocationSize = 1)
    @Column(name = "fidelity_card_id", nullable = false)
    private Long fidelityCardId;

    @ManyToMany
    @JoinTable(name = "fidelity_card_loyalty_programs",
            joinColumns = @JoinColumn(name = "fidelity_card_fidelity_card_id"),
            inverseJoinColumns = @JoinColumn(name = "loyalty_programs_loyalty_program_id"))
    private Set<LoyaltyProgram> loyaltyPrograms = new LinkedHashSet<>();

    @OneToOne(mappedBy = "fidelityCard", orphanRemoval = true)
    private CostumerWallet costumerWallet;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FidelityCard that = (FidelityCard) o;
        return fidelityCardId != null && Objects.equals(fidelityCardId, that.fidelityCardId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "fidelityCardId = " + fidelityCardId + ", " +
                "costumerWallet = " + costumerWallet + ")";
    }
}