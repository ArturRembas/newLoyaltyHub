package it.unicam.cs.ids.LoyaltyHub.repository;

import it.unicam.cs.ids.LoyaltyHub.model.Costumer;
import org.springframework.data.repository.CrudRepository;

public interface CostumerRepository extends CrudRepository<Costumer, Long> {
	boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}