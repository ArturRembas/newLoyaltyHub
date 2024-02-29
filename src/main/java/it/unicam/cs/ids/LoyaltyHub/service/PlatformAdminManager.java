package it.unicam.cs.ids.LoyaltyHub.service;

import it.unicam.cs.ids.LoyaltyHub.model.PlatformAdmin;

/**
 * Interface for managing {@link PlatformAdmin} entities within the loyalty platform.
 * Extends the generic {@code EntityManager} interface to provide CRUD operations and
 * possibly additional functionalities specific to platform administrator management.
 */
public interface PlatformAdminManager extends EntityManager<PlatformAdmin, Long> {
    
}
