package it.unicam.cs.ids.LoyaltyHub.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Serves as the base class for users within the loyalty platform system.
 * This abstract class provides common user properties and functionalities.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "progressiveNumber")
    @SequenceGenerator(name = "progressiveNumber", allocationSize = 1)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    private String name;
    private String surname;
    private String email;
    private String phone;

    /**
     * Constructs a new User with the specified details.
     *
     * @param name    the first name of the user
     * @param surname the surname of the user
     * @param email   the email address of the user
     * @param phone   the phone number of the user
     */
    public User(String name, String surname, String email, String phone) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
    }
}