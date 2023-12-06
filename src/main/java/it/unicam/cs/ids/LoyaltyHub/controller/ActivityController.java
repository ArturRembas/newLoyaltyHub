package it.unicam.cs.ids.LoyaltyHub.controller;

import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.exception.IdConflictException;
import it.unicam.cs.ids.LoyaltyHub.model.Activity;

public interface ActivityController extends EntityController<Activity, Long>{
    Activity createActivityWithAdmin(Activity activity) throws IdConflictException, EntityNotFoundException;
}
