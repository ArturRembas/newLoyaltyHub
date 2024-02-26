package it.unicam.cs.ids.LoyaltyHub.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Represents an activity within the loyalty platform, extending the User entity
 * to include business-specific attributes like VAT code and association with a loyalty program.
 */
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Activity extends User {

    /**
     * VAT code unique to the activity, used for business identification.
     */
    private String vatCode;

    /**
     * The loyalty program associated with this activity, establishing a many-to-one relationship.
     * An activity can participate in one loyalty program at a time.
     */
    @ManyToOne
    @JoinColumn(name = "loyalty_program")
    private LoyaltyProgram loyaltyProgram;

    /**
     * The name of the loyalty program this activity is associated with.
     * This field is for convenience and may duplicate information in the loyaltyProgram field.
     */
    private String programName;

    /**
     * Constructs an Activity with the provided details.
     *
     * @param name     The name of the activity.
     * @param email    The contact email for the activity.
     * @param vatCode  The VAT code for the activity.
     * @param address  The physical address of the activity.
     * @param phone    The contact phone number for the activity.
     */
    public Activity(String name, String email, String vatCode, String address, String phone) {
        super(name, "", address, email, phone);
        this.vatCode = vatCode;
    }
}
