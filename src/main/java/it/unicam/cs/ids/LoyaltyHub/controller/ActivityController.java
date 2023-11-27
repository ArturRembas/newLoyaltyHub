package it.unicam.cs.ids.LoyaltyHub.controller;

import it.unicam.cs.ids.LoyaltyHub.model.Activity;

public interface ActivityController extends EntityController<Activity, Long>{
    Activity createActivityWithAdmin(Activity activity);
}
