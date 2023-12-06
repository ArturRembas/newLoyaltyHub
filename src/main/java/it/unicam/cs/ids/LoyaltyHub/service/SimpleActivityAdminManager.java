package it.unicam.cs.ids.LoyaltyHub.service;

import it.unicam.cs.ids.LoyaltyHub.exception.IdConflictException;
import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.model.ActivityAdmin;
import it.unicam.cs.ids.LoyaltyHub.repository.ActivityAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import java.util.Objects;

@Validated
@Service
public class SimpleActivityAdminManager implements ActivityAdminManager{
    @Autowired
    private ActivityAdminRepository activityAdminRepository;
    @Override
    public ActivityAdmin getInstance(Long id) {
        return activityAdminRepository.findById(id).orElseThrow();
    }

    @Override
    public ActivityAdmin create(ActivityAdmin object) throws IdConflictException, EntityNotFoundException {
    	checkIfParametersAreNotNull(object);
        checkActivityAdmin(object);
        return activityAdminRepository.save(object);
    }

    @Override
    public ActivityAdmin update(ActivityAdmin object) {
        return null;
    }

    @Override
    public boolean delete(Long id) throws EntityNotFoundException {
        if(!this.exists(id)) throw new EntityNotFoundException("Nessun admin con id: "+id+"trovata");
        activityAdminRepository.deleteById(id);
        return !this.exists(id);
    }

    @Override
    public boolean exists(Long id) {
    	return activityAdminRepository.existsByActivityAdminId(id);
    }


    private void checkActivityAdmin(ActivityAdmin admin) throws IdConflictException, EntityNotFoundException{
        if(activityAdminRepository.existsByEmail(admin.getEmail()))
            throw new IdConflictException("Un admin con email: "+admin.getEmail()+" è già presente!");
        if(activityAdminRepository.existsByPhone(admin.getPhone()))
            throw new IdConflictException("Un admin con telefono: "+admin.getPhone()+" è già presente!");
    }

    private void checkIfParametersAreNotNull(ActivityAdmin admin) throws NullPointerException{
        Objects.requireNonNull(admin.getEmail(),"Inserire mail valida!");
        Objects.requireNonNull(admin.getPhone(),"Inserire telefono valido!");
    }
}
