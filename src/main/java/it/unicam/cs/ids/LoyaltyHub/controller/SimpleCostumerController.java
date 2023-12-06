package it.unicam.cs.ids.LoyaltyHub.controller;

import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.exception.IdConflictException;
import it.unicam.cs.ids.LoyaltyHub.model.Costumer;
import it.unicam.cs.ids.LoyaltyHub.repository.CostumerRepository;
import it.unicam.cs.ids.LoyaltyHub.service.CostumerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Costumer")
public class SimpleCostumerController implements CostumerController{
    @Autowired
    private CostumerManager costumerManager;
    @Autowired
    private CostumerRepository costumerRepository;

    @Override
    @GetMapping("/{id}")
    public Costumer getInstance(@PathVariable Long id) throws EntityNotFoundException {
        return costumerManager.getInstance(id);
    }

    @Override
    @PostMapping("/createNew")
    public Costumer create(@RequestBody Costumer object) throws EntityNotFoundException, IdConflictException {
        return costumerManager.create(object);
    }

    @Override
    public Costumer update(Costumer object) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean exists(Long id) {
        return false;
    }
}
