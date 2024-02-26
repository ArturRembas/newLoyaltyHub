package it.unicam.cs.ids.LoyaltyHub.model;

/**
 * Represents a generic join request within the platform.
 * This interface defines the essential behavior for all types of join requests,
 * ensuring they can be validated according to the platform's requirements.
 */
public interface JoinRequest {

    /**
     * Validates the join request.
     * Implementations should define specific validation logic to ensure the request
     * meets the necessary criteria for acceptance.
     */
    void validate();
}
