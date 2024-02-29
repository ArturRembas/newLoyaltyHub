package it.unicam.cs.ids.LoyaltyHub.service;

import it.unicam.cs.ids.LoyaltyHub.model.EmployeeJoinRequest;

/**
 * Interface for managing {@link EmployeeJoinRequest} entities within the loyalty platform.
 * Extends the generic {@code EntityManager} interface to provide CRUD operations and
 * possibly additional functionalities specific to employee join requests.
 */
public interface EmployeeJoinRequestManager extends EntityManager<EmployeeJoinRequest, Long> {
    
}
