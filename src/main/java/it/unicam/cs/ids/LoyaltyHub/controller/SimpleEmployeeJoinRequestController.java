package it.unicam.cs.ids.LoyaltyHub.controller;

import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.exception.IdConflictException;
import it.unicam.cs.ids.LoyaltyHub.model.EmployeeJoinRequest;
import it.unicam.cs.ids.LoyaltyHub.service.EmployeeJoinRequestManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling employee join requests.
 * Provides endpoints for managing {@link EmployeeJoinRequest} entities.
 */
@RestController
@RequestMapping("/api/EmployeeRequest")
public class SimpleEmployeeJoinRequestController {

    private final EmployeeJoinRequestManager requestManager;

    public SimpleEmployeeJoinRequestController(EmployeeJoinRequestManager requestManager) {
        this.requestManager = requestManager;
    }

    /**
     * Retrieves an employee join request by its ID.
     * 
     * @param id The ID of the employee join request.
     * @return The requested {@link EmployeeJoinRequest}.
     * @throws EntityNotFoundException if the request with the specified ID does not exist.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeJoinRequest> getInstance(@PathVariable Long id) throws EntityNotFoundException {
        EmployeeJoinRequest request = requestManager.getInstance(id);
        return ResponseEntity.ok(request);
    }

    /**
     * Creates a new employee join request.
     * 
     * @param request The {@link EmployeeJoinRequest} to be created.
     * @return The created {@link EmployeeJoinRequest}.
     * @throws EntityNotFoundException if the creation fails due to missing related entities.
     * @throws IdConflictException if there's an ID conflict with an existing request.
     */
    @PostMapping("/create")
    public ResponseEntity<EmployeeJoinRequest> create(@RequestBody EmployeeJoinRequest request) throws EntityNotFoundException, IdConflictException {
        EmployeeJoinRequest createdRequest = requestManager.create(request);
        return ResponseEntity.ok(createdRequest);
    }

    /**
     * Deletes an employee join request by its ID.
     * 
     * @param id The ID of the employee join request to delete.
     * @return A boolean indicating whether the deletion was successful.
     * @throws IdConflictException if there's an ID conflict during deletion.
     * @throws EntityNotFoundException if the request with the specified ID does not exist.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) throws IdConflictException, EntityNotFoundException {
        boolean deleted = requestManager.delete(id);
        return ResponseEntity.ok(deleted);
    }

    /**
     * Checks if an employee join request exists by its ID.
     * 
     * @param id The ID of the employee join request to check.
     * @return A boolean indicating whether the request exists.
     */
    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> exists(@PathVariable Long id) {
        boolean exists = requestManager.exists(id);
        return ResponseEntity.ok(exists);
    }
}
