package it.unicam.cs.ids.LoyaltyHub.repository;

import it.unicam.cs.ids.LoyaltyHub.model.Employee;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for managing CRUD operations for {@link Employee} entities.
 * This interface provides additional methods to check for the existence of employees by their email.
 */
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    /**
     * Checks if an employee exists by their email.
     *
     * @param email The email address to check for an existing employee.
     * @return {@code true} if an employee with the given email exists, {@code false} otherwise.
     */
    boolean existsByEmail(String email);
}
