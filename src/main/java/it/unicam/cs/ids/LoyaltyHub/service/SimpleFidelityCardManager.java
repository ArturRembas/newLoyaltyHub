package it.unicam.cs.ids.LoyaltyHub.service;

import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.model.FidelityCard;
import it.unicam.cs.ids.LoyaltyHub.repository.FidelityCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * Service class for managing fidelity card entities within the loyalty platform.
 * Provides functionalities to create, retrieve, update points, and delete fidelity cards.
 */
@Validated
@Service
public class SimpleFidelityCardManager implements FidelityCardManager {

    @Autowired
    private FidelityCardRepository fidelityCardRepository;

    /**
     * Retrieves a fidelity card by its ID.
     *
     * @param id The ID of the fidelity card.
     * @return The {@link FidelityCard} object.
     * @throws EntityNotFoundException if the fidelity card is not found.
     */
    @Override
    public FidelityCard getInstance(Long id) throws EntityNotFoundException {
        return fidelityCardRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Fidelity card with ID " + id + " not found."));
    }

    /**
     * Creates a new fidelity card.
     *
     * @param object The {@link FidelityCard} object to create.
     * @return The created {@link FidelityCard} object.
     */
    @Override
    public FidelityCard create(FidelityCard object) {
        return fidelityCardRepository.save(object);
    }

    /**
     * Updates an existing fidelity card. This method is currently not implemented.
     *
     * @param object The {@link FidelityCard} object to update.
     * @return null as this operation is not supported.
     */
    @Override
    public FidelityCard update(FidelityCard object) {
        throw new UnsupportedOperationException("Fidelity card update not supported.");
    }

    /**
     * Updates the points on a fidelity card.
     *
     * @param object The {@link FidelityCard} to update.
     * @param points The new points value to set.
     * @return The updated points value on the fidelity card.
     * @throws EntityNotFoundException if the fidelity card is not found.
     */
    @Override
    public int updatePoints(FidelityCard object, int points) throws EntityNotFoundException {
        FidelityCard card = getInstance(object.getId());
        card.setTotalPoints(points);
        fidelityCardRepository.save(card);
        return card.getTotalPoints();
    }

    /**
     * Deletes a fidelity card by its ID.
     *
     * @param id The ID of the fidelity card to delete.
     * @return true if the deletion was successful, false otherwise.
     * @throws EntityNotFoundException if the fidelity card does not exist.
     */
    @Override
    public boolean delete(Long id) throws EntityNotFoundException {
        if (!fidelityCardRepository.existsById(id)) {
            throw new EntityNotFoundException("Fidelity card with ID " + id + " not found, cannot delete.");
        }
        fidelityCardRepository.deleteById(id);
        return !fidelityCardRepository.existsById(id);
    }

    /**
     * Checks if a fidelity card exists by its ID.
     *
     * @param id The ID of the fidelity card.
     * @return true if the fidelity card exists, false otherwise.
     */
    @Override
    public boolean exists(Long id) {
        return fidelityCardRepository.existsById(id);
    }
}
