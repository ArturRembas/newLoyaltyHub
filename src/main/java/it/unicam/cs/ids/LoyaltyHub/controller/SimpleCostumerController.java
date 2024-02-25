package it.unicam.cs.ids.LoyaltyHub.controller;

import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.exception.IdConflictException;
import it.unicam.cs.ids.LoyaltyHub.model.Costumer;
import it.unicam.cs.ids.LoyaltyHub.repository.CostumerRepository;
import it.unicam.cs.ids.LoyaltyHub.service.CostumerManager;
import it.unicam.cs.ids.LoyaltyHub.repository.CostumerWalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Costumer")
public class SimpleCostumerController implements CostumerController{
    @Autowired
    private CostumerManager costumerManager;
    @Autowired
    private CostumerRepository costumerRepository;
    @Autowired
    private CostumerWalletRepository costumerWalletRepository;

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
    @PostMapping("/update")
    public Costumer update(@RequestBody Costumer object) throws IdConflictException, EntityNotFoundException {
    	return costumerManager.update(object);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Long id) throws IdConflictException, EntityNotFoundException {
    	return costumerManager.delete(id);
    }

    @Override
    @GetMapping("/exists/{id}")
    public boolean exists(@PathVariable  Long id) {
    	return costumerManager.exists(id);
    }

    @GetMapping("/costumerEmail/{emailCostumer}")
    public Costumer findCostumerByEmail(@PathVariable String emailCostumer) throws EntityNotFoundException {
    	return costumerRepository.findByEmail(emailCostumer);
    }
}
