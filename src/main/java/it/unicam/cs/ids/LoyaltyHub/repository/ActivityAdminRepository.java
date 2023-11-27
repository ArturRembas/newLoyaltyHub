package it.unicam.cs.ids.LoyaltyHub.repository;

import it.unicam.cs.ids.LoyaltyHub.model.ActivityAdmin;
import org.springframework.data.repository.CrudRepository;

public interface ActivityAdminRepository extends CrudRepository<ActivityAdmin, Long> {
    ActivityAdmin findByEmail(String email);
    ActivityAdmin findByEmailLike(String email);
    ActivityAdmin findByActivityAdminId(Long activityAdminId);
}