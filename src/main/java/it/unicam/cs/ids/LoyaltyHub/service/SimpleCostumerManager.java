package it.unicam.cs.ids.LoyaltyHub.service;

import it.unicam.cs.ids.LoyaltyHub.model.Costumer;
import it.unicam.cs.ids.LoyaltyHub.repository.CostumerRepository;
import it.unicam.cs.ids.LoyaltyHub.repository.CostumerWalletRepository;
import it.unicam.cs.ids.LoyaltyHub.repository.FidelityCardRepository;
import it.unicam.cs.ids.LoyaltyHub.model.CostumerWallet;
import it.unicam.cs.ids.LoyaltyHub.model.FidelityCard;
import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.exception.IdConflictException;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * Service class that implements {@link CostumerManager} to manage {@link Costumer} entities.
 * Provides CRUD operations and additional functionality such as creating a {@link CostumerWallet} and a {@link FidelityCard} for each new customer.
 */
@Validated
@Service
public class SimpleCostumerManager implements CostumerManager {
    
    @Autowired
    private CostumerRepository costumerRepository;
    
    @Autowired
    private CostumerWalletRepository walletRepository;
    
    @Autowired
    private FidelityCardRepository fidelityCardRepository;
    
    /**
     * Retrieves a {@link Costumer} by its ID.
     * @param id ID of the {@link Costumer} to retrieve.
     * @return the found {@link Costumer}.
     * @throws EntityNotFoundException if no {@link Costumer} is found with the provided ID.
     */
    @Override
    public Costumer getInstance(Long id) throws EntityNotFoundException {
        return costumerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer not found for id: " + id));
    }

    /**
     * Creates and saves a new {@link Costumer} entity, along with a new {@link CostumerWallet} and {@link FidelityCard}.
     * @param object {@link Costumer} to create.
     * @return the saved {@link Costumer}.
     */
    @Override
    public Costumer create(Costumer object) throws EntityNotFoundException, IdConflictException {
        checkFieldsAreNotNull(object);
        checkCostumer(object);
        CostumerWallet wallet= new CostumerWallet(null);
        object.setCostumerWallet(walletRepository.save(wallet));
        object.getCostumerWallet().setFidelityCard(fidelityCardRepository.save(new FidelityCard()));
        return costumerRepository.save(object);
    }

    /**
     * Updates a {@link Costumer} entity, along with a new {@link CostumerWallet} and {@link FidelityCard}.
     * @param object {@link Costumer} to create.
     * @return the saved {@link Costumer}.
     */
    @Override
	public Costumer update(Costumer object) throws EntityNotFoundException, IdConflictException {
		if (!costumerRepository.existsByEmail(object.getEmail()))
			throw new EntityNotFoundException("Nessun cliente con email: " + object.getEmail() + " presente");
		return costumerRepository.save(object);
	}
    
    /**
     * Delete {@link Costumer} entity by id.
     * @param id of {@link Costumer} to delete.
     * @return TRUE if {@link Costumer} is deleted, FALSE otherwise.
     */
    @Override
	public boolean delete(Long id) throws EntityNotFoundException {
		if (!this.exists(id))
			throw new EntityNotFoundException("Nessun cliente con id: " + id + " presente");
		costumerRepository.deleteById(id);
		return !this.exists(id);
	}
    
    /**
     * Check {@link Costumer} entity by id.
     * @param id of {@link Costumer} to check.
     * @return TRUE if {@link Costumer} exists, FALSE otherwise.
     */
    @Override
    public boolean exists(Long id) {
    	return costumerRepository.existsByCostumerId(id);
    }
        
    private void checkFieldsAreNotNull(Costumer costumer) throws NullPointerException{
    	Objects.requireNonNull(costumer.getEmail(), "Inserire email valida");
    	Objects.requireNonNull(costumer.getPhone(),"Inserire telefono valido");
    }
    
	private void checkCostumer(Costumer costumer) throws EntityNotFoundException, IdConflictException {
		if (costumerRepository.existsByEmail(costumer.getEmail()))
			throw new IdConflictException("Un cliente con email " + costumer.getEmail() + " Ã¨ giÃ  presente");
		if (costumerRepository.existsByPhone(costumer.getPhone()))
			throw new IdConflictException("Un cliente con telefono " + costumer.getPhone() + " Ã¨ giÃ  presente");
	}
}