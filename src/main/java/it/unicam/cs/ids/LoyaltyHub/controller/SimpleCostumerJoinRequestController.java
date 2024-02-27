package it.unicam.cs.ids.LoyaltyHub.controller;

import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.exception.IdConflictException;
import it.unicam.cs.ids.LoyaltyHub.model.CostumerJoinRequest;
import it.unicam.cs.ids.LoyaltyHub.service.CostumerJoinRequestManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class to manage costumer join requests in the Loyalty Platform.
 * Provides endpoints for creating, retrieving, and deleting costumer join requests.
 */
@RestController
@RequestMapping("api/CostumerRequest")
public class SimpleCostumerJoinRequestController implements CostumerJoinRequestController {

    @Autowired
    private CostumerJoinRequestManager requestManager;

    /**
     * Retrieves a specific costumer join request by its ID.
     * 
     * @param id The ID of the costumer join request to retrieve.
     * @return The requested costumer join request.
     * @throws EntityNotFoundException if no costumer join request is found with the given ID.
     */
    @Override
    @GetMapping("/{id}")
    public CostumerJoinRequest getInstance(@PathVariable Long id) throws EntityNotFoundException {
        return requestManager.getInstance(id);
    }

    /**
     * Creates a new costumer join request in the system.
     * 
     * @param object The costumer join request to create.
     * @return The created costumer join request.
     * @throws EntityNotFoundException if the creation fails due to missing data.
     * @throws IdConflictException if there is an ID conflict with an existing costumer join request.
     */
    @Override
    @PostMapping("/createNew")
    public CostumerJoinRequest create(@RequestBody CostumerJoinRequest object) throws EntityNotFoundException, IdConflictException {
        return requestManager.create(object);
    }

    /**
     * Update a costumer in the system.
     * 
     * @param object The costumer join request to create.
     * @return The created costumer join request.
     * @throws EntityNotFoundException if the creation fails due to missing data.
     * @throws IdConflictException if there is an ID conflict with an existing costumer join request.
     */
    @Override
    public CostumerJoinRequest update(CostumerJoinRequest object) throws EntityNotFoundException, IdConflictException {
        return null;
    }
    
    /**
     * Deletes a costumer join request by its ID.
     * 
     * @param id The ID of the costumer join request to delete.
     * @return True if the deletion was successful, false otherwise.
     * @throws IdConflictException if there is an ID conflict during deletion.
     * @throws EntityNotFoundException if no costumer join request is found with the given ID.
     */
    @Override
    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Long id) throws IdConflictException, EntityNotFoundException {
        return requestManager.delete(id);
    }

    /**
     * Checks if a costumer join request exists in the system by its ID.
     * 
     * @param id The ID of the costumer join request to check.
     * @return True if the costumer join request exists, false otherwise.
     */
    @Override
    @GetMapping("/exists/{id}")
    public boolean exists(Long id) {
        return requestManager.exists(id);
    }
}
