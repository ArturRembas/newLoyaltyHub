package it.unicam.cs.ids.LoyaltyHub.controller;

import it.unicam.cs.ids.LoyaltyHub.model.Employee;

/**
 * Controller interface for {@link Employee} entities.
 * Extends the {@link EntityController} interface to provide CRUD operations and potentially other custom functionalities specific to {@link Employee} entities.
 * This interface is intended to be implemented by controllers that handle HTTP requests for {@link Employee} entities.
 */
public interface EmployeeController extends EntityController<Employee, Long>{
	
}
