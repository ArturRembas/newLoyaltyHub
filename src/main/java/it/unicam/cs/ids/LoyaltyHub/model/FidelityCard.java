package it.unicam.cs.ids.LoyaltyHub.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a fidelity card associated with a customer. 
 * This card tracks the customer's total points, cashback, and associated loyalty programs and transactions.
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
public class FidelityCard {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fidelityCardGenerator")
    @SequenceGenerator(name = "fidelityCardGenerator", allocationSize = 1)
    @Column(name = "card_id", nullable = false)
    private Long cardId;

    private int totalPoints = 0;
    private String level;
    private double cashback = 0.0;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "costumer_user_id", unique = true)
    private Costumer costumer;

    @ManyToMany(mappedBy = "fidelityCards")
    private Set<LoyaltyProgram> loyaltyPrograms = new HashSet<>();

    @OneToMany(mappedBy = "fidelityCard", orphanRemoval = true)
    private Set<Transaction> transactions = new HashSet<>();

    /**
     * Constructs a new FidelityCard for a specified customer.
     *
     * @param costumer the customer to whom this card is associated.
     */
    public FidelityCard(Costumer costumer) {
        this.costumer = costumer;
    }

    /**
     * Adds points to the total points on the card.
     *
     * @param value the number of points to add.
     */
    public void addPoints(int value) {
        this.totalPoints += value;
    }

    /**
     * Removes points from the total points on the card.
     *
     * @param value the number of points to remove.
     * @return the new total points after removal.
     */
    public int removePoints(int value) {
        this.totalPoints -= value;
        return this.totalPoints;
    }

    /**
     * Associates a loyalty program with this card.
     *
     * @param loyaltyProgram the loyalty program to associate.
     */
    public void addLoyaltyProgram(LoyaltyProgram loyaltyProgram) {
        this.loyaltyPrograms.add(loyaltyProgram);
    }

    /**
     * Adds a cashback value to the card.
     *
     * @param value the cashback value to add.
     */
    public void addCashback(double value) {
        this.cashback += value;
    }
}
