package it.unicam.cs.ids.LoyaltyHub.service;

import it.unicam.cs.ids.LoyaltyHub.model.ActivityAdmin;

public interface ActivityAdminManager extends EntityManager<ActivityAdmin, Long>{

   ActivityAdmin findAdminByEmail(String email);
}
