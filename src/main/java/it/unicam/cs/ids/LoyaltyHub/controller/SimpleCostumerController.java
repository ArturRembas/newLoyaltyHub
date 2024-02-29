package it.unicam.cs.ids.LoyaltyHub.controller;

import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.exception.IdConflictException;
import it.unicam.cs.ids.LoyaltyHub.model.Costumer;
import it.unicam.cs.ids.LoyaltyHub.model.LoyaltyProgram;
import it.unicam.cs.ids.LoyaltyHub.model.Transaction;
import it.unicam.cs.ids.LoyaltyHub.service.CostumerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Controller for managing costumer-related operations within the Loyalty Platform.
 */
@RestController
@RequestMapping("/api/Costumer")
public class SimpleCostumerController implements CostumerController {

    @Autowired
    private CostumerManager costumerManager;

    /**
     * Retrieves a costumer by their unique identifier.
     *
     * @param id The unique identifier of the costumer.
     * @return The requested costumer if found.
     * @throws EntityNotFoundException If no costumer is found with the provided identifier.
     */
    @GetMapping("/{id}")
    @Override
    public Costumer getInstance(@PathVariable Long id) throws EntityNotFoundException {
        return costumerManager.getInstance(id);
    }

    /**
     * Creates a new costumer in the system.
     *
     * @param costumer The costumer information to create.
     * @return The created costumer with their generated identifier.
     * @throws EntityNotFoundException If there are any issues with entity references during creation.
     * @throws IdConflictException If there's an identifier conflict.
     */
    @PostMapping("/create")
    @Override
    public Costumer create(@RequestBody Costumer costumer) throws EntityNotFoundException, IdConflictException {
        return costumerManager.create(costumer);
    }

    /**
     * Updates an existing costumer's information.
     * Currently, this functionality is not implemented.
     *
     * @param costumer The updated costumer information.
     * @return null to indicate not implemented.
     */
    @Override
    public Costumer update(@RequestBody Costumer costumer) {
        // Update functionality not implemented
        return null;
    }

    /**
     * Deletes a costumer from the system using their unique identifier.
     *
     * @param id The unique identifier of the costumer to be deleted.
     * @return true if the deletion was successful, false otherwise.
     * @throws IdConflictException If there's an identifier conflict during deletion.
     * @throws EntityNotFoundException If no costumer is found with the provided identifier.
     */
    @DeleteMapping("/delete/{id}")
    @Override
    public boolean delete(@PathVariable Long id) throws IdConflictException, EntityNotFoundException {
        return costumerManager.delete(id);
    }

    /**
     * Checks if a costumer exists in the system based on their unique identifier.
     *
     * @param id The unique identifier of the costumer.
     * @return true if the costumer exists, false otherwise.
     */
    @GetMapping("/exists/{id}")
    @Override
    public boolean exists(@PathVariable Long id) {
        return costumerManager.exists(id);
    }

    /**
     * Retrieves the total points accumulated on a costumer's fidelity card.
     *
     * @param costumerEmail The email address associated with the costumer.
     * @return The total points on the costumer's fidelity card.
     */
    @GetMapping("/totalPoints/{costumerEmail}")
    public int getTotalCardPoints(@PathVariable String costumerEmail) {
        return costumerManager.getTotalPoints(costumerEmail);
    }

    /**
     * Lists all transactions associated with a costumer's fidelity card.
     *
     * @param costumerEmail The email address associated with the costumer.
     * @return A set of transactions linked to the costumer's fidelity card.
     */
    @GetMapping("/listTransactions/{costumerEmail}")
    public Set<Transaction> listTransactions(@PathVariable String costumerEmail) {
        return ((SimpleCostumerController) costumerManager).listTransactions(costumerEmail);
    }

    /**
     * Lists all loyalty programs a costumer is enrolled in.
     *
     * @param costumerEmail The email address associated with the costumer.
     * @return A list of loyalty programs the costumer is part of.
     */
    @GetMapping("/listPrograms/{costumerEmail}")
    public Set<LoyaltyProgram> listEnrolledPrograms(@PathVariable String costumerEmail) {
        return ((SimpleCostumerController) costumerManager).listEnrolledPrograms(costumerEmail);
    }
}