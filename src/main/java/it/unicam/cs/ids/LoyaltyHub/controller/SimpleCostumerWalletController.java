package it.unicam.cs.ids.LoyaltyHub.controller;

import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.exception.IdConflictException;
import it.unicam.cs.ids.LoyaltyHub.model.CostumerWallet;
import it.unicam.cs.ids.LoyaltyHub.model.FidelityCard;
import it.unicam.cs.ids.LoyaltyHub.repository.CostumerRepository;
import it.unicam.cs.ids.LoyaltyHub.service.CostumerManager;
import it.unicam.cs.ids.LoyaltyHub.service.CostumerWalletManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/CostumerWallet")
public class SimpleCostumerWalletController implements CostumerWalletController{

    @Autowired
    private CostumerWalletManager walletManager;
    @Autowired
    private CostumerManager costumerManager;
    @Autowired
    private CostumerRepository costumerRepository;

    @Override
    @GetMapping("/{id}")
    public CostumerWallet getInstance(Long id) throws EntityNotFoundException {
        return walletManager.getInstance(id);
    }

    @Override
    @PostMapping("/createNew")
    public CostumerWallet create(CostumerWallet object) throws EntityNotFoundException, IdConflictException {
        return walletManager.create(object);
    }

    @Override
    public CostumerWallet update(CostumerWallet object) throws IdConflictException, EntityNotFoundException {
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
}