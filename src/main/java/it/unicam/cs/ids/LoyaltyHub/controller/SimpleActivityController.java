package it.unicam.cs.ids.LoyaltyHub.controller;

import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.exception.IdConflictException;
import it.unicam.cs.ids.LoyaltyHub.model.Activity;
import it.unicam.cs.ids.LoyaltyHub.model.Costumer;
import it.unicam.cs.ids.LoyaltyHub.model.FidelityCard;
import it.unicam.cs.ids.LoyaltyHub.repository.FidelityCardRepository;
import it.unicam.cs.ids.LoyaltyHub.service.ActivityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for managing {@link Activity} entities within the Loyalty Platform.
 */
@RestController
@RequestMapping("/api/Activity")
public class SimpleActivityController implements ActivityController {

    @Autowired
    private ActivityManager activityManager;

    @Autowired
    private FidelityCardRepository fidelityCardRepository;
    
    @Autowired
    private LoyaltyProgramController loyaltyProgramController;

    /**
     * Retrieves an {@link Activity} entity by its ID.
     *
     * @param id The ID of the {@link Activity} to retrieve.
     * @return The requested {@link Activity} entity.
     * @throws EntityNotFoundException If the {@link Activity} with the specified ID is not found.
     */
    @GetMapping("/{id}")
    @Override
    public Activity getInstance(@PathVariable Long id) throws EntityNotFoundException {
        return activityManager.getInstance(id);
    }

    /**
     * Creates a new {@link Activity} entity.
     *
     * @param object The {@link Activity} object to create.
     * @return The created {@link Activity} entity.
     * @throws EntityNotFoundException If related entities are not found.
     * @throws IdConflictException If there is a conflict with an existing entity's ID.
     */
    @PostMapping("/create")
    @Override
    public Activity create(@RequestBody Activity object) throws EntityNotFoundException, IdConflictException {
        return activityManager.create(object);
    }

    /**
     * Updates an existing {@link Activity} entity.
     * Currently not implemented.
     *
     * @param object The {@link Activity} object to update.
     * @return null.
     * @throws EntityNotFoundException If the {@link Activity} to update is not found.
     * @throws IdConflictException If there is an ID conflict during the update.
     */
    @Override
    public Activity update(@RequestBody Activity object) throws EntityNotFoundException, IdConflictException {
        // Update logic here
        return null; // Placeholder return statement
    }

    /**
     * Deletes an {@link Activity} entity by its ID.
     *
     * @param id The ID of the {@link Activity} to delete.
     * @return true if the entity was successfully deleted, false otherwise.
     * @throws IdConflictException If there is an ID conflict during the deletion process.
     * @throws EntityNotFoundException If the {@link Activity} to delete is not found.
     */
    @DeleteMapping("/delete/{id}")
    @Override
    public boolean delete(@PathVariable Long id) throws IdConflictException, EntityNotFoundException {
    	loyaltyProgramController.unEnrollActivity(this.getInstance(id).getProgramName(), this.getInstance(id).getEmail());
        return activityManager.delete(id);
    }

    /**
     * Checks if an {@link Activity} entity exists by its ID.
     *
     * @param id The ID of the {@link Activity} to check.
     * @return true if the entity exists, false otherwise.
     */
    @Override
    public boolean exists(@PathVariable Long id) {
        return activityManager.exists(id);
    }

    /**
     * Lists all {@link Costumer} entities enrolled in a specific {@link Activity} by the activity's email.
     *
     * @param activityEmail The email of the {@link Activity} to list the enrolled {@link Costumer}s for.
     * @return A list of {@link Costumer}s enrolled in the specified {@link Activity}.
     */
    @GetMapping("/listEnrolledCostumers/{activityEmail}")
    public List<Costumer> getEnrolledCostumers(@PathVariable String activityEmail) {
        List<Costumer> costumers = new ArrayList<>();
        List<FidelityCard> cards = fidelityCardRepository.findByLoyaltyPrograms_ProgramName(
            activityManager.getActivityByEmail(activityEmail).getLoyaltyProgram().getProgramName()
        );
        cards.forEach(card -> costumers.add(card.getCostumer()));
        return costumers;
    }
}


/*
import it.unicam.cs.ids.LoyaltyHub.model.Activity;
import it.unicam.cs.ids.LoyaltyHub.service.ActivityManager;
import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.exception.IdConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Activity")
public class SimpleActivityController implements ActivityController{
    @Autowired
    private ActivityManager activityManager;
    @Override
    @GetMapping("/{id}")
    public Activity getInstance(@PathVariable Long id) throws EntityNotFoundException {
        return activityManager.getInstance(id);
    }

    @Override
    @PostMapping("/createNew")
    public Activity create(@RequestBody Activity object) throws IdConflictException, EntityNotFoundException {
        return activityManager.create(object);
    }

    @Override
    @PostMapping("/update")
    public Activity update(@RequestBody Activity object) throws IdConflictException, EntityNotFoundException {
    	return activityManager.update(object);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Long id) throws IdConflictException, EntityNotFoundException {
        return activityManager.delete(id);
    }

    @Override
    @GetMapping("/exists/{id}")
    public boolean exists(@PathVariable Long id) {
    	return activityManager.exists(id);
    }

    @Override
    @PostMapping("/createNewWithAdmin")
    public Activity createActivityWithAdmin(@RequestBody Activity activity) throws IdConflictException, EntityNotFoundException {
        return activityManager.createActivityWithAdminEmail(activity);
    }
}
*/