package it.unicam.cs.ids.LoyaltyHub.controller;

import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.exception.IdConflictException;
import it.unicam.cs.ids.LoyaltyHub.model.ActivityJoinRequest;
import it.unicam.cs.ids.LoyaltyHub.service.ActivityJoinRequestManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling activity join requests in the Loyalty Platform.
 * Provides endpoints to manage {@link ActivityJoinRequest} entities.
 */
@RestController
@RequestMapping("/api/ActivityRequest")
public class SimpleActivityJoinRequestController {

    @Autowired
    private ActivityJoinRequestManager requestManager;

    /**
     * Retrieves an activity join request by its ID.
     *
     * @param id The ID of the activity join request.
     * @return The requested {@link ActivityJoinRequest}.
     * @throws EntityNotFoundException If the request is not found.
     */
    @GetMapping("/{id}")
    public ActivityJoinRequest getInstance(@PathVariable Long id) throws EntityNotFoundException {
        return requestManager.getInstance(id);
    }

    /**
     * Creates a new activity join request.
     *
     * @param request The {@link ActivityJoinRequest} to create.
     * @return The created {@link ActivityJoinRequest}.
     * @throws EntityNotFoundException If related entities are not found.
     * @throws IdConflictException If there is a conflict with an existing request.
     */
    @PostMapping("/create")
    public ActivityJoinRequest create(@RequestBody ActivityJoinRequest request) throws EntityNotFoundException, IdConflictException {
        return requestManager.create(request);
    }

    /**
     * Deletes an activity join request by its ID.
     *
     * @param id The ID of the activity join request to delete.
     * @return {@code true} if the request was successfully deleted, {@code false} otherwise.
     * @throws EntityNotFoundException If the request is not found.
     * @throws IdConflictException If there is a conflict during the deletion process.
     */
    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Long id) throws EntityNotFoundException, IdConflictException {
        return requestManager.delete(id);
    }

    /**
     * Checks if an activity join request exists by its ID.
     *
     * @param id The ID of the activity join request to check.
     * @return {@code true} if the request exists, {@code false} otherwise.
     */
    @GetMapping("/exists/{id}")
    public boolean exists(@PathVariable Long id) {
        return requestManager.exists(id);
    }
}
