package it.unicam.cs.ids.LoyaltyHub.service;

import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.exception.IdConflictException;
import it.unicam.cs.ids.LoyaltyHub.model.FidelityCard;
import it.unicam.cs.ids.LoyaltyHub.repository.FidelityCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * Service class for managing {@link FidelityCard} entities.
 * Implements {@link FidelityCardManager} to provide CRUD operations and additional functionality for {@link FidelityCard} entities.
 */
@Validated
@Service
public class SimpleFidelityCardManager implements FidelityCardManager {

    @Autowired
    private FidelityCardRepository fidelityCardRepository;

    /**
     * Retrieves a {@link FidelityCard} by its ID.
     * @param id ID of the {@link FidelityCard} to retrieve.
     * @return the found {@link FidelityCard}.
     * @throws EntityNotFoundException if no {@link FidelityCard} is found with the provided ID.
     */
    @Override
    public FidelityCard getInstance(Long id) throws EntityNotFoundException {
        return fidelityCardRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("FidelityCard not found for id: " + id));
    }

    /**
     * Creates and saves a new {@link FidelityCard} entity.
     * @param object {@link FidelityCard} to create.
     * @return the saved {@link FidelityCard}.
     */
    @Override
    public FidelityCard create(FidelityCard object) throws EntityNotFoundException, IdConflictException {
        return fidelityCardRepository.save(object);
    }

    
    @Override
    public FidelityCard update(FidelityCard object) throws EntityNotFoundException, IdConflictException {
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
    	return fidelityCardRepository.existsById(id);
    }
    
    @Override
    public FidelityCard createWithLoyaltyProgram(String costumerEmail, String loyaltyProgramName) {
        return null;
    }

    @Override
    public FidelityCard createWithoutLoyaltyProgram(String costumerEmail) throws Exception {
        return null;
    }

}