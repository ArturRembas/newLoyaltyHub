package it.unicam.cs.ids.LoyaltyHub.repository;

import it.unicam.cs.ids.LoyaltyHub.model.Activity;
import org.springframework.data.repository.CrudRepository;

public interface ActivityRepository extends CrudRepository<Activity, Long> {
}