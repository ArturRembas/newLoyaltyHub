package it.unicam.cs.ids.LoyaltyHub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a transaction within the loyalty platform, linking activities,
 * fidelity cards, and their corresponding prices and validation statuses.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactionGenerator")
    @SequenceGenerator(name = "transactionGenerator", allocationSize = 1)
    @Column(name = "transaction_id", nullable = false)
    private Long transactionId;

    private int price;
    private boolean validated = false;

    @ManyToOne
    @JoinColumn(name = "fidelity_card_card_id")
    @JsonIgnore
    private FidelityCard fidelityCard;

    @OneToOne(orphanRemoval = true)
    @JsonIgnore
    @JoinColumn(name = "activity_user_id")
    private Activity activity;

    /**
     * Constructs a new Transaction with the specified price, fidelity card, and activity.
     *
     * @param price         the price associated with the transaction
     * @param fidelityCard  the fidelity card involved in the transaction
     * @param activity      the activity linked to the transaction
     */
    public Transaction(int price, FidelityCard fidelityCard, Activity activity) {
        this.price = price;
        this.fidelityCard = fidelityCard;
        this.activity = activity;
    }

    /**
     * Marks this transaction as validated.
     */
    public void validate() {
        this.validated = true;
    }
}
