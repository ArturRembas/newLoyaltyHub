package it.unicam.cs.ids.LoyaltyHub.repository;

import it.unicam.cs.ids.LoyaltyHub.model.EmployeeJoinRequest;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for handling persistence operations for {@link EmployeeJoinRequest} entities.
 * It provides functionalities to check for the existence of join requests by phone and email.
 */
public interface EmployeeJoinRequestRepository extends CrudRepository<EmployeeJoinRequest, Long> {

    /**
     * Checks if an {@link EmployeeJoinRequest} exists by the given phone number.
     *
     * @param phone The phone number to check for existing join requests.
     * @return {@code true} if a join request with the given phone number exists, {@code false} otherwise.
     */
    boolean existsByPhone(String phone);

    /**
     * Checks if an {@link EmployeeJoinRequest} exists by the given employee email.
     *
     * @param employeeEmail The email of the employee to check for existing join requests.
     * @return {@code true} if a join request with the given email exists, {@code false} otherwise.
     */
    boolean existsByEmployeeEmail(String employeeEmail);
}
