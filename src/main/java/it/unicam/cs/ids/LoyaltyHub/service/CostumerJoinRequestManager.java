package it.unicam.cs.ids.LoyaltyHub.service;

import it.unicam.cs.ids.LoyaltyHub.model.CostumerJoinRequest;

/**
 * Interface for managing {@link CostumerJoinRequest} entities within the loyalty platform.
 * This interface extends {@code EntityManager}, providing generic CRUD operations along with
 * additional functionalities specific to customer join requests.
 */
public interface CostumerJoinRequestManager extends EntityManager<CostumerJoinRequest, Long> {

	Iterable<CostumerJoinRequest> listAllRequests();

	CostumerJoinRequest getRequestById(Long id);

	CostumerJoinRequest validateRequest(Long id);
    
}
