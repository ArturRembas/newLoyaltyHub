package it.unicam.cs.ids.LoyaltyHub.repository;

import it.unicam.cs.ids.LoyaltyHub.model.ActivityAdmin;
import org.springframework.data.repository.CrudRepository;

public interface ActivityAdminRepository extends CrudRepository<ActivityAdmin, Long> {
    ActivityAdmin findByEmail(String email);
    boolean existsByActivityAdminId(Long activityAdminId);
    boolean existsByPhone(String phone);
    boolean existsByEmail(String email);
}