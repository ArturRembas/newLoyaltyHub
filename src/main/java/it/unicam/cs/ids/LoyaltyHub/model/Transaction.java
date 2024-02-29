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

    /**
     * Checks if the transaction is validated.
     *
     * @return true if the transaction is validated, false otherwise.
     */
    public boolean isValidated() {
        return this.validated;
    }

    /**
     * Retrieves the activity associated with this transaction.
     *
     * @return The {@link Activity} object linked to this transaction.
     */
    public Activity getActivity() {
        return this.activity;
    }

    /**
     * Retrieves the fidelity card associated with this transaction.
     *
     * @return The {@link FidelityCard} object linked to this transaction.
     */
    public FidelityCard getFidelityCard() {
        return this.fidelityCard;
    }

    /**
     * Updates the validation status of this transaction.
     *
     * @param validated The new validation status to set.
     */
    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    /**
     * Sets the fidelity card associated with this transaction.
     *
     * @param fidelityCard The {@link FidelityCard} object to associate with this transaction.
     */
    public void setFidelityCard(FidelityCard fidelityCard) {
        this.fidelityCard = fidelityCard;
    }

    /**
     * Sets the activity associated with this transaction.
     *
     * @param activity The {@link Activity} object to associate with this transaction.
     */
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    /**
     * Retrieves the price associated with this transaction.
     *
     * @return The price linked to this transaction.
     */
	public int getPrice() {
		return this.price;
	}

	/**
     * Retrieves the ID of fidelity card.
     *
     * @return The the ID of fidelity card.
     */
	public Long getFidelityCardId() {
        return this.fidelityCard != null ? this.fidelityCard.getCardId() : null;
    }

	/**
     * Retrieves the ID of Activity.
     *
     * @return The the ID of Activity.
     */
    public Long getActivityId() {
        return this.activity != null ? this.activity.getId() : null;
    }

}
