package it.unicam.cs.ids.LoyaltyHub.exception;

/**
 * Exception thrown when an entity cannot be found within the LoyaltyHub system.
 * This can be used in scenarios where the retrieval of an entity by its identifier or other criteria yields no result.
 */
public class EntityNotFoundException extends Exception {

    /**
     * Constructs a new EntityNotFoundException with the specified detail message.
     * The detail message is saved for later retrieval by the {@link Throwable#getMessage()} method.
     *
     * @param message the detail message which provides more information about the reason for the exception.
     */
    public EntityNotFoundException(String message) {
        super(message);
    }
}
