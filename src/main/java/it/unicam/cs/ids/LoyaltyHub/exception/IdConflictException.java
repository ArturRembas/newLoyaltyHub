package it.unicam.cs.ids.LoyaltyHub.exception;

/**
 * Exception thrown when an ID conflict occurs within the LoyaltyHub system.
 * This exception is typically thrown in scenarios where an attempt is made to insert or update an entity with an ID that already exists in the system.
 */
public class IdConflictException extends Exception {

    /**
     * Constructs a new IdConflictException with the specified detail message.
     * The detail message is saved for later retrieval by the {@link Throwable#getMessage()} method.
     *
     * @param message the detail message which provides more information about the reason for the exception.
     */
    public IdConflictException(String message) {
        super(message);
    }
}
