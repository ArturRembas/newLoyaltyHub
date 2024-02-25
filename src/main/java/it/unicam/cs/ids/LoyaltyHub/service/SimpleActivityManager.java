package it.unicam.cs.ids.LoyaltyHub.service;

import it.unicam.cs.ids.LoyaltyHub.model.Activity;
import it.unicam.cs.ids.LoyaltyHub.repository.ActivityRepository;
import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.exception.IdConflictException;
import it.unicam.cs.ids.LoyaltyHub.repository.ActivityAdminRepository;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * Service class that implements {@link ActivityManager} to manage {@link Activity} entities.
 * Provides CRUD operations and additional functionality to create activities with or without a linked loyalty program.
 */
@Validated
@Service
public class SimpleActivityManager implements ActivityManager {
    
    @Autowired
    private ActivityRepository activityRepository;
    
    @Autowired
    private ActivityAdminRepository activityAdminRepository;

    /**
     * Retrieves an {@link Activity} by its ID.
     * @param id ID of the {@link Activity} to retrieve.
     * @return the found {@link Activity}.
     * @throws EntityNotFoundException if no {@link Activity} is found with the provided ID.
     */
    @Override
    public Activity getInstance(Long id) throws EntityNotFoundException {
        return activityRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Activity not found for id: " + id));
    }

    /**
     * Creates and saves a new {@link Activity} entity.
     * @param object {@link Activity} to create.
     * @return the saved {@link Activity}.
     */
    @Override
    public Activity create(Activity object) {
        return activityRepository.save(object);
    }

    /**
     * Updates an existing {@link Activity}.
     * @param object {@link Activity} to update.
     * @return the updated {@link Activity}.
     * @throws EntityNotFoundException if no {@link Activity} is found with the provided email.
     */
    @Override
    public Activity update(Activity object) throws EntityNotFoundException {
        if(!activityRepository.existsByEmail(object.getEmail()))
            throw new EntityNotFoundException("No activity with email: " + object.getEmail() + " found");
        return activityRepository.save(object);
    }

    /**
     * Deletes an {@link Activity} by its ID.
     * @param id ID of the {@link Activity} to delete.
     * @return true if the {@link Activity} was successfully deleted, false otherwise.
     * @throws EntityNotFoundException if no {@link Activity} is found with the provided ID.
     */
    @Override
    public boolean delete(Long id) throws EntityNotFoundException {
        if(!this.exists(id))
            throw new EntityNotFoundException("No activity with id: " + id + " found");
        activityRepository.deleteById(id);
        return !this.exists(id);
    }

    /**
     * Checks if an {@link Activity} exists by its ID.
     * @param id ID of the {@link Activity} to check.
     * @return true if the {@link Activity} exists, false otherwise.
     */
    @Override
    public boolean exists(Long id) {
        return activityRepository.existsByActivityId(id);
    }

    @Override
	public Activity getActivityById(long l) {
		// TODO Auto-generated method stub
		return null;
	}
    
    @Override
    public Activity createActivityWithAdminEmail(Activity activity) throws IdConflictException, EntityNotFoundException {
        checkIfFieldsAreNotNull(activity);
        checkActivity(activity);
        activity.setActivityAdmin(activityAdminRepository.findByEmail(activity.getAdminEmail()));
        return this.create(activity);
    }
    
        
    private void checkIfFieldsAreNotNull(Activity activity) throws NullPointerException{
        Objects.requireNonNull(activity.getEmail(),"Inserire mail valida");
        Objects.requireNonNull(activity.getPhone(),"Inserire telefono valido");
        Objects.requireNonNull(activity.getAdminEmail(),"Inserire Admin valido");
    }
    
	private void checkActivity(Activity activity) throws EntityNotFoundException, IdConflictException {
		if (!activityAdminRepository.existsByEmail(activity.getAdminEmail()))
			throw new EntityNotFoundException("Admin con mail: " + activity.getAdminEmail() + " non trovato");
		if (activityRepository.existsByEmail(activity.getEmail()))
			throw new IdConflictException("Esiste già un'attività con email: " + activity.getEmail());
		if (activityRepository.existsByPhone(activity.getPhone()))
			throw new IdConflictException("Esiste già un'attività con telefono: " + activity.getPhone());
		if (activityRepository.existsByVatCode(activity.getVatCode()))
			throw new IdConflictException("Esiste già un'attività con P.Iva: " + activity.getVatCode());
	}
}