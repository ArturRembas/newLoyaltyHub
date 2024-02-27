package it.unicam.cs.ids.LoyaltyHub.controller;

import it.unicam.cs.ids.LoyaltyHub.model.Activity;

/**
 * Controller interface for {@link Activity} entities.
 * Extends the {@link EntityController} interface to provide CRUD operations and potentially other custom functionalities specific to {@link Activity} entities.
 * This interface is intended to be implemented by controllers that handle HTTP requests for {@link Activity} entities.
 */
public interface ActivityController extends EntityController<Activity, Long> {

}
