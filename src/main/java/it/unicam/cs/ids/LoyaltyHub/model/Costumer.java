package it.unicam.cs.ids.LoyaltyHub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a customer in the loyalty platform, extending the User entity
 * with a unique fidelity card for tracking loyalty program participation.
 */
@Entity
@NoArgsConstructor
@Setter
@Getter
public class Costumer extends User {

    private String surname;

    /**
     * The fidelity card associated with this customer, indicating participation in the loyalty program.
     * The JsonIgnore annotation prevents the fidelity card from being serialized in JSON responses.
     */
    @OneToOne(mappedBy = "costumer", orphanRemoval = true)
    @JsonIgnore
    private FidelityCard fidelityCard;

    /**
     * Constructs a new Costumer with specified personal and contact details.
     *
     * @param name    The first name of the customer.
     * @param surname The surname of the customer.
     * @param email   The email address of the customer.
     * @param phone   The contact phone number of the customer.
     */
    public Costumer(String name, String surname, String address, String email, String phone) {
        super(name, surname, email, phone);
    }

    /**
     * Associates a fidelity card with this customer for loyalty program tracking.
     *
     * @param card The FidelityCard to be associated with this customer.
     */
    public void addCard(FidelityCard card) {
        this.fidelityCard = card;
    }

    /**
     * Associates a loyalty program with the customer's fidelity card.
     *
     * @param program The LoyaltyProgram to be associated with this customer's fidelity card.
     */
    public void setLoyaltyProgram(LoyaltyProgram program) {
        if (this.fidelityCard != null) {
            this.fidelityCard.addLoyaltyProgram(program);
        } else {
            FidelityCard newCard = new FidelityCard(this);
            newCard.addLoyaltyProgram(program);
            this.addCard(newCard);
        }
    }

    /**
     * Retrieves the fidelity card associated with this customer.
     *
     * @return The fidelity card associated with the customer.
     */
    public FidelityCard getFidelityCard() {
        return this.fidelityCard;
    }
}