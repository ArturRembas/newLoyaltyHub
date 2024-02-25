package it.unicam.cs.ids.LoyaltyHub.model;

/**
 * Enumerates the different types of users in the LoyaltyHub system.
 * This allows the system to differentiate between various roles and access levels.
 */
public enum UserType {

    /**
     * Represents a costumer in the LoyaltyHub system.
     * Costumers are end-users who participate in loyalty programs.
     */
    COSTUMER,

    /**
     * Represents an administrative user who manages activities within the LoyaltyHub system.
     * Activity admins can configure and manage loyalty programs for their respective activities.
     */
    ACTIVITY_ADMIN,

    /**
     * Represents an employee associated with an activity within the LoyaltyHub system.
     * Activity employees can perform operational tasks under the guidance of an activity admin.
     */
    ACTIVITY_EMPLOYEE
}
