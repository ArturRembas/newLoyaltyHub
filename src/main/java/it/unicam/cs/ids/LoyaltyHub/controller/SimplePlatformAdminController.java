package it.unicam.cs.ids.LoyaltyHub.controller;

import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.exception.IdConflictException;
import it.unicam.cs.ids.LoyaltyHub.model.Activity;
import it.unicam.cs.ids.LoyaltyHub.model.FidelityCard;
import it.unicam.cs.ids.LoyaltyHub.model.PlatformAdmin;
import it.unicam.cs.ids.LoyaltyHub.service.PlatformAdminManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing platform administrators within the loyalty platform.
 * Provides RESTful API endpoints for administrative operations such as creating, retrieving,
 * updating, and deleting platform administrators, as well as listing all enrolled customers and activities.
 */
@RestController
@RequestMapping("/api/PlatformAdmin")
public class SimplePlatformAdminController implements PlatformAdminController {

    @Autowired
    private PlatformAdminManager platformAdminManager;

    /**
     * Retrieves a platform administrator by their unique identifier.
     *
     * @param id The unique identifier of the platform administrator.
     * @return The requested platform administrator.
     * @throws EntityNotFoundException if the platform administrator with the specified ID does not exist.
     */
    @GetMapping("/{id}")
    public PlatformAdmin getInstance(@PathVariable Long id) throws EntityNotFoundException {
        return platformAdminManager.getInstance(id);
    }

    /**
     * Creates a new platform administrator.
     *
     * @param object The platform administrator to be created.
     * @return The newly created platform administrator.
     * @throws EntityNotFoundException if required entities for creation are not found.
     * @throws IdConflictException if there's a conflict in the unique constraints of the platform administrator.
     */
    @PostMapping("/createNew")
    public PlatformAdmin create(@RequestBody PlatformAdmin object) throws EntityNotFoundException, IdConflictException {
        return platformAdminManager.create(object);
    }

    @Override
    public PlatformAdmin update(PlatformAdmin object) throws EntityNotFoundException, IdConflictException {
        return null;
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Long id) throws IdConflictException, EntityNotFoundException {
        return platformAdminManager.delete(id);
    }

    @Override
    @GetMapping("/exists/{id}")
    public boolean exists(@PathVariable Long id) {
        return platformAdminManager.exists(id);
    }

    @GetMapping("/listCostumers")
    public Iterable<FidelityCard> getEnrolledCostumers(){ return fidelityCardRepository.findAll();}

    @GetMapping("/listActivities")
    public Iterable<Activity> getEnrolledActivities(){ return activityRepository.findAll();}
}
