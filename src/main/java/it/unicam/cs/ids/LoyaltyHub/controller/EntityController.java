package it.unicam.cs.ids.LoyaltyHub.controller;

public interface EntityController<T, I>{
    /**
     * > This function returns an instance of type T, given an id of type I
     *
     * @param id The id of the entity to be retrieved.
     * @return An instance of the class that implements this interface.
     */
    T getInstance( I id) ;


    /**
     * Create a new object of type T and return it.
     *
     * @param object The object to be created.
     * @return The object that was created.
     */
    T create(T object);


    /**
     * Update the object in the database.
     *
     * @param object The object to be updated.
     * @return The object that was updated.
     */
    T update(T object) ;


    /**
     * Delete the object with the given id from the database.
     *
     * @param id The id of the object to delete.
     * @return A boolean value.
     */
    boolean delete(I id);

    /**
     * Returns true if the object with the given id exists in the database.
     *
     * @param id The id of the entity to check for existence.
     * @return A boolean value.
     */
    boolean exists(I id);
}
