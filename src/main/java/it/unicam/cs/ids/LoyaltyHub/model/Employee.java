package it.unicam.cs.ids.LoyaltyHub.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents an employee in the loyalty platform system. Each employee is a specialized user
 * with their own unique attributes, in this case, a surname.
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Employee extends User {

    /**
     * Surname of the employee.
     */
    private String surname;

    /**
     * Constructs a new Employee with specified personal and contact details.
     * Inherits user-related attributes like name, email, and phone from the {@link User} class.
     *
     * @param name    The first name of the employee.
     * @param surname The surname of the employee.
     * @param address The physical address of the employee.
     * @param email   The email address of the employee.
     * @param phone   The contact phone number of the employee.
     */
    public Employee(String name, String surname, String address, String email, String phone) {
        super(name, surname, address, email, phone);
    }
}
