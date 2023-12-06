package it.unicam.cs.ids.LoyaltyHub.service;

import it.unicam.cs.ids.LoyaltyHub.model.Activity;
import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.exception.IdConflictException;

public interface ActivityManager extends EntityManager<Activity, Long> {

    Activity createActivityWithAdminEmail(Activity activity) throws IdConflictException, EntityNotFoundException;
}
