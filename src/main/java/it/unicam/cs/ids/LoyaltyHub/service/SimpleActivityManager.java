package it.unicam.cs.ids.LoyaltyHub.service;

import it.unicam.cs.ids.LoyaltyHub.model.Activity;
import it.unicam.cs.ids.LoyaltyHub.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class SimpleActivityManager implements ActivityManager{
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private ActivityAdminManager adminManager;
    @Override
    public Activity getInstance(Long id) {
        return activityRepository.findById(id).orElseThrow();
    }

    @Override
    public Activity create(Activity object) {
        return activityRepository.save(object);
    }

    @Override
    public Activity update(Activity object) {
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
    public Activity createActivityWithAdminEmail(Activity activity) {
        activity.setActivityAdmin(adminManager.findAdminByEmail(activity.getAdminEmail()));
        return activityRepository.save(activity);
    }
}
