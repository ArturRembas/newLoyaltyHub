package it.unicam.cs.ids.LoyaltyHub.service;

import it.unicam.cs.ids.LoyaltyHub.model.Activity;

public interface ActivityManager extends EntityManager<Activity, Long>{

    Activity createActivityWithAdminEmail(Activity activity);
}
