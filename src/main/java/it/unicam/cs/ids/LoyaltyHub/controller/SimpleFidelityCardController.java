package it.unicam.cs.ids.LoyaltyHub.controller;

import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.exception.IdConflictException;
import it.unicam.cs.ids.LoyaltyHub.model.FidelityCard;
import it.unicam.cs.ids.LoyaltyHub.service.FidelityCardManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing fidelity cards within the Loyalty Platform.
 * Provides CRUD operations for {@link FidelityCard} entities.
 */
@RestController
@RequestMapping("/api/FidelityCard")
public class SimpleFidelityCardController {

    private final FidelityCardManager fidelityCardManager;

    public SimpleFidelityCardController(FidelityCardManager fidelityCardManager) {
        this.fidelityCardManager = fidelityCardManager;
    }

    /**
     * Retrieves a fidelity card by its ID.
     *
     * @param id The ID of the fidelity card to retrieve.
     * @return The requested {@link FidelityCard}.
     * @throws EntityNotFoundException if the fidelity card with the specified ID does not exist.
     */
    @GetMapping("/{id}")
    public ResponseEntity<FidelityCard> getInstance(@PathVariable Long id) throws EntityNotFoundException {
        FidelityCard card = fidelityCardManager.getInstance(id);
        return ResponseEntity.ok(card);
    }

    /**
     * Creates a new fidelity card.
     *
     * @param card The {@link FidelityCard} to create.
     * @return The created {@link FidelityCard}.
     * @throws EntityNotFoundException if the creation fails due to missing related entities.
     * @throws IdConflictException if there's an ID conflict with an existing fidelity card.
     */
    @PostMapping("/createNew")
    public ResponseEntity<FidelityCard> create(@RequestBody FidelityCard card) throws EntityNotFoundException, IdConflictException {
        FidelityCard createdCard = fidelityCardManager.create(card);
        return ResponseEntity.ok(createdCard);
    }

    @Override
    public FidelityCard update(FidelityCard object) throws IdConflictException, EntityNotFoundException {
        return null;
    }
    
    /**
     * Deletes a fidelity card by its ID.
     *
     * @param id The ID of the fidelity card to delete.
     * @return A boolean indicating whether the deletion was successful.
     * @throws EntityNotFoundException if the fidelity card with the specified ID does not exist.
     * @throws IdConflictException if there's an ID conflict during deletion.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) throws EntityNotFoundException, IdConflictException {
        boolean deleted = fidelityCardManager.delete(id);
        return ResponseEntity.ok(deleted);
    }

    /**
     * Checks if a fidelity card exists by its ID.
     *
     * @param id The ID of the fidelity card to check.
     * @return A boolean indicating whether the fidelity card exists.
     */
    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> exists(@PathVariable Long id) {
        boolean exists = fidelityCardManager.exists(id);
        return ResponseEntity.ok(exists);
    }
}

/*
import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.exception.IdConflictException;
import it.unicam.cs.ids.LoyaltyHub.model.FidelityCard;
import it.unicam.cs.ids.LoyaltyHub.service.FidelityCardManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/FidelityCard")
public class SimpleFidelityCardController implements FidelityCardController{
    @Autowired
    private FidelityCardManager cardManager;

    @Override
    @GetMapping("/{id}")
    public FidelityCard getInstance(Long id) throws EntityNotFoundException {
        return cardManager.getInstance(id);
    }

    @Override
    public FidelityCard create(FidelityCard object) throws EntityNotFoundException, IdConflictException {
        return null;
    }

    @Override
    public FidelityCard update(FidelityCard object) throws IdConflictException, EntityNotFoundException {
        return null;
    }

    @Override
    public boolean delete(Long id) throws IdConflictException, EntityNotFoundException {
        return false;
    }

    @Override
    public boolean exists(Long id) {
        return false;
    }

    @Override
    @PostMapping("/createNewLoyalty")
    public FidelityCard createWithLoyaltyProgram(String costumerEmail, String loyaltyProgramName) {
        return null;
    }

    @Override
    @PostMapping("/createNewNoLoyalty/{costumerEmail}")
    public FidelityCard createWithoutLoyaltyProgram(@PathVariable String costumerEmail) throws Exception {
        return cardManager.createWithoutLoyaltyProgram(costumerEmail);
    }
}
*/