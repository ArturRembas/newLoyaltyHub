package it.unicam.cs.ids.LoyaltyHub.service;

import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.exception.IdConflictException;
import it.unicam.cs.ids.LoyaltyHub.model.CostumerWallet;
import it.unicam.cs.ids.LoyaltyHub.repository.CostumerWalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * Service class for managing {@link CostumerWallet} entities.
 * Implements {@link CostumerWalletManager} to provide CRUD operations and additional functionality for {@link CostumerWallet} entities.
 */
@Validated
@Service
public class SimpleCostumerWalletManager implements CostumerWalletManager {

    @Autowired
    private CostumerWalletRepository walletRepository;

    /**
     * Retrieves a {@link CostumerWallet} by its ID.
     * @param id ID of the {@link CostumerWallet} to retrieve.
     * @return the found {@link CostumerWallet}.
     * @throws EntityNotFoundException if no {@link CostumerWallet} is found with the provided ID.
     */
    @Override
    public CostumerWallet getInstance(Long id) throws EntityNotFoundException {
        return walletRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Wallet not found for id: " + id));
    }

    /**
     * Creates and saves a new {@link CostumerWallet} entity.
     * @param object {@link CostumerWallet} to create.
     * @return the saved {@link CostumerWallet}.
     */
    @Override
    public CostumerWallet create(CostumerWallet object) throws EntityNotFoundException, IdConflictException {
        return walletRepository.save(object);
    }

    // Implementations for update, delete, and exists methods
    
    @Override
    public CostumerWallet update(CostumerWallet object) throws EntityNotFoundException, IdConflictException {
        //TODO
    	return null;
    }

    @Override
    public boolean delete(Long id) throws EntityNotFoundException, IdConflictException {
        //TODO
    	return false;
    }

    @Override
    public boolean exists(Long id) {
        //TODO
    	return false;
    }
}