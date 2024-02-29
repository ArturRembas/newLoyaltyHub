package it.unicam.cs.ids.LoyaltyHub.service;

import it.unicam.cs.ids.LoyaltyHub.model.ActivityJoinRequest;

/**
 * Interface for managing {@link ActivityJoinRequest} entities. It extends the generic {@code EntityManager}
 * interface to provide additional management capabilities specific to activity join requests.
 */
public interface ActivityJoinRequestManager extends EntityManager<ActivityJoinRequest, Long> {

	Iterable<ActivityJoinRequest> listAllRequests();

	ActivityJoinRequest getRequestById(Long id);

	ActivityJoinRequest validateRequest(Long id);
	
}
