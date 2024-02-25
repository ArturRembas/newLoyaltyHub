package it.unicam.cs.ids.LoyaltyHub.model;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a transaction within the LoyaltyHub system.
 * A transaction is associated with a loyalty program and contains information about the transaction amount and validation status.
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_id", nullable = false)
    private Long transactionId;

    /**
     * The price or value associated with this transaction.
     */
    private int price;

    /**
     * Indicates whether this transaction has been validated within the loyalty program.
     */
    private boolean isValidated;

    @ManyToOne(optional = false)
    @JoinColumn(name = "loyalty_program_loyalty_program_id", nullable = false)
    private LoyaltyProgram loyaltyProgram;
}