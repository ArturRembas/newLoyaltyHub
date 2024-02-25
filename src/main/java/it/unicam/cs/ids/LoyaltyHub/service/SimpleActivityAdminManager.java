package it.unicam.cs.ids.LoyaltyHub.service;

import it.unicam.cs.ids.LoyaltyHub.model.ActivityAdmin;
import it.unicam.cs.ids.LoyaltyHub.repository.ActivityAdminRepository;
import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.exception.IdConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import java.util.Objects;

/**
 * A service class for managing ActivityAdmin entities.
 * This class provides CRUD operations for ActivityAdmin entities using ActivityAdminRepository.
 */
@Validated
@Service
public class SimpleActivityAdminManager implements ActivityAdminManager {

    @Autowired
    private ActivityAdminRepository activityAdminRepository;

    /**
     * Retrieves an ActivityAdmin entity by its ID.
     *
     * @param id the ID of the ActivityAdmin entity to retrieve.
     * @return the retrieved ActivityAdmin entity.
     * @throws EntityNotFoundException if no ActivityAdmin entity with the given ID is found.
     */
    @Override
    public ActivityAdmin getInstance(Long id) throws EntityNotFoundException {
        return activityAdminRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ActivityAdmin not found for id: " + id));
    }

    /**
     * Creates a new ActivityAdmin entity.
     *
     * @param object the ActivityAdmin entity to create.
     * @return the created ActivityAdmin entity.
     * @throws EntityNotFoundException if no ActivityAdmin entity with the given ID is found.
     */
    @Override
    public ActivityAdmin create(ActivityAdmin object) throws IdConflictException, EntityNotFoundException {
    	checkIfParametersAreNotNull(object);
        checkActivityAdmin(object);
        return activityAdminRepository.save(object);
    }
    
    /**
     * Update an ActivityAdmin entity.
     *
     * @param object the ActivityAdmin entity to update.
     * @return the updated ActivityAdmin entity.
     * @throws EntityNotFoundException if no ActivityAdmin entity with the given ID is found.
     */
    @Override
	public ActivityAdmin update(ActivityAdmin object) throws EntityNotFoundException, IdConflictException {
		if (!activityAdminRepository.existsByEmail(object.getEmail()))
			throw new EntityNotFoundException("Nessun admin con email: " + object.getActivityAdminId() + " trovato");
		return activityAdminRepository.save(object);
    }
    
    /**
     * Delete an ActivityAdmin entity.
     *
     * @param id of the ActivityAdmin entity to delete.
     * @return TRUE if ActivityAdmin entity is deleted, FALSE otherwise.
     * @throws EntityNotFoundException if no ActivityAdmin entity with the given ID is found.
     */
    @Override
    public boolean delete(Long id) throws EntityNotFoundException {
        if(!this.exists(id)) throw new EntityNotFoundException("Nessun admin con id: "+id+" trovato");
        activityAdminRepository.deleteById(id);
        return !this.exists(id);
    }

    /**
     * Check existence of an ActivityAdmin entity.
     *
     * @param id of the ActivityAdmin entity to delete.
     * @return TRUE if ActivityAdmin entity exists, FALSE otherwise.
     */
    @Override
    public boolean exists(Long id) {
    	return activityAdminRepository.existsByActivityAdminId(id);
    }
    
    // private methods
    
	private void checkIfParametersAreNotNull(ActivityAdmin admin) throws NullPointerException {
		Objects.requireNonNull(admin.getEmail(), "Inserire mail valida");
		Objects.requireNonNull(admin.getPhone(), "Inserire telefono valido");
    }

	private void checkActivityAdmin(ActivityAdmin admin) throws IdConflictException, EntityNotFoundException {
		if (activityAdminRepository.existsByEmail(admin.getEmail()))
			throw new IdConflictException("Admin con email: " + admin.getEmail() + " presente");
		if (activityAdminRepository.existsByPhone(admin.getPhone()))
			throw new IdConflictException("Admin con telefono: " + admin.getPhone() + " presente");
	}
}