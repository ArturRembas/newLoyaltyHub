package it.unicam.cs.ids.LoyaltyHub.controller;

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