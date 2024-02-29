package it.unicam.cs.ids.LoyaltyHub.service;

import it.unicam.cs.ids.LoyaltyHub.model.Employee;

/**
 * Interface for managing {@link Employee} entities within the loyalty platform.
 * Extends the generic {@code EntityManager} interface to provide CRUD operations and
 * possibly additional functionalities specific to employee management.
 */
public interface EmployeeManager extends EntityManager<Employee, Long> {
    
}
