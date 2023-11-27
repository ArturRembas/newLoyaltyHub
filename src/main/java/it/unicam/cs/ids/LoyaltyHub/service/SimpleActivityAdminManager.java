package it.unicam.cs.ids.LoyaltyHub.service;

import it.unicam.cs.ids.LoyaltyHub.model.ActivityAdmin;
import it.unicam.cs.ids.LoyaltyHub.repository.ActivityAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

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
    public ActivityAdmin create(ActivityAdmin object) {
        return activityAdminRepository.save(object);
    }

    @Override
    public ActivityAdmin update(ActivityAdmin object) {
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

    @Override
    public ActivityAdmin findAdminByEmail(String email) {
        return activityAdminRepository.findByEmail(email);
    }
}
