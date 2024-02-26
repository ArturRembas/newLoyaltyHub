package it.unicam.cs.ids.LoyaltyHub.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a Platform Administrator entity within the system.
 * Inherits common user attributes and behaviors from the User class.
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
public class PlatformAdmin extends User {

    /**
     * Constructs a new PlatformAdmin with specified details.
     *
     * @param name    The name of the PlatformAdmin.
     * @param surname The surname of the PlatformAdmin.
     * @param email   The email address of the PlatformAdmin.
     * @param phone   The phone number of the PlatformAdmin.
     */
    public PlatformAdmin(String name, String surname, String email, String phone) {
        super(name, surname, null, email, phone);
    }
}
